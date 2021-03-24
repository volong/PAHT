<template>
  <div>
    <div class="logo"></div>
    <a-menu theme="dark" mode="horizontal" :style="{ lineHeight: '64px' }">
      <a-menu-item key="1">
        <router-link to="/" exact></router-link>Trang chủ
      </a-menu-item>
      <a-menu-item key="2">
        <router-link to="/create" exact></router-link> Cổng góp ý
      </a-menu-item>
      <a-menu-item key="3" v-if="!visbleUser">
        <router-link to="/login" exact></router-link> Đăng nhập
      </a-menu-item>
      <a-menu-item key="4" v-if="visbleUser">
        <router-link to="/profile" exact>
          <a-avatar :src="userObj.avatar" />
          {{ userObj.fullname }}
        </router-link>
      </a-menu-item>
    </a-menu>
  </div>
</template>
<script>
var userObj = new Object();
var visbleUser;
export default {
  name: "UserNavbar",
  data() {
    return {
      userObj,
      visbleUser,
    };
  },
  computed: {
    currentUser() {
      return this.$store.state.userAuth.user;
    },
    loggedIn() {
      return this.$store.state.userAuth.status.loggedIn;
    },
  },

  created() {
    if (this.loggedIn) {
      this.visbleUser = true;
      this.userObj = this.$store.state.userAuth.user;
    } else {
      this.visbleUser = false;
    }
  },
};
</script>
<style scoped>
#components-layout-demo-fixed .logo {
  width: 120px;
  height: 31px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px 24px 16px 0;
  float: left;
}
</style>