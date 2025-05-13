import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

// 把 App.vue 填充到 /public/index.html中id为app的元素中
createApp(App).use(store).use(router).mount('#app')
