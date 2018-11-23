const router = require('koa-router')()
const FileEntrytHandler = require('../handlers/fileEntry.handler')

router.prefix('/api/entities')

router.post('/', async (ctx, next) => {
    const result = await ArticleHandler.add(ctx)
    ctx.body = result
})

router.delete('/:id', async (ctx, next) => {
    const result = await ArticleHandler.delete(ctx)
    ctx.body = result
})

router.put('/:id', async (ctx, next) => {
    const result = await ArticleHandler.update(ctx)
    ctx.body = result
})

router.get('/:id', async (ctx, next) => {
    const result = await ArticleHandler.get(ctx)
    ctx.body = result
})

router.get('/', async (ctx, next) => {
    const result = await ArticleHandler.list(ctx)
    ctx.body = result
})

router.post('/upload', async (ctx, next) => {
    const result = await FileEntrytHandler.upload(ctx);
    ctx.body = result;
})

module.exports = router

// ctx.query
// ctx.params
// ctx.request.body