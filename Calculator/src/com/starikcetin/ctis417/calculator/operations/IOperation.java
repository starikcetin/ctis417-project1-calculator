package com.starikcetin.ctis417.calculator.operations;

import com.starikcetin.ctis417.calculator.core.CalculatorException;

public interface IOperation {
    double calculate(double[] operands) throws CalculatorException;
}
