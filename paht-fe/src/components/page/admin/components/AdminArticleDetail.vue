<template>
  <!-- Buttons default template -->
  <div>
    <div class="button" v-if="this.editVisible === 1">
      <a-button icon="edit" type="primary" @click="onEdit">Sửa</a-button>
      <a-button icon="form" type="primary" @click="onHandle"> Xử lí</a-button>
      <a-button icon="delete" type="dashed">Xoá</a-button>
    </div>

    <!-- Buttons on edit template -->
    <div class="button" v-if="this.editVisible === 0">
      <a-button icon="close" type="default" @click="cancleEdit">Đóng</a-button>

      <a-button icon="check" type="primary" @click="saveEdit">Lưu</a-button>
    </div>

    <!-- Article Detail template -->
    <div>
      <a-descriptions>
        <a-descriptions-item>
          <span>
            Được đăng bởi <a> {{ articleObj.user.fullname }} </a></span
          >
        </a-descriptions-item>
      </a-descriptions>
      <a-descriptions>
        <a-descriptions-item label="Tiêu đề">
          <span v-if="this.editVisible === 1">
            {{ articleObj.title }}
          </span>

          <a-input
            placeholder="Nhập tiêu đề"
            v-model="articleObj.title"
            style="width: 500px"
            v-if="this.editVisible === 0"
          />
        </a-descriptions-item>
      </a-descriptions>

      <a-descriptions>
        <a-descriptions-item label="Trạng thái">
          <span v-if="this.editVisible === 1">
            {{ articleObj.status.status_name }}
          </span>
          <a-select
            style="width: 250px"
            v-if="this.editVisible === 0"
            v-model="articleObj.status.status_id"
          >
            <a-select-option v-for="status in statuses" :key="status.status_id">
              {{ status.status_name }}
            </a-select-option>
          </a-select>
        </a-descriptions-item>
      </a-descriptions>

      <a-descriptions>
        <a-descriptions-item label="Địa điểm diễn ra">
          {{ articleObj.location }}
        </a-descriptions-item>
      </a-descriptions>

      <a-descriptions>
        <a-descriptions-item label="Ngày đăng">
          {{ articleObj.dateofpost }}
        </a-descriptions-item>
      </a-descriptions>

      <a-descriptions>
        <a-descriptions-item label="Lĩnh vực">
          <span v-if="this.editVisible === 1">
            {{ articleObj.field.field_name }}
          </span>

          <a-select
            style="width: 250px"
            v-if="this.editVisible === 0"
            v-model="articleObj.field.field_id"
          >
            <a-select-option v-for="field in fields" :key="field.field_id">
              {{ field.field_name }}
            </a-select-option>
          </a-select>
        </a-descriptions-item>
      </a-descriptions>

      <a-descriptions>
        <a-descriptions-item label="Nội dung">
          {{ articleObj.content }}
        </a-descriptions-item>
      </a-descriptions>

      <a-descriptions>
        <a-descriptions-item label="Ảnh kèm theo"> </a-descriptions-item>
      </a-descriptions>

      <div>
        <div class="image-container">
          <img
            v-for="item in filesArticleList"
            :key="item.uid"
            :src="item.url"
            alt=""
          />
        </div>
      </div>

      <div v-if="filesArticleVisible === 1">
        <p>Không có ảnh kèm theo</p>
      </div>
    </div>

    <!-- Handle form template -->
    <div>
      <a-drawer
        title="Xử lí bài viết"
        :width="720"
        :visible="handleVisibale"
        :body-style="{ paddingBottom: '80px' }"
        @close="closeHandle"
      >
        <a-form layout="vertical" hide-required-mark>
          <a-row :gutter="24">
            <a-col :span="24">
              <a-form-item label="Đơn vị xử lý:">
                <a-select
                  style="width: 250px"
                  v-model="articleObj.status.status_id"
                >
                  <a-select-option
                    v-for="status in statuses"
                    :key="status.status_id"
                  >
                    {{ status.status_name }}
                  </a-select-option>
                </a-select>
              </a-form-item>
            </a-col>
          </a-row>
          <a-row :gutter="16">
            <a-col :span="24">
              <a-form-item label="Nội dung phản hồi:">
                <a-textarea
                  :rows="4"
                  placeholder="Nhập nội dung phản hồi"
                  v-model="fbObj.content"
                />
              </a-form-item>
            </a-col>
          </a-row>
        </a-form>
        <div
          :style="{
            position: 'absolute',
            right: 0,
            bottom: 0,
            width: '100%',
            borderTop: '1px solid #e9e9e9',
            padding: '10px 16px',
            background: '#fff',
            textAlign: 'right',
            zIndex: 1,
          }"
        >
          <a-button :style="{ marginRight: '8px' }" @click="closeHandle">
            Huỷ
          </a-button>
          <a-button type="primary" @click="saveHandle"> Lưu </a-button>
        </div>
      </a-drawer>
    </div>
  </div>
</template>

<script>
import ArticleService from "@/service/ArticleService.js";
import FileArticleService from "@/service/FileArticleService.js";
import FeedbackService from "@/service/FeedbackService.js";
import FieldService from "@/service/FieldService.js";
import AdminArticleEdit from "./AdminArticleEdit.vue";
import StatusService from "@/service/StatusService.js";

