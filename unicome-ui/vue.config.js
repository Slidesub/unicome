const path = require('path');

module.exports = {
    chainWebpack: config => {
        config.module.rules.delete('svg');
        config.module
        .rule('svg-sprite-loader')
        .test(/\.svg$/)
        .include
        .add(path.resolve(__dirname, 'src/assets/svg'))
        .end()
        .use('svg-sprite-loader')
        .loader('svg-sprite-loader')
        .options({
            symbolId: 'icon-[name]'
        })
    }
}