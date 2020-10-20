package com.starikcetin.ctis417.calculator;

import com.starikcetin.ctis417.calculator.core.Calculator;
import com.starikcetin.ctis417.calculator.io.ConsoleInputBroadcaster;
import com.starikcetin.ctis417.calculator.io.ConsoleOutputter;
import com.starikcetin.ctis417.calculator.io.OutputEventBus;
import com.starikcetin.ctis417.calculator.operations.*;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        var operators = Map.ofEntries(
                Map.entry("+", new AddOperation()),
                Map.entry("-", new SubtractOperation()),
                Map.entry("*", new MultiplyOperation()),
                Map.entry("/", new DivideOperation()),
                Map.entry("sin", new SineOperation()),
                Map.entry("cos", new CosineOperation()),
                Map.entry("sqrt", new SquareRootOperation())
        );

        var consoleInputBroadcaster = new ConsoleInputBroadcaster();
        var outputEventBus = new OutputEventBus();
        var consoleOutputter = new ConsoleOutputter();
        var calculator = new Calculator(operators, outputEventBus);

        outputEventBus.addListener(consoleOutputter);
        consoleInputBroadcaster.registerObserver(calculator);

        System.out.println("\nSupported binary operators: + - * /");
        System.out.println("Supported unary operators: sin cos sqrt");
        System.out.println("Put at least one space between each token. Example: '2 + 2' or 'sin 30'\n");

        consoleInputBroadcaster.begin();

        // These can be called later on in the lifecycle for cleanup:
        // outputEventBus.removeListener(consoleOutputter);
        // consoleInputBroadcaster.unregisterObserver(calculator);
    }
}
