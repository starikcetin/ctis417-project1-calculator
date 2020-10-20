package com.starikcetin.ctis417.calculator.operations;

import com.starikcetin.ctis417.calculator.core.CalculatorException;

public class CosineOperation implements IOperation {
    @Override
    public double calculate(double[] operands) throws CalculatorException {
        if (operands.length != 1) {
            throw new CalculatorException("Cosine operation requires a single operand.");
        }

        return Math.cos(operands[0]);
    }
}
