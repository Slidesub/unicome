<template>
    <div class="articlePreview">
        <h2>{{article.title}}</h2>
        <blockquote>{{article.desc}}</blockquote>
        <div>
           <i><small>{{article.tags | toString}}</small></i>
        </div>
        <hr />
        <div v-html="toHtml(article.body)"></div>
    </div>
</template>
<script>
import markdown from 'markdown'

export default {
    data () {
        return {
            article: {}
        }
    },
    methods: {
        fetch () {
            this.$http.get(`/api/articles/${this.$route.params.id}`).then(resp => {
                this.article = resp.data.article || {}
            }).catch(error => {
                console.log(error.message)
            })
        },
        toHtml: function (value) {
            return value ? markdown.markdown.toHTML(value) : ''
        }
    },
    created () {
        this.fetch()
    },
    filters: {
        toString: (arr) => {
            let values = []
            if (arr) {
                arr.forEach(item => {
                    values.push(item.title)
                })
            }
            return values.toString()
        }
    }
}
</script>

