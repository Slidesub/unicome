<template>
    <header class="header" ref="header" v-scroll>
        <slot>
             <ul>
                <li class="logo">UNICOME.ORG</li>
                <li><router-link :to="{ name: 'dashboard' }">INDEX</router-link></li>
                <li><router-link :to="{ name: 'article-list' }">ARTICLE</router-link></li>
                <li><router-link :to="{ name: 'tag-list' }">TAG</router-link></li>
            </ul>
            <ul>
                <li>name|logout</li>
            </ul>
        </slot>
    </header>
</template>

<script>
export default {
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
  font-size 16px
  position fixed
  display flex
  flex-direction row
  justify-content space-between
  background-color #fff
  box-shadow 0 2px 4px -1px rgba(0,0,0,.25)
  width 100%
  height 4em
  transition top .2s ease-in-out
  ul
    display flex
    flex-direction row
    margin 0
    padding 0
    li
      padding 1.5em
      line-height 1
</style>
