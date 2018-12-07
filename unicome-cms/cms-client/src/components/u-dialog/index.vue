<template>
    <div class="u-dialog" v-show="show">
        <u-mask></u-mask>
        <div class="u-dialog-class" :style="{'width': width + 'px', 'height': height + 'px'}">
            <slot>
                <div class="dialog-title">{{ title }}</div>
                <div class="dialog-content">
                    {{ content }}
                </div>
                <div class="u-flex confirm-footer">
                    <u-button @click="hide">CANCEL</u-button>
                    <u-button @click="hide">OK</u-button>
                </div>
            </slot>
        </div>
    </div>
</template>
<script>
import UButton from '../u-button'
import UMask from '../u-mask'
export default {
    components: {
        UButton,
        UMask
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
        title: String,
        content: String,
        width: {
            type: Number,
            default: 400
        },
        height: {
            type: Number,
            default: 300
        }
    },
    methods: {
        hide () {
            this.$emit('change', false)
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
.u-dialog {
    font-size: 16px;
}
.u-dialog-class
    position: absolute;
    top: 0;
    margin: auto;
    left: 0;
    right: 0;
    bottom: 0;
    z-index: 1000;
    background: #fff;
    border-radius .2em;
    padding: .5rem
</style>
