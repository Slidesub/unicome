<template>
    <div class="articleList">
        <router-link :to="{ name: 'article-create'}">CREATE</router-link>
        <table>
            <thead>
                <tr>
                    <td width="10%"></td>
                    <td width="20%">TITLE</td>
                    <td width="20%">DESC</td>
                    <td width="10%">PREVIEW</td>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(article, index) of articles" :key="index">
                    <td>{{++index}}</td>
                    <td>
                        <router-link :title="article.title" :to="{ name: 'article-edit', params: {id: article._id} }">
                            {{article.title|limit}}
                        </router-link></td>
                    <td :title="article.desc">{{article.desc | limit}}</td>
                    <td><router-link :to="{ name: 'article-preview', params: {id: article._id} }">-></router-link></td>
                </tr>
            </tbody>
        </table>
    </div>
</template>
<script>
export default {
    data () {
        return {
            articles: []
        }
    },
    methods:{
        async fetch () {
            this.$http.get('/api/articles/').then(resp => {
                this.articles = resp.data.articles
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
    filters: {
        limit: (value) => {
            if (value && value.length > 30) {
                return value.substring(0, 30)  + ' ...'
            }
            return value
        }
    },
    created () {
        this.fetch()
    }
}
</script>


