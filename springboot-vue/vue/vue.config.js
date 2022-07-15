const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
    transpileDependencies: true,
    //跨域配置 //
    devServer: { //devServer//设置本地默认端口
        // host: "localhost",
        port: 9000,
        // proxy:'http://localhost:9001'
        // proxy: {
        //     '/api': {
        //         target : 'http://localhost:9001',
        //         ws: true,
        //         changeOrigin: true,
        //         pathRewrite: {
        //             '^/api': '',
        //         },
        //     }
        // }
    }
})
