<template>
    <div class="tagEdit">
        <div><input type="text" v-model="tag.title" /></div>
        <div><textarea v-model="tag.desc"></textarea></div>
        <div>
            <button @click="submit">publish</button>
        </div>
    </div>
</template>
<script>
export default {
    data () {
        return {
            tag: {}
        }
    },
    computed: {
        isEdit () {
            return this.$route.meta.isEdit
        }
    },
    methods: {
        fetch (id) {
            this.$http.get(`/api/tags/${this.$route.params.id}`).then(resp => {
                this.tag = resp.data.tag || {}
            }).catch(error => {
                console.log(error.message);
            })
        },
        submit () {
            let formData = {
                title: this.tag.title,
                desc: this.tag.desc
            }
            if (this.isEdit) {
                this.$http.put(`/api/tags/${this.$route.params.id}`, formData).then(resp => {
                }).catch(error => {
                    console.log(error.message);
                })
            } else {
                this.$http.post(`/api/tags`, formData).then(resp => {
                }).catch(error => {
                    console.log(error.message);
                })
            }
        }
    },
    created () {
        if (this.isEdit) {
            this.fetch()
        }
    }
}
</script>

