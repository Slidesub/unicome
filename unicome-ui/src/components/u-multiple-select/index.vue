<template>
    <div class="u-multiple-select" ref="multipleSelect" @click="dropdown()">
        <div class="label-wrap" ref="selected">
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
            let bottom = document.querySelector('.u-multiple-select').offsetTop + document.querySelector('.u-multiple-select').clientHeight + '2em';
            // document.querySelector('.dropdown-wrap').clientHeight
            if (bottom > window.innerHeight) {
                this.$refs.dropdown.style.marginTop = '-5px';
                this.$refs.dropdown.style.top = 'auto';
                this.$refs.dropdown.style.bottom = '2em';
            } else {
                this.$refs.dropdown.style.marginTop = '5px';
                this.$refs.dropdown.style.bottom = 'auto';
                this.$refs.dropdown.style.top = '2em';
            }
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
        getSelectedValue() {
            let that = this;
            let selectedList = [];
            document.querySelectorAll('.option').forEach(option => {
                if (this.selected.indexOf(option.getAttribute('data-value')) > -1) {
                    selectedList.push(option.innerText);
                }
            });
            return selectedList.toString() || this.placeholder;
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
        this.addListener();
        this.$refs.selected.innerHTML = this.getSelectedValue();
    },
    watch: {
        selected (data) {
            this.$refs.selected.innerHTML = this.getSelectedValue();
        }
    }
}
</script>
<style lang="stylus" scoped>
.u-multiple-select
    position relative
    font-size inherit
    width 15em
    height 2em
    border 1px solid #d1d5da
    border-radius 5px
    outline none
    .label-wrap
        height 2em
        line-height 2em
        padding 0 16px 0 8px
        position relative
        overflow hidden
        text-overflow ellipsis
        white-space nowrap
    .label-wrap:after
        content ""
        position absolute
        border-style solid
        border-color transparent transparent transparent #333
        transform rotate(270deg)
        right 8px
        top 0
        bottom 0
        margin auto 0
        width 0
        height 0;
        border-top 4px solid transparent;
        border-left 0px solid transparent;
        border-bottom 4px solid transparent;
        border-right 8px solid #333;
    .dropdown-wrap
        position absolute
        margin-top 5px
        border 1px solid #d1d5da
        border-radius 5px
        padding 0 8px
        overflow auto
        background-color #fff
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


