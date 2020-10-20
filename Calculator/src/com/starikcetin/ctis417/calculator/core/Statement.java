package com.starikcetin.ctis417.calculator.core;

import com.starikcetin.ctis417.calculator.operations.IOperation;

public class Statement {
    private final IOperation operation;
    private final double[] operands;

    public Statement(IOperation operation, double[] operands) {
        this.operation = operation;
        this.operands = operands;
    }

    public double calculate() throws CalculatorException {
        return operation.calculate(operands);
    }
}
