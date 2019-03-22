<template>
    <div class="u-multiple-select" ref="multipleSelect" @click="dropdown()">
        <div class="label-wrap" ref="selected">
            {{selected.toString() || placeholder}}
        </div>
        <div ref="dropdown" class="dropdown-wrap" v-show="isShow">
            <u-options @select="change"></u-options>
        </div>
    </div>
</template>
<script>
import UOptions from './options.js';
export default {
    components: {
        UOptions
    },
    model: {
        prop: 'value',
        event: 'change'
    },
    props: {
        placeholder: {
            type: String,
            default: 'Please Select'
        },
        value: {
            type: Array,
            default: function () {
                return []
            }
        }
    },
    data () {
        return {
            isShow: false,
            selected: this.value || []
        }
    },
    methods: {
        dropdown () {
            if (!this.isShow) {
                this.isShow = true;
            }
        },
        change (evt) {
            if (this.hasClass(evt.target, 'selected')) {
                this.removeClass(evt.target, 'selected');
                this.selected.splice(this.selected.indexOf(evt.target.getAttribute('data-value')), 1);
            } else {
                this.addClass(evt.target, 'selected');
                this.selected.push(evt.target.getAttribute('data-value'));
            }
            // 改变model的value值
            this.$emit('change', this.selected);
        },
        hasClass(obj, cls) {
            let classNameList = obj.className.split(/\s+/);
            for (let x in classNameList) {
                if (classNameList[x] === cls) {
                    return true;
                }
            }
            return false;
        },
        addClass(obj, cls) {
            let blank = obj.className != '' ? ' ' : '';
            obj.className = obj.className + blank + cls;
        },
        removeClass(obj, cls) {
            // g 全局，i 大小写不敏感
            // \s+ 查找多个空白字符
            let removed = (' ' + obj.className.replace(/(\s+)/gi, ' ') + ' ').replace(' ' + cls + ' ', '');
            obj.className = removed.replace(/(^\s+)|(\s+$)/g, '');
        },
        showSelectedValue() {
            let that = this;
            let selectedValue = '';
            document.querySelectorAll('.option').forEach(option => {
                if (this.selected.indexOf(option.getAttribute('data-value')) > -1) {
                    selectedValue += selectedValue === ''? option.innerText : ', ' + option.innerText;
                }
            });
            this.$refs.selected.innerHTML = selectedValue;
        },
        addListener() {
            let that = this;
            document.addEventListener('click', function (event) {
                if (that.isShow &&  !that.$refs.multipleSelect.contains(event.target)) {
                    that.isShow = false;
                }
            });
        }
    },
    computed: {
    },
    mounted () {
        this.showSelectedValue();
        this.addListener();
    },
    watch: {
        selected (data) {
            this.showSelectedValue();
        }
    }
}
</script>
<style lang="stylus" scoped>
.u-multiple-select
    font-size inherit
    width 15em
    height 2em
    border 1px solid #d1d5da
    border-radius 5px
    outline none
    .label-wrap
        height 2em
        line-height 2em
        padding 0 8px
    .dropdown-wrap
        margin-top 5px
        border 1px solid #d1d5da
        border-radius 5px
        padding 0 8px
        overflow auto
        .options
            max-height 10em
            .optgroup-label
                font-weight bold
                line-height 2em
                border-bottom 1px solid #eee
            .option
                border-bottom 1px solid #d1d5da
                line-height 2em
                height 2em
            .option:last-child
                border-bottom none
            .selected
                background-color #eee
</style>


