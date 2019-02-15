<template>
    <div class="u-layout">
        <slot name="header">
            <u-header></u-header>
        </slot>
        <slot name="section">
            <section class="section">
                <slot name="main">
                    <main><router-view/></main>
                </slot>
                <slot name="aside">
                    <aside></aside>
                </slot>
            </section>
        </slot>
        <slot name="footer">
            <footer></footer>
        </slot>
    </div>
</template>
<script>
import UHeader from '@/components/u-header'

export default {
  components: {
    UHeader
  }
}
</script>
<style lang="stylus" scoped>
.u-layout
    font-size: 16px
.section
    padding-top 4em
</style>


