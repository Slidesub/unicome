<template>
    <div class="u-panel">
        <div class="container" :style="{width, height}">
            <slot>
                <div class="panel-title">{{ title }}</div>
                <div class="panel-content">
                    {{ content }}
                </div>
            </slot>
        </div>
    </div>
</template>
<script>
export default {
    props: {
        title: String,
        content: String,
        width: {
            type: String,
            default: '15em'
        },
        height: {
            type: String,
            default: '10em'
        }
    }
}
</script>
<style lang="stylus" scoped>
.u-panel
    font-size 16px
    .container
        background-color #fff
        border-radius .2em
        box-shadow 0 2px 3px rgba(10,10,10,.1), 0 0 0 1px rgba(10,10,10,.1)
</style>


