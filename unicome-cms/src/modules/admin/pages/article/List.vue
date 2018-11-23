<template>
    <div id="articleList">
        123
    </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';

@Component({})
export default class ArticleList extends Vue {

    public async fetch() {
        this.$http.get('/api/articles/').then(resp => {
            // console.log(resp.data);
        }).catch(err => {
            // console.log(err);
        })
    }
    public created() {
        this.fetch();
    }
}
</script>
