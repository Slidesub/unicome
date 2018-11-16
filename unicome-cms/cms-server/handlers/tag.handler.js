const Tag = require('../models/Tag')

class TagHandler {
    static async add (ctx) {
        const data = ctx.request.body
        const doc = {
            code: data.code,
            name: data.name,
            desc: data.desc,
        }
        let tag = await Tag.create(doc)
        if (tag) {
            return { code: 200, msg: 'SUCCESS', data: tag }
        }
        return { code: 200, msg: 'FAILED' }
    }

    static async delete(ctx) {
        const user = await User.verify(ctx.headers.authorization)
        if (user) {
            const ids = ctx.params.ids.split(',')
            let tag = await Tag.remove({'_id': {$in: ids}})
            if (tag) {
                return { code: 200, msg: 'SUCCESS' }
            }
        }
        return { code: 200, msg: 'FAILED' }
    }

    static async update(ctx) {
        const user = await User.verify(ctx.headers.authorization)
        if (user) {
            const data = ctx.request.body
            const doc = {
                code: data.code,
                name: data.name,
                desc: data.desc,
                author: user.id
            }
            let tag = await Tag.update({_id: ctx.params.id}, doc)
            if (tag) {
                return { code: 200, msg: 'SUCCESS' }
            }
        }
        return { code: 200, msg: 'FAILED' }
    }

    static async get(ctx) {
        const tag = await Tag.findOne({_id: ctx.params.id})
        return { code: 200, msg: 'SUCCESS', data: tag }
    }

    static async list(ctx) {
        const data = ctx.query;
        // const search = data.search
        let tags = []
        let count = await Tag.countDocuments()
        if (data.size && data.index && parseInt(data.size) > 0) {
            const size = parseInt(data.size)
            const index = parseInt(data.index)
            tags = await Tag.find().skip(size * (index - 1)).limit(size).exec()
        } else {
            tags = await Tag.find()
        }
        return { code: 200, msg: 'SUCCESS', data: { tags, count } }
    }
}

module.exports = TagHandler