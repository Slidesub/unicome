const mongoose = require('mongoose')
const Schema = mongoose.Schema

const ArticleSchema = new Schema({
    title: {
        type: String,
        unique: false,
        require: false
    },
    desc: {
        type: String,
        unique: false,
        require: false
    },
    icon: {
        type: Schema.Types.ObjectId,
        unique: false,
        require: false
    },
    body: {
        type: String,
        unique: false,
        require: false
    },
    hot: {
        type: Boolean,
        require: false
    },
    tags: {
        type: [ Schema.Types.ObjectId ],
        unique: false,
        require: false
    }
});

ArticleSchema.index({body: 'text'})

ArticleSchema.post('findOne', function (article, next) {
    article.title = decodeURI(article.title)
    article.desc = decodeURI(article.desc)
    article.body = decodeURI(article.body)
    next();
});

ArticleSchema.post('find', function (articles, next) {
    articles.forEach(article => {
        article.title = decodeURI(article.title)
        article.desc = decodeURI(article.desc)
    })
    next();
});

module.exports = mongoose.model('Article', ArticleSchema, 'article');