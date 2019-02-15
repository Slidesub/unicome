<template>
    <span class="u-icon">
        <svg xmlns="http://www.w3.org/2000/svg" class="icon" aria-hidden="true">
            <use :fill="color" :xlink:href="iconName"></use>
        </svg>
    </span>
</template>
<script>
import './svg.js'
export default {
    name: 'u-icon',
    props: {
        type: {
            type: String,
            required: true
        },
        color: {
            type: String,
            default: '#000'
        }
    },
    computed: {
        iconName() {
            return `#icon-${this.type}`
        }
    }
}
</script>
<style scoped>
.icon {
  width: 1em;
  height: 1em;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}
.xl {
    width: 1.5em;
    height: 1.5em;
}
.xxl {
    width: 2em;
    height: 2em;
}
.xxxl {
    width: 3em;
    height: 3em;
}
</style>
