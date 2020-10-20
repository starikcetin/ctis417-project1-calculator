package com.starikcetin.ctis417.calculator.io;

import java.util.ArrayList;
import java.util.Objects;

public class InputSubject {
    private final ArrayList<InputObserver> observers = new ArrayList<>();

    public void registerObserver(InputObserver observer) {
        observers.add(observer);
    }

    public void unregisterObserver(InputObserver observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(String input) {
        observers.stream().filter(Objects::nonNull).forEach(x -> x.updateInput(input));
    }
}
