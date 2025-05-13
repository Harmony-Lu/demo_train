<template>
  <a-row class="login">
    <a-col :span="8" :offset="8" class="login-main">
      <h1 style="text-align: center"> <rocket-two-tone />12306网上售票系统 </h1>
      <a-form
        :model="loginForm"
        name="basic"
        autocomplete="off"
      >
      <a-form-item
          label=""
          name="mobile"
          :rules="[{ required: true, message: '请输入手机号码!' }]"
      >
        <a-input v-model:value="loginForm.mobile" />
      </a-form-item>

      <a-form-item
          label=""
          name="code"
          :rules="[{require:true, message:'请输入验证码！'}]"
      >
        <a-input v-model:value="loginForm.code">
          <template #addonAfter>
            <a @click="sendCode">获取验证码</a>
          </template>
        </a-input>
      </a-form-item>

      <a-form-item>
        <a-button type="primary" block @click="login">登录</a-button>
      </a-form-item>
    </a-form>
    </a-col>
  </a-row>
</template>

<script >
import { defineComponent, reactive } from 'vue';
import { notification } from 'ant-design-vue';
import { useRouter } from 'vue-router'
import store from "@/store";
// 前后端交互组件
import axios from 'axios';

export default defineComponent({
  name: "login-view",
  setup() {
    const router = useRouter();

    const loginForm = reactive({
      mobile: '13000000000',
      code: '',
    });

    const sendCode = () => {
      console.log("sendCode to backend!")
      axios.post("/member/member/send-code", {
        mobile: loginForm.mobile
      }).then(response => {
        let data = response.data;
        if (data.success) {
          notification.success({ description: '发送验证码成功！' });
          loginForm.code = "8888";
        } else {
          notification.error({ description: data.message });
        }
      });
    };

    const login = () => {
      console.log("login!")
      axios.post("/member/member/login", loginForm).then((response) => {
        let data = response.data;
        if (data.success) {
          notification.success({ description: '登录成功！' });
          // 登录成功，跳到控台主页
          router.push("/welcome");
          store.commit("setMember", data.content);
        } else {
          notification.error({ description: data.message });
        }
      })
    };

    return {
      loginForm,
      sendCode,
      login
    };
  },
});
</script>

<style>

</style>