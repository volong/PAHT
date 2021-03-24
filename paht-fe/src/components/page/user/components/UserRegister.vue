<template>
  <a-card
    class="container"
    title="Tạo tài khoản"
    :bordered="false"
    :style="{ minHeight: '580px' }"
  >
    <a-form class="form" :form="form" @submit="handleSubmit">
      <a-form-item v-bind="formItemLayout">
        <span slot="label"> Họ và tên </span>
        <a-input
          v-decorator="[
            'fullname',
            {
              rules: [
                {
                  required: true,
                  message: 'Vui lòng nhập họ tên',
                },
              ],
            },
          ]"
        />
      </a-form-item>
      <a-form-item v-bind="formItemLayout">
        <span slot="label"> Tên tài khoản </span>
        <a-input
          v-decorator="[
            'username',
            {
              rules: [
                {
                  required: true,
                  message: 'Vui lòng nhập tài khoản',
                },
              ],
            },
          ]"
        />
      </a-form-item>
      <a-form-item v-bind="formItemLayout" label="E-mail">
        <a-input
          v-decorator="[
            'email',
            {
              rules: [
                {
                  type: 'email',
                  message: 'Email không hợp lệ!',
                },
                {
                  required: true,
                  message: 'Vui lòng nhập email E-mail!',
                },
              ],
            },
          ]"
        />
      </a-form-item>
      <a-form-item v-bind="formItemLayout" label="Mật khẩu" has-feedback>
        <a-input
          v-decorator="[
            'password',
            {
              rules: [
                {
                  required: true,
                  message: 'Vui lòng nhập mật khẩu!',
                },
              ],
            },
          ]"
          type="password"
        />
      </a-form-item>

      <div class="avatar">
        <a-spin tip="Đang tải..." :spinning="uploading">
          <a-avatar v-if="!visbleAvatar" :size="100" icon="user" />
          <a-avatar v-if="visbleAvatar" :size="100" :src="urlAvatar" />

          <div></div>

          <div class="upload">
            <a-button id="buttonImage" @click="inputFileClick">
              <a-icon type="upload" />Tải ảnh đại diện</a-button
            >
            <input
              type="file"
              id="inputImage"
              @change="selectFile($event)"
              hidden
            />
          </div>
        </a-spin>
      </div>

      <a-form-item v-bind="tailFormItemLayout" class="button-register">
        <a-button type="primary" html-type="submit"> Đăng kí </a-button>
      </a-form-item>
    </a-form>
  </a-card>
</template>

<script>
import UploadService from "@/service/UploadService.js";
import AuthService from "@/service/AuthService.js";

const residences = [];

var urlAvatar;
var userObj = new Object();
var visbleAvatar;
export default {
  data() {
    return {
      selectedFile: undefined,
      visbleAvatar: false,
      userObj,
      urlAvatar,
      uploading: false,
      confirmDirty: false,
      residences,
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
    handleSubmit(e) {
      e.preventDefault();
      this.form.validateFieldsAndScroll((err, values) => {
        if (!err) {
          this.userObj = {
            fullname: values.fullname,
            username: values.username,
            email: values.email,
            password: values.password,
            avatar: this.urlAvatar,
          };
          this.register();
        }
      });
    },

    handleConfirmBlur(e) {
      const value = e.target.value;
      this.confirmDirty = this.confirmDirty || !!value;
    },

    selectFile(event) {
      this.selectedFile = event.target.files;
      this.upload(this.selectedFile[0]);
    },

    upload(file) {
      this.uploading = true;
      UploadService.upload(file)
        .then((response) => {
          this.visbleAvatar = true;
          this.urlAvatar = response.data.url;
          this.uploading = false;
        })

        .catch((err) => {
          this.$message.warning("Lỗi tải file");
          this.uploading = false;
        });
    },

    register() {
      AuthService.register(this.userObj)
        .then((response) => {
          this.$message.success("Đăng kí thành công");
        })
        .catch((error) => {
          var message =
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
          this.$message.warning(message.message);
        });
    },

    inputFileClick() {
      document.getElementById("inputImage").click();
      return null;
    },
  },
};
</script>

<style scoped>
input {
  width: 300px;
}

.form {
  padding-left: 50px;
}
.avatar {
  padding-top: 10px;
  padding-right: 70px;
  text-align: center;
}

.upload {
  padding-top: 20px;
}

.button-register {
  padding-top: 10px;
}
</style>