<template>
    <div class="u-loading" v-show="loading">
        <div class="loading">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
        </div>
    </div>
</template>
<script>
export default {
   model: {
        prop: 'loading',
        event: 'change'
    },
    props: {
        loading: {
            type: Boolean,
            default: false
        }
    }
}
</script>
<style lang="stylus" scoped>
$delay = 0.13
.u-loading
    font-size 16px
.loading
    width 6em
    height 6em
    position relative
    margin 0 auto
    span
        display inline-block
        width 1em
        height 1em
        border-radius 50%
        background-color lightgreen
        position absolute
        animation load 1.04s ease infinite
    span:nth-child(1)
        left 0
        top (2.5 / 6 * 100%)
        animation-delay 0.13s
    span:nth-child(2)
        left (1 / 6 * 100%)
        top (1 / 6 * 100%)
        animation-delay 0.26s
    span:nth-child(3)
        left (2.5 / 6 * 100%)
        top 0
        animation-delay 0.39s
    span:nth-child(4)
        right (1 / 6 * 100%)
        top (1 / 6 * 100%)
        animation-delay 0.52s
    span:nth-child(5)
        right 0
        top (2.5 / 6 * 100%)
        animation-delay 0.65s
    span:nth-child(6)
        right (1 / 6 * 100%)
        bottom (1 / 6 * 100%)
        animation-delay 0.78s
    span:nth-child(7)
        right (2.5 / 6 * 100%)
        bottom 0
        animation-delay 0.91s
    span:nth-child(8)
        left (1 / 6 * 100%)
        bottom (1 / 6 * 100%)
        animation-delay 1.04s
@keyframes load
    50%
        opacity 0.3
        transform  scale(0.3)
    100%
        opacity 1
        transform  scale(1)
</style>