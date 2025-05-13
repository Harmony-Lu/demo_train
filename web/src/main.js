import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
// 全局引入Ant-design-vue
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';
import * as Icons from '@ant-design/icons-vue';

// 把 App.vue 填充到 /public/index.html中id为app的元素中
const app = createApp(App);
app.use(Antd).use(store).use(router).mount('#app');

// 全局使用图标组件
const icons = Icons;
for(const i in icons){
    app.component(i, icons[i]);
}
