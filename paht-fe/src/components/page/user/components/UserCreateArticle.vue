<template>
  <a-card
    title="Cổng Góp ý - Phản ánh"
    :bordered="false"
    :style="{ minHeight: '550px' }"
  >
    <div v-if="!visibleForm">
      <user-create-article-message></user-create-article-message>
    </div>

    <div v-if="visibleForm">
      <div>
        <div>
          <a-textarea
            v-model="articleObj.content"
            v-decorator="[
              'content',
              {
                rules: [
                  {
                    required: true,
                    message: 'Vui lòng nhập nội dung bài viết',
                  },
                ],
              },
            ]"
            placeholder="Nhập nôi dung bài viết"
          ></a-textarea>
        </div>
        <div class="input">
          <a-input v-model="articleObj.location" placeholder="Địa điểm diễn ra">
            <a-icon
              slot="prefix"
              type="environment"
              style="color: rgba(0, 0, 0, 0.25)"
            />
          </a-input>
        </div>

        <div class="upload">
          <a-row>
            <a-col :span="12">
              <a-button id="buttonImage" @click="inputFileClick">
                <a-icon type="upload" />Hình ảnh</a-button
              >

              <input
                type="file"
                id="inputImage"
                multiple
                @change="selectFile($event)"
                hidden
              />
            </a-col>
            <a-col :span="12" :style="{ textAlign: 'right' }">
              <a-spin :spinning="spinSubmit" tip="Đang gửi...">
                <a-button
                  @click="sendPost"
                  class="sumit"
                  type="primary"
                  icon="check"
                  :disabled="
                    articleObj.content === '' || articleObj.location === ''
                  "
                >
                  Gửi
                </a-button>
              </a-spin>
            </a-col>
          </a-row>
        </div>
      </div>

      <div class="clearfix">
        <a-spin :spinning="spinning" tip="Đang tải ảnh...">
          <a-upload
            list-type="picture-card"
            :default-file-list="fileList"
            @preview="handlePreview"
          >
          </a-upload>
          <a-modal
            :visible="previewVisible"
            :footer="null"
            @cancel="handleCancel"
          >
            <img alt="example" style="width: 100%" :src="previewImage" />
          </a-modal>
        </a-spin>
      </div>
    </div>
  </a-card>
</template>

<script>
import UploadService from "@/service/UploadService.js";
import ArticleService from "@/service/ArticleService.js";
import FeedbackService from "@/service/FeedbackService.js";
import FileArticleService from "@/service/FileArticleService.js";
import UserCreateArticleMessage from "./UserCreateArticleMessage.vue";

