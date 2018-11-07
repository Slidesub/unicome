const mongoose = require('mongoose')
const Schema = mongoose.Schema

const TagSchema = new Schema({
    code: {
        type: String,
        unique: false,
        require: false
    },
    name: {
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

module.exports = mongoose.model('Tag', TagSchema, 'tag');