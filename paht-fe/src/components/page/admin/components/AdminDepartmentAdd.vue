<template>
  <a-button type="primary" @click="showModal">
    <a-icon type="plus-circle" /> Tạo mới
    <a-modal v-model="visible" title="Tạo Đơn vị xử lí" @ok="handleOk">
      <div>
        <a-form class="form" :form="form">
          <a-form-item v-bind="formItemLayout">
            <span slot="label"> Tên Đơn vị </span>
            <a-input v-model="depObj.fullname" />
          </a-form-item>
          <a-form-item v-bind="formItemLayout">
            <span slot="label"> Tài khoản </span>
            <a-input v-model="depObj.username" />
          </a-form-item>
          <a-form-item v-bind="formItemLayout" label="E-mail">
            <a-input v-model="depObj.email" />
          </a-form-item>
          <a-form-item v-bind="formItemLayout" label="Mật khẩu" has-feedback>
            <a-input
              v-model="depObj.password"
              v-decorator="[
                'password',
                {
                  rules: [
                    {
                      required: true,
                      message: 'Please input your password!',
                    },
                    {
                      validator: validateToNextPassword,
                    },
                  ],
                },
              ]"
              type="password"
            />
          </a-form-item>
          <a-form-item
            v-bind="formItemLayout"
            label="Xác nhận mật khẩu"
            has-feedback
          >
            <a-input
              v-decorator="[
                'confirm',
                {
                  rules: [
                    {
                      required: true,
                      message: 'Please confirm your password!',
                    },
                    {
                      validator: compareToFirstPassword,
                    },
                  ],
                },
              ]"
              type="password"
              @blur="handleConfirmBlur"
            />
          </a-form-item>
        </a-form>
      </div>
    </a-modal>
  </a-button>
</template>


<script>
import DepAuthService from "@/service/DepAuthService.js";

const residences = [];

var visible;
var depObj = new Object();
export default {
  data() {
    return {
      selectedFile: undefined,
      visbleAvatar: false,
      depObj,
      confirmDirty: false,
      residences,
      visible: false,
      autoCompleteResult: [],
      formItemLayout: {
        labelCol: {
          xs: { span: 24 },
          sm: { span: 8 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
      },
      tailFormItemLayout: {
        wrapperCol: {
          xs: {
            span: 24,
            offset: 0,
          },
          sm: {
            span: 16,
            offset: 8,
          },
        },
      },
    };
  },
  beforeCreate() {
    this.form = this.$form.createForm(this, { name: "register" });
  },
  methods: {
    showModal() {
      this.visible = true;
    },
    handleSubmit(e) {
      e.preventDefault();
      this.form.validateFieldsAndScroll((err, values) => {
        if (!err) {
          console.log("Received values of form: ", values);
        }
      });
    },
    handleConfirmBlur(e) {
      const value = e.target.value;
      this.confirmDirty = this.confirmDirty || !!value;
    },
    compareToFirstPassword(rule, value, callback) {
      const form = this.form;
      if (value && value !== form.getFieldValue("password")) {
        callback("Two passwords that you enter is inconsistent!");
      } else {
        callback();
      }
    },
    validateToNextPassword(rule, value, callback) {
      const form = this.form;
      if (value && this.confirmDirty) {
        form.validateFields(["confirm"], { force: true });
      }
      callback();
    },
    handleWebsiteChange(value) {
      let autoCompleteResult;
      if (!value) {
        autoCompleteResult = [];
      } else {
        autoCompleteResult = [".com", ".org", ".net"].map(
          (domain) => `${value}${domain}`
        );
      }
      this.autoCompleteResult = autoCompleteResult;
    },

    handleOk() {
      this.registerSubmit();
    },

    registerSubmit() {
      DepAuthService.register(this.depObj)
        .then((response) => {
          console.log(response.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style scoped>
input {
  width: 300px;
}

.avatar {
  padding-top: 10px;
  text-align: center;
}

.upload {
  padding-top: 20px;
}

.button-container {
  padding: 50px 0px 0 50px;
}
</style>