import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './assets/js/enums';
// 全局引入Ant-design-vue
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';
import * as Icons from '@ant-design/icons-vue';
import axios from "axios";

// 把 App.vue 填充到 /public/index.html中id为app的元素中
const app = createApp(App);
app.use(Antd).use(store).use(router).mount('#app');


// 全局使用图标组件
const icons = Icons;
for(const i in icons){
    app.component(i, icons[i]);
}

/**
 * axios拦截器
 */
// 拦截request
axios.interceptors.request.use(function (config) {
    console.log('请求参数：', config);
    return config;
}, error => {
    return Promise.reject(error);
});
//拦截response
axios.interceptors.response.use(function (response) {
    console.log('返回结果：', response);
    return response;
}, error => {
    console.log('返回错误：', error);
    return Promise.reject(error);
});

axios.defaults.baseURL = process.env.VUE_APP_SERVER;
console.log('环境：', process.env.NODE_ENV);
console.log('服务端：', process.env.VUE_APP_SERVER);