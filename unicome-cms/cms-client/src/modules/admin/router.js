import Vue from 'vue'
import Router from 'vue-router'
import Page from './pages/Page.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL + 'admin',
  routes: [
    {
      path: '/',
      component: Page,
      children: [
        { path: '', name: 'dashboard', component: () => import('./pages/Dashboard.vue') },
        { path: '/article', name: 'article-list', component: () => import('./pages/article/List.vue') },
        { path: '/article/create', name: 'article-create', component: () => import('./pages/article/Edit.vue') },
        { path: '/article/:id', name: 'article-edit', component: () => import('./pages/article/Edit.vue'), meta: { isEdit: true } },
        { path: '/tag', name: 'tag-list', component: () => import('./pages/tag/List.vue') },
        { path: '/tag/create', name: 'tag-create', component: () => import('./pages/tag/Edit.vue') },
        { path: '/tag/:id', name: 'tag-edit', component: () => import('./pages/tag/Edit.vue'), meta: { isEdit: true } }
      ]
    },
    {
      path: '*',
      component: (resolve) => {
        import('./pages/error/404.vue').then((module) => {
          resolve(module)
        })
      }
    }
  ]
})
