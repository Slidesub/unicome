import instance from './fetch';

export default {
    install(Vue: any) {
        Vue.prototype.$http = instance;
        Vue.http = instance;
    },
    $http: instance,
};

export const $http = instance;
