import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    loading: []
  },
  mutations: {
    addLoading (state, msg = {}) {
      state.loading.add(msg)
    },
    removeLoading (state, msg = {}) {
      state.loading.forEach((item, index) => {
        if (item.url === msg.url) {
          item = undefined
        }
      })
    }
  },
  actions: {
  }
})