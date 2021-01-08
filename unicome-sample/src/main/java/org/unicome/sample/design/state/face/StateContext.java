package org.unicome.sample.design.state.face;

public class StateContext implements State {

    public State startState = new StartState();
    public State stopState = new StopState();

    private State state;

    public StateContext() {
        this.state = startState;
    }

    @Override
    public void doAction() {
        state.doAction();
    }
}
