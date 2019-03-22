<template>
    <div class="article-edit">
        <div><u-input v-model.trim="article.title"></u-input></div>
        <div><u-input v-model.trim="article.desc"></u-input></div>
        <div><u-multiple-select v-model="article.tags" :options="tags"></u-multiple-select></div>
        <u-code-mirror v-model="article.body"></u-code-mirror>
        <u-button @click="showConfirm">PUBLISH</u-button>
        <u-confirm v-model="show" @on-confirm="submit"></u-confirm>
    </div>
</template>
<script>
import UConfirm from '@/components/u-confirm'
import UDialog from '@/components/u-dialog'
// import UInput from '@/components/u-input'
import UButton from '@//components/u-button'
import UCodeMirror from '@/components/u-codemirror'
import uMultipleSelect from '@/components/u-multiple-select'
import UInput from 'unicome-ui/src/components/u-input/index'
export default {
    components: {
        UConfirm,
        UDialog,
        UInput,
        UButton,
        UCodeMirror,
        uMultipleSelect
    },
    data () {
        return {
            tags: [],
            article: {
                tags: []
            },
            show: false,
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
        showConfirm() {
            this.show = !this.show
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
<style lang="stylus" scoped>
.article-edit
    center

</style>


