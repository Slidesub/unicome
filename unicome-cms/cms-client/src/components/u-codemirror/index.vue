<template>
    <div class="u-code-mirror">
        <div class="code-mirror-class">
            <textarea ref="editor" :value="values"></textarea>
        </div>
    </div>
</template>
<script>
import * as CodeMirror from 'codemirror'
export default {
    model: {
        prop: 'value',
        event: 'input'
    },
    props: {
        value: {
            type: String,
            default: ''
        }
    },
    data () {
        return {
            editor: null,
            values: this.value
        }
    },
    mounted () {
        this.initEditor()
    },
    methods: {
        initEditor () {
            let that = this
            this.editor = CodeMirror.fromTextArea(this.$refs.editor, {
                lineNumbers: true,
                value: this.values
            })
            this.editor.on('change', function (event) {
                that.$emit('input', event.getValue())
            })
        }
    },
    watch: {
        value (target) {
            if (this.editor && !this.editor.getValue()) {
                this.editor.setValue(target)
            }
        }
    }
}
</script>
<style lang="stylus" scoped>
@import '~codemirror/lib/codemirror.css'

.u-code-mirror
    font-size 16px
.code-mirror-class
    border 1px solid #2c3e50
    border-radius .2em
    margin .5em 0
</style>

