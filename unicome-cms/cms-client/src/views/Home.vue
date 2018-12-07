<template>
  <div class="home">
    <u-header></u-header>
  </div>
</template>

<script>
// @ is an alias to /src
import UHeader from '@/components/u-header'

export default {
  name: 'home',
  components: {
    UHeader
  }
}
</script>
<style lang="stylus" scoped>
// .header
//   background-image url('../assets/bg.jpg')
//   background-repeat no-repeat
//   background-size cover
//   width 100%
//   height auto
//   display flex
//   flex-direction row
//   justify-content space-around
//   padding 15em 0
// img
//   width 100%
//   height auto
</style>

