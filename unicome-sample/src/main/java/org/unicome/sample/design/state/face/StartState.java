package org.unicome.sample.design.state.face;

public class StartState implements State {
    @Override
    public void doAction() {
        System.out.println("Start");
    }
}
