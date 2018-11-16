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
    tags: {
        type: [ Schema.Types.ObjectId ],
        unique: false,
        require: false
    }
});

module.exports = mongoose.model('Article', ArticleSchema, 'article');