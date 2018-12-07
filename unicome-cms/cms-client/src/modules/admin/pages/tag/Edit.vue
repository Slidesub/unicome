<template>
    <div class="tagEdit">
        <div><u-input v-model.trim="tag.title"></u-input></div>
        <div><u-input v-model.trim="tag.desc"></u-input></div>
        <u-button @click="showConfirm">PUBLISH</u-button>
        <u-confirm v-model="show" @on-confirm="submit"></u-confirm>
    </div>
</template>
<script>
import UInput from '@/components/u-input'
import UButton from '@/components/u-button'
import UConfirm from '@/components/u-confirm'
export default {
    components: {
        UInput,
        UButton,
        UConfirm
    },
    data () {
        return {
            show: false,
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
            let that = this
            let formData = {
                title: this.tag.title,
                desc: this.tag.desc
            }
            if (this.isEdit) {
                this.$http.put(`/api/tags/${this.$route.params.id}`, formData).then(resp => {
                    that.$router.replace({name: 'tag-list'})
                }).catch(error => {
                    console.log(error.message);
                })
            } else {
                this.$http.post(`/api/tags`, formData).then(resp => {
                    that.$router.replace({name: 'tag-list'})
                }).catch(error => {
                    console.log(error.message);
                })
            }
        },
        showConfirm() {
            this.show = !this.show
        }
    },
    created () {
        if (this.isEdit) {
            this.fetch()
        }
    }
}
</script>

