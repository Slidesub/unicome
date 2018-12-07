<template>
  <div id="app">
    <!-- <router-view/> -->
    <u-layout></u-layout>
  </div>
</template>
<script>
import ULayout from '@/components/u-layout'
export default {
  components: {
    ULayout
  }
}
</script>

<style lang="stylus">
@import "~@/style/reset"

#app
  font-family 'Avenir', Helvetica, Arial, sans-serif
  -webkit-font-smoothing antialiased
  -moz-osx-font-smoothing grayscale
  color #2c3e50
  font-size 16px
</style>
