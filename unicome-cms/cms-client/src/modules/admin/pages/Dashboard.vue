<template>
    <div class="dashboard">
        <router-link :to="{ name: 'article-list' }">ARTICLE</router-link> | 
        <router-link :to="{ name: 'tag-list' }">TAG</router-link>

        <transition-group name="cell" tag="div" class="container">
            <div v-for="cell in cells" :key="cell.id" class="cell">
            {{ cell.number }}
            </div>
        </transition-group>
    </div>
</template>
<script>
import shuffle from 'lodash/shuffle'

export default {
    data () {
        return {
            cells: Array.apply(null, { length: 81 })
                .map(function (_, index) { 
                return {
                    id: index,
                    number: index % 9 + 1
                }
            })
        }
    },
    methods: {
        timer () {
            this.cells = shuffle(this.cells)
        }
    },
    mounted: function () {
        this.timer()
        setInterval(this.timer, 500)
    }
}
</script>

<style lang="stylus" scoped>
.dashboard
    font-family 'Press Start 2P'
    text-align center

.container 
  display flex
  flex-wrap wrap
  width 238px
  margin-top 10px
  margin 10px auto

.cell
  display: flex
  justify-content space-around
  align-items center
  width 25px
  height 25px
  border 1px solid #aaa
  margin-right -1px
  margin-bottom -1px

.cell:nth-child(3n)
  margin-right 0

.cell:nth-child(27n)
  margin-bottom 0

.cell-move 
  transition transform 1s
</style>

