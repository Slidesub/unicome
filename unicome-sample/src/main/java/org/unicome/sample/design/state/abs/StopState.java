package org.unicome.sample.design.state.abs;

public class StopState extends AbsState {
    StateContext stateContext;
    public StopState(StateContext stateContext) {
        this.stateContext = stateContext;
    }
    @Override
    public void doAction() {
        System.out.println("Stop");
    }
}
