package org.unicome.sample.design.state.abs;

import lombok.Getter;
import lombok.Setter;

public class StateContext extends AbsState {

    public AbsState startState = new StartState(this);
    public AbsState stopState = new StopState(this);

    @Getter
    @Setter
    private AbsState state;

    public StateContext(String command) {
        if ("start".equalsIgnoreCase(command)) {
            this.state = startState;
        } else {
            this.state = stopState;
        }
    }

    @Override
    public void doAction() {
        state.doAction();
    }

    public static void main(String[] args) {
        StateContext stateContext = new StateContext("start");
        stateContext.doAction();
    }
}
