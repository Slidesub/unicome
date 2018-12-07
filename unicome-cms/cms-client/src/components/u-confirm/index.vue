<template>
    <div class="u-confirm" v-show="show">
        <u-dialog
            v-model="show"
            :width="width"
            :height="height">
            <div class="u-confirm-flex">
                <div class="confirm-title">{{ title }}</div>
                <div class="confirm-content">
                    <div class="center">{{ content }}</div>
                </div>
                <div class="confirm-footer">
                    <u-button @click="cancel">CANCEL</u-button>
                    <u-button @click="confirm">CONFIRM</u-button>
                </div>
            </div>
        </u-dialog>
    </div>
</template>
<script>
import UButton from '../u-button'
import UDialog from '../u-dialog'
export default {
    components: {
        UDialog,
        UButton
    },
    model: {
        prop: 'show',
        event: 'change'
    },
    props: {
        show: {
            type: Boolean,
            default: false
        },
        title: {
            type: String,
            default: 'WARNING'
        },
        content: {
            type: String,
            default: 'Are you true?'
        },
        width: {
            type: Number,
            default: 200
        },
        height: {
            type: Number,
            default: 100
        }
    },
    methods: {
        cancel () {
            this.$emit('change', false)
            this.$emit('on-cancel')
        },
        confirm () {
            this.$emit('change', false)
            this.$emit('on-confirm')
        }
    },
    watch: {
        show (value) {
            this.$emit(value ? 'on-show' : 'on-hide')
        }
    }
}
</script>

<style lang="stylus" scoped>
.u-confirm
    font-size 16px
.u-confirm-flex
    width 100%
    height 100%
    display flex
    flex-direction column
.confirm-title
    font-weight 600
    text-align center
.confirm-content
    flex 1
    text-align center
    padding .5rem
    .center
        width 100%
        height 100%
        display flex
        align-items center
        justify-content center
        text-align justify
        margin auto
.confirm-footer
    display flex
    flex-direction row
    justify-content space-around
</style>
