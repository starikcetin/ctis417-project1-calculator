package com.starikcetin.ctis417.calculator.operations;

import com.starikcetin.ctis417.calculator.core.CalculatorException;

public class MultiplyOperation implements IOperation {
    @Override
    public double calculate(double[] operands) throws CalculatorException {
        if (operands.length != 2) {
            throw new CalculatorException("Multiply operation requires 2 operands.");
        }

        return operands[0] * operands[1];
    }
}
