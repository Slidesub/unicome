<template>
        <input class="u-input-class" :placeholder="placeholder" :type="type" :value="value" @input="$emit('input', $event.target.value)">
</template>
<script>
export default {
    props: {
        type: {
            type: String,
            default: 'text'
        },
        value: String,
        placeholder: String
    }
}
</script>

<style lang="stylus" scoped>
.u-input-class
    font-size inherit
    height 2em
    width 15em
    padding 0 8px;
    border 1px solid #d1d5da
    border-radius 5px
    outline none
</style>

