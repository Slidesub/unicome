<template>
    <div class="articleEdit">
        <div><input type="text" v-model="article.title" /></div>
        <div><input type="text" v-model="article.desc" /></div>
        <div>
            <select v-model="article.tags" multiple>
                <option v-for="(tag, index) of tags" :key="index" :value="tag._id">{{tag.title}}</option>
            </select>
        </div>
        <div><textarea v-model="article.body"></textarea></div>
        <div>
            <button @click="submit">publish</button>
        </div>
    </div>
</template>
<script>
export default {
    data () {
        return {
            tags: [],
            article: {
                tags: []
            }
        }
    },
    computed: {
        isEdit () {
            return this.$route.meta.isEdit
        }
    },
    methods: {
        fetch () {
            this.fetchTags()
            if (this.isEdit) {
                this.fetchArticles()
            }
        },
        fetchArticles () {
            this.$http.get(`/api/articles/${this.$route.params.id}`).then(resp => {
                this.article = resp.data.article || {}
                this.toArray()
            }).catch(error => {
                console.log(error.message)
            })
        },
        fetchTags () {
            this.$http.get('/api/tags/').then(resp => {
                this.tags = resp.data.tags || []
            }).catch(error => {
                console.log(error.message)
            })
        },
        submit () {
            let that = this
            let formData = {
                title: this.article.title,
                desc: this.article.desc,
                tags: this.article.tags,
                body: this.article.body
            }
            if (this.isEdit) {
                this.$http.put(`/api/articles/${this.$route.params.id}`, formData).then(resp => {
                    that.$router.replace({name: 'article-list'})
                }).catch(error => {
                    console.log(error.message);
                })
            } else {
                this.$http.post(`/api/articles`, formData).then(resp => {
                    that.$router.replace({name: 'article-list'})
                }).catch(error => {
                    console.log(error.message);
                })
            }
        },
        toArray () {
            let values = []
            if (this.article.tags) {
                this.article.tags.forEach(tag => {
                    values.push(tag._id)
                })
            }
            this.article.tags = values
        }
    },
    created () {
        this.fetch()
    }
}
</script>

