<template>
    <span class="u-input">
        <input class="u-input-class" :type="type" :value="value" @input="$emit('input', $event.target.value)">
    </span>
</template>
<script>
export default {
    props: {
        type: {
            type: String,
            default: 'text'
        },
        value: String
    }
}
</script>

<style lang="stylus" scoped>
.u-input
    font-size 16px
.u-input-class
    font-size inherit
    height 2em
    width 19em
    padding: 0 .5em;
    border 1px solid #2c3e50
    // border none
    border-radius .2em
    outline-color #42b983
    margin .5em 0
    // box-shadow: 0 0 1px rgba(0,0,0,0.25)
</style>

