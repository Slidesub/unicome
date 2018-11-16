const Koa = require('koa')
const app = new Koa()
// const views = require('koa-views')
const json = require('koa-json')
const onerror = require('koa-onerror')
const bodyparser = require('koa-bodyparser')
const logger = require('koa-logger')

const mongoose = require('mongoose')
const index = require('./routes/index')
const database = require('./config').mongoose.database

const historyApiFallback  = require('koa2-connect-history-api-fallback')
const path = require('path')
const distPath = path.resolve(__dirname, '../dist')

// error handler
onerror(app)

// middlewares
app.use(bodyparser({
  enableTypes:['json', 'form', 'text']
}))
app.use(json())
app.use(logger())
app.use(require('koa-static')(__dirname + '/public'))

// SAP
app.use(require('koa-static')(distPath))
app.use(
  historyApiFallback ({
    index: path.join(distPath, 'index.html'),
    whiteList: ['/api'],
    rewrites: [
      { from: /\//, to: path.join(distPath, 'index.html') },
      { from: /\/admin/, to: path.join(distPath, 'admin.html') },
      { from: /\/blog/, to: path.join(distPath, 'blog.html') }
    ]
  })
)

// app.use(views(__dirname + '/views', {
//   extension: 'pug'
// }))

// app.use(views(__dirname + '/views', {
//   map: {
//     html: 'nunjucks'
//   }
// }))

// logger
app.use(async (ctx, next) => {
  const start = new Date()
  await next()
  const ms = new Date() - start
  console.log(`${ctx.method} ${ctx.url} - ${ms}ms`)
})

// mongoose
mongoose.connect(database, { useNewUrlParser: true, useCreateIndex: true })

// routes
app.use(index.routes(), index.allowedMethods())

// error-handling
app.on('error', (err, ctx) => {
  console.error('server error', err, ctx)
});

module.exports = app
