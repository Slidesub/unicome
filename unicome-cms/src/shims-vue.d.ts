declare module '*.vue' {
  import Vue from 'vue';
  export default Vue;
}

declare module 'vue/tyupes/vue' {
  interface Vue {
    $http: any
  }
}
