<template>
    <div id="articleList">

    </div>
</template>
<script lang="ts">
import { Vue, Component } from 'vue-property-decorator'

@Component
export default class ArticleList extends Vue {

    async fetch() {
        this.$http.get('/')
    }
    created () {
        fetch()
    }
}
</script>
