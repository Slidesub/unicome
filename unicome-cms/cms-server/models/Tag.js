const mongoose = require('mongoose')
const Schema = mongoose.Schema

const TagSchema = new Schema({
    title: {
        type: String,
        unique: false,
        require: false
    },
    desc: {
        type: String,
        unique: false,
        require: false
    }
});

TagSchema.post('findOne', function (tag, next) {
    tag.title = decodeURI(tag.title)
    tag.desc = decodeURI(tag.desc)
    next();
});

TagSchema.post('find', function (tags, next) {
    tags.forEach(tag => {
        tag.title = decodeURI(tag.title)
        tag.desc = decodeURI(tag.desc)
    })
    next();
});


module.exports = mongoose.model('Tag', TagSchema, 'tag');