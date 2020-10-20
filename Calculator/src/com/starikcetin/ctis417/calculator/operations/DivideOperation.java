package com.starikcetin.ctis417.calculator.operations;

import com.starikcetin.ctis417.calculator.core.CalculatorException;

public class DivideOperation implements IOperation {
    @Override
    public double calculate(double[] operands) throws CalculatorException {
        if (operands.length != 2) {
            throw new CalculatorException("Divide operation requires 2 operands.");
        }

        return operands[0] / operands[1];
    }
}
