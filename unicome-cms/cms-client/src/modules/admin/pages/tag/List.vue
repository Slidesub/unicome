<template>
    <div class="tagList">
        <router-link :to="{ name: 'tag-create'}">CREATE</router-link>
        <table>
            <thead>
                <tr>
                    <td width="10%"></td>
                    <td width="20%">TITLE</td>
                    <td width="20%">DESC</td>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(tag, index) of tags" :key="index">
                    <td>{{++index}}</td>
                    <td><router-link :to="{ name: 'tag-edit', params: {id: tag._id} }">{{tag.title}}</router-link></td>
                    <td>{{tag.desc | limit}}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>
<script>
export default {
    data () {
        return {
            tags: []
        }
    },
    methods:{
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
    filters: {
        limit: (value) => {
            if (value && value.length > 30) {
                return value.substring(0, 30) + ' ...'
            }
            return value
        }
    },
    created () {
        this.fetch()
    }
}
</script>


