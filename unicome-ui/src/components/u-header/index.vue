<template>
    <header class="header fixed" ref="header" v-scroll>
        <slot>
             <!--<ul>
                <router-link :to="{ name: 'dashboard' }"><u-logo></u-logo></router-link>
                <li><router-link :to="{ name: 'article-list' }">ARTICLE</router-link></li>
                <li><router-link :to="{ name: 'tag-list' }">TAG</router-link></li>
            </ul>
            -->
            <ul>
                <li></li>
            </ul>
        </slot>
    </header>
</template>

<script>
import ULogo from '@/components/u-logo'
export default {
  components: {
    ULogo
  },
  data () {
    return {
      scrollTop: 0
    }
  },
  directives: {
    scroll: {
      bind: function (el, binding, vnode) {
        window.addEventListener('scroll', vnode.context.handleScroll)
      },
      unbind: function (el, binding, vnode) {
        window.removeEventListener('scroll', vnode.context.handleScroll)
      }
    }
  },
  methods: {
    handleScroll: function (evt) {
      let offset = document.documentElement.scrollTop || window.pageYOffset || document.body.scrollTop
      if (offset > this.scrollTop) {
        // down
        if (offset > 50) {
        this.$refs.header.style.top = '-4em'
        }
      } else {
        // up
        this.$refs.header.style.top = '0'
      }
      this.scrollTop = offset
    }
  }
}
</script>
<style lang="stylus" scoped>
.header
  font-size inherit
  display flex
  flex-direction row
  justify-content space-between
  background-color #fff
  box-shadow 0 2px 4px -1px rgb(132,133,135)
  padding 0 1em
  height 4em
  transition top .2s ease-in-out
  z-index 9999
  ul
    list-style none
    display flex
    flex-direction row
    margin 0
    padding 0
    li
      padding 1.5em
      line-height 1
.fixed
    position fixed
    left 0
    right 0
</style>
