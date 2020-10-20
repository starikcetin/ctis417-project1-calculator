package com.starikcetin.ctis417.calculator.core;

import com.starikcetin.ctis417.calculator.io.InputObserver;
import com.starikcetin.ctis417.calculator.io.OutputEventBus;
import com.starikcetin.ctis417.calculator.operations.IOperation;

import java.util.Arrays;
import java.util.Map;

public class Calculator extends InputObserver {
    private final Map<String, IOperation> operators;
    private final OutputEventBus outputEventBus;

    public Calculator(Map<String, IOperation> operators, OutputEventBus outputEventBus) {
        this.operators = operators;
        this.outputEventBus = outputEventBus;
    }

    @Override
    public void updateInput(String input) {
        try {
            var tokens = tokenize(input);
            var statement = parse(tokens);
            var result = statement.calculate();

            outputEventBus.emit("Result: " + result);
        } catch (CalculatorException e) {
            outputEventBus.emit("Error: " + e.getMessage());
        }
    }

    private Statement parse(String[] tokens) throws CalculatorException {
        if (tokens.length == 2) {
            var operatorStr = tokens[0];
            var operandStr = tokens[1];

            var operator = getOperation(operatorStr);
            var operand = parseOperand(operandStr);

            return new Statement(operator, new double[]{operand});
        } else if (tokens.length == 3) {
            var operatorStr = tokens[1];
            var operandsStr = new String[]{tokens[0], tokens[2]};

            var operator = getOperation(operatorStr);
            var operands = parseOperands(operandsStr);

            return new Statement(operator, operands);
        }

        throw new CalculatorException("Statements must have 2 or 3 tokens.");
    }

    private double[] parseOperands(String[] operandsStr) throws CalculatorException {
        var len = operandsStr.length;
        var arr = new double[len];
        for (int i = 0; i < len; i++) {
            arr[i] = parseOperand(operandsStr[i]);
        }
        return arr;
    }

    private double parseOperand(String operandStr) throws CalculatorException {
        try {
            return Double.parseDouble(operandStr);
        } catch (NumberFormatException e) {
            throw new CalculatorException("Invalid operand: " + operandStr);
        }
    }

    private String[] tokenize(String statement) {
        var allTokens = statement.trim().split(" ");
        return Arrays.stream(allTokens).filter(x -> !x.isBlank()).toArray(String[]::new);
    }

    private IOperation getOperation(String operator) throws CalculatorException {
        var operation = operators.get(operator);

        if (operation == null) {
            throw new CalculatorException("Operator is not supported: " + operator);
        }

        return operation;
    }
}
