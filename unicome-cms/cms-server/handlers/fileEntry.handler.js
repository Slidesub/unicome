const fs = require('fs');
const os = require('os');
const path = require('path');
const FileEntry = require('../models/FileEntry')

class FileEntrytHandler {
    static async upload(ctx) {
        const upload = ctx.request.body.files.file;
        const arr = upload.name.split('.');
        const ext = arr[arr.length - 1];
        const destName = parseInt(Math.random() * 100) + Date.parse(new Date()).toString() + '.' + ext;
        const dest = path.join('public/upload/image', destName);
        const reader = fs.createReadStream(upload.path);
        const stream = fs.createWriteStream(dest);
        reader.pipe(stream);

        const up = {
            name: destName,
            type: upload.type,
            size: upload.size,
            desc: 'upload',
            url: 'http://' + ctx.host + '/upload/image/' + destName,
            path: dest,
        }
        const upObj = await FileEntry.create(up);
        const result = {
            id: upObj.id,
            name: upload.name,
            url: upObj.url
        }
        return { code: 200, msg: 'SUCCESS', data: result}
    }
}

module.exports = UploadHandler;