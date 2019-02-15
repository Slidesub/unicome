<template>
    <span class="u-select">
        <select class="select-class" :value="value" @change="$emit('change', $event.target.value)">
            <option value="0">please select</option>
            <option v-for="(item, index) of getOptions" :key="index" :value="item.key">{{ item.value }}</option>
        </select>
    </span>
</template>
<script>
export default {
    props: {
        value: {
            type: String
        },
        options: {
            type: Array,
            default: function () {
                return []
            }
        },
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
    }
}
</script>
<style lang="stylus" scoped>
.u-select
    font-size 16px
.select-class
    width 20em
    height 2em
    margin 0.5em
</style>


