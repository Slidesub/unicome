<template>
    <div class="dashboard">
        <!-- <transition-group name="cell" tag="div" class="container">
            <div v-for="cell in cells" :key="cell.id" class="cell">
            {{ cell.number }}
            </div>
        </transition-group> -->
        <!-- <aside>
            <div><router-link :to="{ name: 'article-list' }">ARTICLE</router-link></div>
            <div><router-link :to="{ name: 'tag-list' }">TAG</router-link></div>
        </aside> -->
        <!-- <main>
            <h1><center>{{ article.title }}</center></h1>
            <div v-html="toHtml(article.body)"></div>
        </main> -->
        <ul>
            <li v-for="(tag, index) of tags" :key="index">
                <u-panel :title="tag.title" :content="tag.desc" width="20em" height="15em"></u-panel>
            </li>
        </ul>
    </div>
</template>
<script>
import shuffle from 'lodash/shuffle'
import markdown from 'markdown'
import UPanel from '@/components/u-panel'
export default {
    components: {
        UPanel
    },
    data () {
        return {
            tags: [],

            cells: Array.apply(null, { length: 81 })
                .map(function (_, index) { 
                return {
                    id: index,
                    number: index % 9 + 1
                }
            }),
            article: {}
        }
    },
    methods: {
        timer () {
            this.cells = shuffle(this.cells)
        },
        toHtml: function (value) {
            return value ? markdown.markdown.toHTML(value) : ''
        },
        async fetch () {
            this.$http.get('/api/tags/').then(resp => {
                this.tags = resp.data.tags
            }).catch(error => {
                if (error.response) {
                    console.log(error.response.data);
                    console.log(error.response.status);
                } else if (error.request) {
                    if (error.code === 'ECONNABORTED' &&
                        error.message.startWith('timeout') &&
                        error.message.endWith('exceeded')) {
                        console.log('timeout')
                    } else {
                        console.log(error.request);
                    }
                } else {
                    console.log(error.message);
                }
            })
        }
    },
    mounted: function () {
        this.timer()
        setInterval(this.timer, 500)
    },
    created () {
        // this.$http.get(`/api/articles/5bff93ab4838811379c879cf`).then(resp => {
        //     this.article = resp.data.article || {}
        // }).catch(error => {
        //     console.log(error.message)
        // })
        this.fetch()
    }
}
</script>

<style lang="stylus" scoped>
.dashboard
    // font-family 'Press Start 2P'
    // text-align center
    display flex
    flex-direction row
    ul
        display flex
        flex-direction row
        flex-wrap wrap
        padding 0 1em
        justify-content center

// .container 
//   display flex
//   flex-wrap wrap
//   width 238px
//   margin-top 10px
//   margin 10px auto

// .cell
//   display: flex
//   justify-content space-around
//   align-items center
//   width 25px
//   height 25px
//   border 1px solid #aaa
//   margin-right -1px
//   margin-bottom -1px

// .cell:nth-child(3n)
//   margin-right 0

// .cell:nth-child(27n)
//   margin-bottom 0

// .cell-move 
//   transition transform 1s
</style>

