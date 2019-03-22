<template>
  <div id="app">
    <div>
        <u-icon color="red" type="folder" ></u-icon>
    </div>
    <div>
        <u-icon color="red" type="help-circle" ></u-icon>
    </div>
    <div>
        <u-input :model="title"></u-input>
    </div>
    <div>
        <u-button>Submit</u-button>
    </div>
    <div>
        <u-codemirror></u-codemirror>
    </div>
    <div>
        <!--<u-header></u-header>-->
    </div>
    <div>
        <u-select></u-select>
    </div>
    <div>
        <u-multiple-select placeholder="pls select" v-model="list">
            <option value="1">选项1</option>
            <option value="2">选项2</option>
            <option value="3">选项3</option>
            <option value="4">选项4</option>
            <option value="5">选项5</option>
            <option value="6">选项6</option>
            <option value="7">选项7</option>
        </u-multiple-select>
        {{list}}
    </div>
  </div>
</template>

<script>
import UHeader from './components/u-header'
import UIcon from './components/u-icon/index'
import UInput from './components/u-input/index'
import UButton from './components/u-button'
import UCodemirror from './components/u-codemirror'
import USelect from './components/u-select'
import UMultipleSelect from './components/u-multiple-select'

export default {
  name: 'app',
  components: {
    UHeader,
    UIcon,
    UInput,
    UButton,
    UCodemirror,
    USelect,
    UMultipleSelect
  },
  data () {
    return {
      title: '1',
      list: ['1']
    }
    
  }
}
</script>

<style>
</style>
