const Article = require('../models/Article')
const Tag = require('../models/Tag')
const FileEntry = require('../models/FileEntry')

class ArticleHandler {
    static async add(ctx) {
        const data = ctx.request.body
        const doc = {
            title: data.title,
            desc: data.desc,
            body: data.body,
            icon: data.icon,
            tags: data.tags
        }
        let article = await Article.create(doc)
        if (article) {
            return { code: 200, msg: 'SUCCESS' }
        }
        return { code: 500, msg: 'FAILED' }
    }

    static async delete(ctx) {
        let article = await Article.deleteOne({_id: ctx.params.id})
        if (article) {
            return { code: 200, msg: 'SUCCESS' }
        }
        return { code: 500, msg: 'FAILED' }
    }

    static async update(ctx) {
        const data = ctx.request.body
        const doc = {
            title: data.title,
            desc: data.desc,
            icon: data.icon,
            body: data.body,
            tags: data.tags
        }
        let article = await Article.update({_id: ctx.params.id}, doc)
        if (article) {
            return { code: 200, msg: 'SUCCESS' }
        }
        return { code: 500, msg: 'FAILED' }
    }

    static async get(ctx) {
        const article = await Article.findOne({_id: ctx.params.id})
            .populate({path: 'icon', select: '_id name url', model: FileEntry})
            .exec()
        return { code: 200, msg: 'SUCCESS', data: article }
    }

    static async list(ctx) {
        const data = ctx.query;
        // const search = data.search
        let articles = []
        let count = await Article.countDocuments()
        if (data.size && data.index) {
            const size = parseInt(data.size)
            const index = parseInt(data.index)
            articles = await Article.find().skip(size * (index - 1)).limit(size)
                .populate({path: 'icon', select: '_id name url', model: FileEntry})
                .populate({path: 'tags', select: '_id code name', model: Tag})
                .exec()
        } else {
            articles = await Article.find()
        }
        return { articles, count }
    }

}

module.exports = ArticleHandler