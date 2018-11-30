import Vue from 'vue'
import markdown from 'markdown'

Vue.filter('markdown', (value) => {
  return markdown.toHtml(value)
})
