<template>
  <a-layout id="components-layout-demo-top-side-2">

    <the-header-view></the-header-view>

    <a-layout>

      <the-sider-view></the-sider-view>

      <a-layout-content
          :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }">
        <router-view></router-view>
<!--          所有会员总数：{{count}}-->
      </a-layout-content>

    </a-layout>
  </a-layout>
</template>
<script >

import { defineComponent, ref } from 'vue';
import TheHeaderView from "@/components/the-header";
import TheSiderView from "@/components/the-sider";
import axios from "axios";
import {notification} from "ant-design-vue";
export default defineComponent({
  components: {
    TheSiderView,
    TheHeaderView,
  },
  setup() {
    // // ref 声明基本的数据类型  reactive 声明对象或对象数组
    // const count = ref();
    // axios.get("/member/member/count").then( response => {
    //   let data = response.data;
    //   if( data.success ){
    //     count.value = data.content;
    //   }else{
    //     notification.error( {description: data.message});
    //   }
    // });
    // return {
    //   count
    // };
    return {};
  },
});

</script>
<style scoped>
#components-layout-demo-top-side-2 .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side-2 .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}
</style>