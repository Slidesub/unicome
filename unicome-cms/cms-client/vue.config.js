module.exports = {
  devServer: {
    index: 'index.html',
    historyApiFallback: {
      rewrites: [
        { from: /\/admin/, to: '/admin.html' },
        { from: /\/blog/, to: '/blog.html' },
        { from: /\//, to: '/index.html' }
      ]
    },
    proxy: 'http://127.0.0.1:3000'
  },
  pages: {
    index: {
      entry: 'src/main.js',
      template: 'public/index.html',
      filename: 'index.html',
      title: 'INDEX',
      chunks: ['chunk-vendors', 'chunk-common', 'index'],
      inject: true
    },
    admin: {
      entry: 'src/modules/admin/main.js',
      template: 'public/admin.html',
      filename: 'admin.html',
      title: 'ADMIN',
      chunks: ['chunk-vendors', 'chunk-common', 'admin'],
      inject: true
    },
    blog: {
      entry: 'src/modules/blog/main.js',
      template: 'public/blog.html',
      filename: 'blog.html',
      title: 'BLOG',
      chunks: ['chunk-vendors', 'chunk-common', 'blog'],
      inject: true
    }
  }
}
