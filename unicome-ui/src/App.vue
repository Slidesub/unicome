<template>
  <div id="app">
    <u-icon color="red" type="folder" ></u-icon>
    <u-input  :model="title"></u-input>
    {{title}}
  </div>
</template>

<script>
import UIcon from './components/u-icon/index'
import UInput from './components/u-input/index'

export default {
  name: 'app',
  components: {
    UIcon,
    UInput
  },
  data () {
    return {
      title: '1'
    }
    
  }
}
</script>

<style>
</style>
