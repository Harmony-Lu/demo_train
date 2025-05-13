<template>
  <a-layout-header class="header">
    <div class="logo" />
    <a-menu
        v-model:selectedKeys="selectedKeys1"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="1">nav 11</a-menu-item>
      <a-menu-item key="2">nav 22</a-menu-item>
      <a-menu-item key="3">nav 33</a-menu-item>
    </a-menu>
  </a-layout-header>
</template>

<script>
import {defineComponent, reactive, ref} from 'vue';

export default defineComponent({
  name: "the-header-view",
  setup() {

    return {
      selectedKeys1 : ref(['2'])
    };
  },
});
</script>

<style scoped>

</style>