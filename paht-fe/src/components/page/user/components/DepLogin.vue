<template>
  <a-card
    title="Quản trị viên"
    :bordered="false"
    :style="{ minHeight: '550px' }"
  >
    <div>
      <a-form
        id="components-form-demo-normal-login"
        :form="form"
        class="login-form"
        @submit.prevent="handleLogin"
      >
        <a-form-item>
          <a-input class="input" v-model="dep.username" placeholder="Tài khoản">
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
            v-model="dep.password"
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
          <a-button type="primary" html-type="submit" class="login-form-button">
            Đăng nhập
          </a-button>
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
import Dep from "@/models/Dep";
function hasErrors(fieldsError) {
  return Object.keys(fieldsError).some((field) => fieldsError[field]);
}
export default {
  name: "DepLogin",
  data() {
    return {
      dep: new Dep("", ""),
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
      return this.$store.state.depAuth.status.loggedIn;
    },
  },

  created() {
    if (this.loggedIn) {
      this.$router.push("/dep");
    }
  },

  methods: {
    handleLogin() {
      this.$store.dispatch("depAuth/login", this.dep).then(
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