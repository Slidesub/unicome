module.exports = {
  pwa: {
    name: 'unicome',
    msTileColor: '#2C3E50'
  },
  devServer: {
    index: 'index.html',
    historyApiFallback: {
        rewrites: [
            { from: /\/admin/, to: '/admin.html' },
            { from: /\/blog/, to: '/blog.html' },
            { from: /\//, to: '/index.html' }
        ]
    }
},
  pages: {
    index: {
        entry: 'src/main.ts',
        template: 'public/index.html',
        filename: 'index.html',
        title: 'INDEX',
        chunks: ['chunk-vendors', 'chunk-common', 'index'],
        inject: true
    },
    admin: {
        entry: 'src/module/admin/main.ts',
        template: 'public/admin.html',
        filename: 'admin.html',
        title: 'ADMIN',
        chunks: ['chunk-vendors', 'chunk-common', 'admin'],
        inject: true
    },
    blog: {
        entry: 'src/module/blog/main.ts',
        template: 'public/blog.html',
        filename: 'blog.html',
        title: 'BLOG',
        chunks: ['chunk-vendors', 'chunk-common', 'blog'],
        inject: true
    }
  }
}