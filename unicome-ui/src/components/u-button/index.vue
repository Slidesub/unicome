<template>
    <div class="u-button">
        <button @click="$emit('click')">
            <slot>{{value}}</slot>
        </button>
    </div>
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
    font-size 16px
button
    cursor pointer
    border none
    outline none
    background-color #2c3e50
    color #fff
    min-width 6em
    padding .5em
    border-radius .2em
button:hover
    background-color #42b983
    color #fff
</style>


