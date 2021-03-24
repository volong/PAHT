<template>
  <a-descriptions title="Thông tin tài khoản">
    <a-descriptions-item>
      <a-avatar :size="100" :src="userObj.avatar" />
    </a-descriptions-item>

    <br />
    <br />
    <a-descriptions-item label="Họ Tên">
      {{ userObj.fullname }}
    </a-descriptions-item>
    <br />
    <br />
    <a-descriptions-item label="Tài khoản">
      {{ userObj.username }}
    </a-descriptions-item>
    <br />
    <br />
    <a-descriptions-item label="Email">
      {{ userObj.email }}
    </a-descriptions-item>
  </a-descriptions>
</template>
<script>
var userObj = new Object();

export default {
  name: "UserProfileInfo",
  data() {
    return {
      userObj,
    };
  },
  computed: {
    currentUser() {
      return this.$store.state.userAuth.user;
    },
  },

  methods: {
    getUser() {
      this.userObj = this.currentUser;
      console.log("demo", this.currentUser);
    },
  },
  mounted() {
    this.getUser();
  },
};
</script>
<style>
</style>