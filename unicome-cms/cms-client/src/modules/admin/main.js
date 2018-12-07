import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuexI18n from 'vuex-i18n'
import AxiosPlugin from '@/plugins/fetch'
import en from '@/i18n/en'

Vue.config.productionTip = false

Vue.use(vuexI18n.plugin, store)
Vue.i18n.add('en', en)
Vue.i18n.set('en')

Vue.use(AxiosPlugin)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
