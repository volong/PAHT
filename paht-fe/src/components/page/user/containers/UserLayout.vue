<template>
  <a-layout id="components-layout-demo-fixed">
    <a-layout-header :style="{ position: 'fixed', zIndex: 1, width: '100%' }">
      <user-navbar></user-navbar>
    </a-layout-header>

    <a-layout-content style="padding: 100px 100px 0px 100px" collapsible>
      <div :style="{ background: '#fff', minHeight: '620px' }">
        <div style="padding: 30px">
          <router-view />
        </div>
      </div>
    </a-layout-content>

    <a-layout-footer
      :style="{
        textAlign: 'center',
        minHeight: '20px',
        bottom: '0',
      }"
    >
      Trung tâm Góp ý - Phản ánh
    </a-layout-footer>
  </a-layout>
</template>

<style scoped>
</style>
<script>
import UserNavbar from "../components/UserNavbar.vue";

export default {
  components: { UserNavbar },
  name: "UserHome",

  mounted() {},
};
</script>