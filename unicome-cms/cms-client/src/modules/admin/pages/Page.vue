<template>
  <div class="page">
    <!-- <u-header></u-header>
    <main style="height: 100vh;">123</main> -->
    <router-view/>
  </div>
</template>
<script>
import UHeader from '@/components/u-header'
export default {
  components: {
    UHeader
  }
}
</script>

<style lang="stylus" scoped>
</style>
