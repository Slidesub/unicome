package org.unicome.sample.design.state.abs;

public class StartState extends AbsState {

    StateContext stateContext;
    public StartState(StateContext stateContext) {
        this.stateContext = stateContext;
    }
    @Override
    public void doAction() {
        System.out.println("Start");
    }
}