var articleObj = new Object();
var fbObj = new Object();
var currentArticle_id;
var filesArticleVisible = 1;
var filesFeedbackVisible = 1;
var editVisible = 1;
var imageInfos = [];
var imageParams = [];
var currentDate;
var currentFb_id;
export default {
  components: { AdminArticleEdit },
  name: "AdminArticleDetail",
  data() {
    return {
      articleObj,
      selectedFiles: undefined,
      fileInfos: [],
      imageInfos,
      filesArticleList: [],
      imageParams,
      spinning: false,
      fileList: [],
      fbObj,
      currentArticle_id,
      filesArticleVisible,
      filesFeedbackVisible,
      filesArticleList: [],
      filesFeedbackList: [],
      fields: [],
      statuses: [],
      editVisible,
      handleVisibale: false,
      visible: false,
      previewVisible: false,
      previewImage: "",
      currentDate,
    };
  },

  methods: {
    // open edit form
    onEdit() {
      this.editVisible = 0;
    },

    saveEdit() {
      this.updateArticle();
      this.editVisible = 1;
    },

    updateArticle() {
      var urlAvatar;
      if (this.filesArticleVisible === 1) {
        urlAvatar =
          "https://res.cloudinary.com/paht/image/upload/v1615438754/mhidxiugfz4dkkyxijp9.jpg";
      } else {
        urlAvatar =
          "https://res.cloudinary.com/paht/image/upload/w_1000,ar_16:9,c_fill,g_auto,e_sharpen/v1615271102/" +
          this.filesArticleList[0].uid +
          "." +
          this.filesArticleList[0].type;
      }
      var data = new FormData();
      data.append("title", this.articleObj.title),
        data.append("avatar", urlAvatar);

      data.append("status_id", this.articleObj.status.status_id),
        data.append("field_id", this.articleObj.field.field_id),
        data.append("article_id", currentArticle_id);

      ArticleService.update(data)
        .then((response) => {
          this.$message.success("Thay đổi bài đăng thành công");
          this.getDetailArticle();
        })
        .catch((error) => {
          this.$message.warning("Lỗi");
        });
    },

    // close edit form
    cancleEdit() {
      this.getDetailArticle();
      this.editVisible = 1;
    },

    // open handle form
    onHandle() {
      this.getFeedback();
      this.handleVisibale = true;
    },

    saveHandle() {
      this.saveFeedback();
    },

    //close handle form
    closeHandle() {
      this.handleVisibale = false;
    },

    handleCancel() {
      this.previewVisible = false;
    },

    async handlePreview(currentFile) {
      this.previewImage = currentFile.url || currentFile.preview;
      this.previewVisible = true;
    },

    // get Article object
    getDetailArticle() {
      ArticleService.findById(currentArticle_id)
        .then((response) => {
          this.articleObj = response.data;
        })
        .catch((e) => {
          this.$message.warning("Lỗi hệ thống, vui lòng thử lại sau");
        });
    },

    // get Feedback object
    getFeedback() {
      FeedbackService.findByArticleId(currentArticle_id)
        .then((response) => {
          this.fbObj = response.data;
          currentFb_id = response.data.fb_id;
        })
        .catch((e) => {
          this.$message.warning("Lỗi hệ thống, vui lòng thử lại sau");
        });
    },

    saveFeedback() {
      var date = new Date(Date.now());
      currentDate =
        ("0" + date.getHours()).slice(-2) +
        ":" +
        ("0" + date.getMinutes()).slice(-2) +
        " " +
        ("0" + date.getDate()).slice(-2) +
        "-" +
        ("0" + (date.getMonth() + 1)).slice(-2) +
        "-" +
        date.getFullYear();
      var data = new FormData();
      data.append("content", this.fbObj.content);
      data.append("dateoffb", currentDate),
        data.append("article_id", currentArticle_id);

      FeedbackService.update(data)
        .then((response) => {
          this.$message.success("Đã lưu nội dung xử lí");
          this.handleVisibale = false;
        })
        .catch((error) => {
          this.$message.warning(
            "Lưu nội dung xử lí thất bại, vui lòng thử lại!"
          );
        });
    },

    // get Fields list
    getFieldsList() {
      FieldService.findAll(0)
        .then((response) => {
          this.fields = response.data;
        })
        .catch((error) => {});
    },

    // get Statuses list
    getStatusesList() {
      StatusService.findAll().then((response) => {
        this.statuses = response.data;
      });
    },

    // get list files of article
    getListFileArticle() {
      FileArticleService.findByArticleId(currentArticle_id)
        .then((response) => {
          response.data.forEach((element) => {
            this.filesArticleList.push(element);
          });
          if (this.filesArticleList.length === 0) {
            this.filesArticleVisible = 1;
          } else {
            this.filesArticleVisible = 0;
          }
        })
        .catch((error) => {});
    },
  },

  mounted() {
    currentArticle_id = this.$route.params.id;
    this.getDetailArticle();
    this.getListFileArticle();
    this.getFieldsList();
    this.getStatusesList();
  },
};
</script>

<style lang="sass" scoped>
.button
  padding-bottom: 2rem

.image-container
  text-align: center

img
  padding: 20px
  width: 25%

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

.icon
  margin-left: 6px
  font-size: 18px
</style>