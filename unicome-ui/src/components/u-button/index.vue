<template>
    <button class="u-button" @click="$emit('click')">
        <slot>{{value}}</slot>
    </button>
</template>
<script>
export default {
    props: {
        value: String
    }
}
</script>
<style lang="stylus" scoped>
.u-button
    font-size inherit
    height 2em
    padding 0 2em
    cursor pointer
    border none
    outline none
    background-color #42b983
    color #fff
    border-radius .2em
    box-shadow 1px 1px 5px rgb(132,133,135)
.u-button:hover
    background-color #006400
    color #fff
</style>