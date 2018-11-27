import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import FetchPlugin from '@/plugins/fetch';

Vue.config.productionTip = false;

Vue.use(FetchPlugin);

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount('#app');
