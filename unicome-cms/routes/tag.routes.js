const router = require('koa-router')()
const TagHandler = require('../handlers/tag.handler')

router.prefix('/tags')

router.post('/', async (ctx, next) => {
    const result = await TagHandler.add(ctx)
    ctx.body = result
})

router.delete('/:ids', async (ctx, next) => {
    const result = await TagHandler.delete(ctx)
    ctx.body = result
})

router.put('/:id', async (ctx, next) => {
    const result = await TagHandler.update(ctx)
    ctx.body = result
})

router.get('/:id', async (ctx, next) => {
    const result = await TagHandler.get(ctx)
    ctx.body = result
})

router.get('/', async (ctx, next) => {
    const result = await TagHandler.list(ctx)
    ctx.body = result
})

module.exports = router

// ctx.query
// ctx.params
// ctx.request.body