import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    errors: new Array<ApiReq>(),
  },
  getters: {
    isUnauthApiReq(state): boolean {
      return state.errors.some((rep: ApiReq)  => rep.statusCode === 401);
    }
  },
  mutations: {
    ['SET_API_REP'](state, apiReq: {}): void {
      state.errors.push(new ApiReq(apiReq));
    },
    ['CLEAR_API_REP'](state): void {
      state.errors = state.errors.filter((rep: ApiReq) => rep.statusCode !== 200);
    }
  },
  actions: {
    clearApiRep({ commit }): void {
      commit('CLEAR_API_REP');
    }
  },
}); 

export class ApiReq {
  public code: number;
  public msg: string;
  public statusCode: number;

  constructor(apiReq: any = {}) {
    this.code = apiReq.code;
    this.msg = apiReq.msg;
    this.statusCode = apiReq.statusCode;
  }
}
