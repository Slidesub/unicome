import Vue from 'vue';
import Vuex from 'vuex';
import * as actions from './store/actions';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    errors: new Array<ApiCall>(),
  },
  getters: {
    isUnauthApiReq(state): boolean {
      return state.errors.some((rep: ApiCall)  => rep.status === 401);
    },
  },
  mutations: {
    ['SET_API_REP'](state, apiReq: {}): void {
      state.errors.push(new ApiCall(apiReq));
    },
    ['CLEAR_API_REP'](state): void {
      state.errors = state.errors.filter((rep: ApiCall) => rep.status !== 200);
    },
  },
  actions,
});

export class ApiCall {
  public status: number;
  public statusText: string;
  public message: string;
  constructor(call: any = {}) {
    this.status = call.status;
    this.statusText = call.statusText;
    this.message = call.message;
  }
}
