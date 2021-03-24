<template>
  <router-view />
</template>

<script>
import Vue from "vue";
import Antd from "ant-design-vue";
import "ant-design-vue/dist/antd.css";
Vue.use(Antd);
Vue.config.productionTip = false;
export default {
  name: "App",
};
</script>

