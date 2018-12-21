<template>
    <div class="u-logo">
        <img :src="src" :alt="alt" :style="{width, height}">
    </div>
</template>
<script>
import LogoPng from '@/components/u-logo/logo.png'
export default {
    props: {
        src: {
            type: String,
            default: LogoPng
        },
        alt: String,
        width: {
            type: String,
            default: '3em'
        },
        height: {
            type: String,
            default: '3em'
        }
    }
}
</script>
<style lang="stylus" scoped>
.u-logo
    font-size 16px
    display flex
    align-items center
    width 100%
    height 100%
</style>


