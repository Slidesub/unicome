import Vue from 'vue'
import Router from 'vue-router'
import Page from './pages/Page.vue'

Vue.use(Router)

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL + 'admin',
  routes: [
    {
      path: '',
      component: Page,
      children: [
        { path: '', name: 'dashboard', component: () => import('./pages/Dashboard.vue') },

        { path: '/article', name: 'article-list', component: () => import('./pages/article/List.vue') },
        { path: '/article/create', name: 'article-create', component: () => import('./pages/article/Edit.vue') },
        { path: '/article/preview/:id', name: 'article-preview', component: () => import('./pages/article/Preview.vue') },
        { path: '/article/:id', name: 'article-edit', component: () => import('./pages/article/Edit.vue'), meta: { isEdit: true } },

        // { path: '/article', name: 'article-list', component: () => import('./pages/article/List.vue') },
        // { path: '/article/create', name: 'article-create', component: () => import('./pages/article/Edit.vue') },
        // { path: '/article/:id/edit', name: 'article-edit', component: () => import('./pages/article/Edit.vue'), meta: { isEdit: true } },
        // { path: '/article/:id', name: 'article-preview', component: () => import('./pages/article/Preview.vue') },

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

// router.beforeEach((to, from, next) => {
//   if (to.matched.some(record => record.meta.auth)) {
//     // 发送请求验证有没有登陆
//     if () {
//       next({
//         path: '/login',
//         query: {
//           redirect: to.fullPath
//         }
//       })
//     }
//   }
//   next()
// })

export default router
