const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  devServer : {
    proxy : {
      '/member' : {
        // 后端服务器地址
        target : 'http://localhost:8000',
        changeOrigin : true
      }
    }
  }

})
