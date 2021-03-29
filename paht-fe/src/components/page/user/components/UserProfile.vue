<template>
  <div>
    <a-tabs default-active-key="1" tab-position="left">
      <!-- <a-tab-pane key="1" tab="Thông báo">No Content </a-tab-pane>
      <a-tab-pane key="2" tab="Lịch sử"> No Content </a-tab-pane> -->
      <a-tab-pane key="1" tab="Thông tin">
        <user-profile-info />
      </a-tab-pane>

      <!-- <a-tab-pane key="2" tab="Lịch sử">
        <user-profile-info />
      </a-tab-pane> -->

      <!-- <a-tab-pane key="4" tab="Đăng xuất"> </a-tab-pane> -->
    </a-tabs>
    <div>
      <a-button type="primary" @click="logOut">Đăng xuất</a-button>
    </div>
  </div>
</template>
<script>
import UserProfileInfo from "./UserProfileInfo.vue";
export default {
  components: { UserProfileInfo },
  name: "UserProfile",
  data() {
    return {};
  },
  computed: {
    currentUser() {
      return this.$store.state.userAuth.user;
    },
  },
  methods: {
    logOut() {
      this.$store.dispatch("userAuth/logout");
      location.reload();
    },
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push("/login");
    }
  },
};
</script>