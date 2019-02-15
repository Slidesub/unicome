<template>
    <span class="u-multiple-select">
        <select multiple class="multiple-select-class" v-model="selected" @change="change">
            <option v-for="(item, index) of getOptions" :key="index" :value="item.key">{{ item.value }}</option>
        </select>
    </span>
</template>
<script>
export default {
    model: {
        prop: 'value',
        event: 'change'
    },
    props: {
        value: {
            type: Array,
            default: function () {
                return []
            }
        },
        options: {
            type: Array,
            default: function () {
                return []
            }
        },
    },
    data () {
        return {
            selected: []
        }
    },
    methods: {
        change (event) {
            this.$emit('change', this.selected)
        }
    },
    computed: {
        getOptions () {
            let arrs = []
            this.options.forEach(item => {
                let arr = []
                for (name in item) {
                    arr.push(name)
                }
                if (arr && arr.length > 1) {
                    let key = arr[0]
                    let value = arr[1]
                    arrs.push({key: item[key], value: item[value]})
                }
            });
            return arrs
        }
    },
    watch: {
        value (target) {
            this.selected = target || []
        }
    }
}
</script>
<style lang="stylus" scoped>
.u-multiple-select
    font-size 16px
.multiple-select-class
    font-size inherit
    min-width 20em
    padding 0.5em
    border 1px solid #2c3e50
    border-radius .2em
    outline-color #42b983
    margin .5em 0

// // 滚动条宽高
// ::-webkit-scrollbar
//   width: 8px;
//   background-color: rgba(255, 255, 255, 0.2);

// // 定义滚动条轨道 内阴影+圆角
// ::-webkit-scrollbar-track
//   -webkit-box-shadow: inset 0 0 8px rgb(1, 64, 118);
//   border-radius: 2px;
//   background-color: rgba(245, 245, 245, 0.2);

// // 定义滑块 内阴影+圆角
// ::-webkit-scrollbar-thumb
//   border-radius: 10px;
//   -webkit-box-shadow: inset 0 0 8px #014076;
//   background-color: #555;

</style>


