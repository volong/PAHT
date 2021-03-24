<template>
  <a-card title="Đăng nhập" :bordered="false" :style="{ minHeight: '550px' }">
    <div>
      <a-form
        id="components-form-demo-normal-login"
        :form="form"
        class="login-form"
        @submit.prevent="handleLogin"
      >
        <a-form-item>
          <a-input
            class="input"
            v-model="user.username"
            placeholder="Tài khoản"
          >
            <a-icon
              slot="prefix"
              type="user"
              style="color: rgba(0, 0, 0, 0.25)"
            />
          </a-input>
        </a-form-item>
        <a-form-item>
          <a-input
            class="input"
            v-model="user.password"
            type="password"
            placeholder="Mật khẩu"
          >
            <a-icon
              slot="prefix"
              type="lock"
              style="color: rgba(0, 0, 0, 0.25)"
            />
          </a-input>
        </a-form-item>
        <a-form-item>
          <a-button type="primary" html-type="submit"> Đăng nhập </a-button>
          <br />
          <a href="">
            <router-link to="/register" exact>Tạo tài khoản mới</router-link>
          </a>
        </a-form-item>
      </a-form>
    </div>
  </a-card>
</template>

<style scoped>
/* .input {
  width: 30rem;
} */

.login-form {
  margin: auto;
  width: 50%;
  padding: 10px;
}
</style>
<script>
import User from "@/models/User";
function hasErrors(fieldsError) {
  return Object.keys(fieldsError).some((field) => fieldsError[field]);
}
export default {
  name: "UserLogin",
  data() {
    return {
      user: new User("", ""),
      message: "",
      hasErrors,
      form: this.$form.createForm(this, { name: "horizontal_login" }),
    };
  },
  mounted() {
    // this.$nextTick(() => {
    //   // To disabled submit button at the beginning.
    //   this.form.validateFields();
    // });
  },

  computed: {
    loggedIn() {
      return this.$store.state.userAuth.status.loggedIn;
    },
  },

  created() {
    if (this.loggedIn) {
      this.$router.push("/profile");
    }
  },

  methods: {
    handleLogin() {
      this.$store.dispatch("userAuth/login", this.user).then(
        () => {
          location.reload();
        },
        (error) => {
          this.$message.warning("Tài khoản hoặc mật khẩu không đúng!");
        }
      );
    },
  },
};
</script>