var currentFile = new Object();
var currenArticle = new Object();
var imageInfos = [];
var imageParams = [];
var visibleForm;
var currentArticle_id;
export default {
  components: { UserCreateArticleMessage },
  name: "UserCreateArticle",
  data() {
    return {
      selectedFiles: undefined,
      message: "",
      currentArticle_id,
      currentArticleID: "",
      fileInfos: [],
      currenArticle,
      imageInfos,
      visibleForm,
      imageParams,
      spinning: false,
      spinSubmit: false,
      previewVisible: false,
      previewImage: "",
      fileList: [],
      articleObj: {
        content: "",
        location: "",
      },
    };
  },
  computed: {
    // kiểm tra trạng thái loggedIn đã được đăng nhập hay chưa
    loggedIn() {
      return this.$store.state.userAuth.status.loggedIn;
    },

    // Trả về thông tin của tài khoản đã đăng nhâpk
    currentUser() {
      return this.$store.state.userAuth.user;
    },
  },
  methods: {
    // Show form Đăng bài viết nếu currentUser đã tồn tại
    checkFormCreate() {
      if (this.currentUser) {
        this.visibleForm = true;
      } else {
        this.visibleForm = false;
      }
    },

    // method lưu bài viết bằng cách gọi create() trong ArticleService
    savePost() {
      this.spinSubmit = true;
      var date = new Date(Date.now());

      // Trả về date với format HH:MM DD-MM-YYYY
      var saveDate =
        ("0" + date.getHours()).slice(-2) +
        ":" +
        ("0" + date.getMinutes()).slice(-2) +
        " " +
        ("0" + date.getDate()).slice(-2) +
        "-" +
        ("0" + (date.getMonth() + 1)).slice(-2) +
        "-" +
        date.getFullYear();
      var status = {
        status_id: 1,
      };
      var field = {
        field_id: 100,
      };
      var user = {
        id: this.currentUser.id,
      };
      var article = {
        dateofpost: saveDate,
        content: this.articleObj.content,
        location: this.articleObj.location,
        status: status,
        field: field,
        is_delete: 0,
        user: user,
      };

      ArticleService.create(article)
        .then((response) => {
          currentArticle_id = response.data;
          this.saveFileInfo();
          this.$message.success("Bài viết đã gửi thành công");
          this.spinSubmit = !this.spinSubmit;
        })
        .catch((error) => {
          this.spinSubmit = false;
          this.$message.warning("Gửi bài viết thất bại, vui lòng thử lại");
        });
    },

    // Gọi hàm savePost()
    sendPost() {
      this.savePost();
    },

    // method save info của hình ảnh kèm theo trong bài viết
    saveFileInfo() {
      for (let i = 0; i < imageInfos.length; i++) {
        imageInfos[i].article_id = currentArticle_id;
      }
      FileArticleService.create(imageInfos)
        .then((response) => {
          console.log(response.data);
        })
        .catch((error) => {});
    },

    // Đóng trạng thái preview hình ảnh
    handleCancel() {
      this.previewVisible = false;
    },

    // Duyệt mảng selectedFiles và gọi method upload()
    uploadFiles() {
      for (let i = 0; i < this.selectedFiles.length; i++) {
        this.upload(this.selectedFiles[i]);
      }
    },

    // Sự kiện chọn ảnh và giới hạn số ảnh được phép chọn
    selectFile(event) {
      this.selectedFiles = event.target.files;
      if (
        this.selectedFiles.length > 5 ||
        this.fileList.length + this.selectedFiles.length > 5
      ) {
        this.$message.warning("Bạn chỉ có thêm tối đa 5 ảnh");
      } else {
        for (let i = 0; i < this.selectedFiles.length; i++) {
          this.upload(this.selectedFiles[i]);
        }
      }
    },

    // method preview ảnh
    async handlePreview(currentFile) {
      this.previewImage = currentFile.url || currentFile.preview;
      this.previewVisible = true;
    },

    // Gửi 1 ảnh về back-end thông qua RESTful và nhận giá trị reponse
    upload(file) {
      this.spinning = !this.spinning;
      UploadService.upload(file)
        .then((response) => {
          currentFile = {
            uid: response.data.public_id,
            name: response.data.public_id,
            type: response.data.format,
            status: "done",
            url: response.data.url,
          };

          imageInfos.push(currentFile);
          this.fileList.push(currentFile);
          this.spinning = !this.spinning;

          return null;
        })

        .catch((err) => {
          this.$message.warning("Lỗi tải file");
          this.spinning = !this.spinning;
        });
    },

    inputFileClick() {
      document.getElementById("inputImage").click();
      return null;
    },
  },
  mounted() {
    this.checkFormCreate();
  },
};
</script>

<style lang="sass" scoped>
textarea
  width: 100%
  padding-top: 1rem
  background-color: #ffffff
  border-color: #e6e6e6
  outline: none
  border-radius: 4px

.input
  padding-top: 1rem

.clearfix
  padding-top: 1rem

.upload
  margin-top: 1rem
  .button
    padding-top: 0.5rem
    border: none
    height: 100%

.alert-container
  margin-bottom: 20px
  width: 400px
</style>