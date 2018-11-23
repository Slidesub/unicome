const router = require('koa-router')()
const article = require('./article.routes')
const tag = require('./tag.routes')
const entity = require('./entity.routes')

router.use(article.routes(), article.allowedMethods())
router.use(tag.routes(), tag.allowedMethods())
router.use(entity.routes(), entity.allowedMethods())

module.exports = router