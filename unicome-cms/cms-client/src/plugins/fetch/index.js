import axios from 'axios'

const instance = axios.create()

instance.defaults.timeout = 5000 // 5s

// instance.interceptors.response.use(resp => {
//     return resp.data;
// }, err => {
//     if (err.response) {
//         console.log(err.response.data);
//         console.log(err.response.status);
//     } else if (err.request) {
//         if (err.code === 'ECONNABORTED' &&
//             err.message.startWith('timeout') &&
//             err.message.endWith('exceeded')) {
//             console.log(err.message);
//         } else {
//             console.log(err.request);
//         }
//     } else {
//         console.log(err.message);
//     }
// });

export default {
  install (Vue) {
    Vue.prototype.$http = instance
    Vue.http = instance
  },
  $http: instance
}

export const $http = instance
