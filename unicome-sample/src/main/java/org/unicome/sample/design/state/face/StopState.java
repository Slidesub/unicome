package org.unicome.sample.design.state.face;

public class StopState implements State {
    @Override
    public void doAction() {
        System.out.println("Stop");
    }
}
