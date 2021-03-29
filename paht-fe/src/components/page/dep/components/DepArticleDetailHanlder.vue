<template>
  <div class="drawer-container">
    <a-button icon="form" type="primary" @click="onHandle"> Xử lí</a-button>
    <div>
      <a-drawer
        title="Xử lí bài viết"
        :width="720"
        :visible="handleVisible"
        :body-style="{ paddingBottom: '80px' }"
        @close="closeHandle"
      >
        <a-form layout="vertical" hide-required-mark>
          <a-row :gutter="24">
            <a-col :span="24">
              <a-form-item label="Trạng thái:">
                <a-select
                  style="width: 250px"
                  v-model="articleObj.status.status_id"
                >
                  <a-select-option
                    v-for="status in statuses"
                    :key="status.status_id"
                    :value="status.status_id"
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
                  placeholder="Nội dung phản hồi"
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
import FeedbackService from "@/service/FeedbackService.js";
import ArticleService from "@/service/ArticleService.js";
import StatusService from "@/service/StatusService.js";
import FieldService from "@/service/FieldService.js";
import DepartmentService from "@/service/DepartmentService.js";

var currentArticle_id;
var articleObj = new Object();
var fbObj = new Object();
var handleVisible;
var deps;
var fields;
var statuses = [];

export default {
  name: "AdminArticleHanlder",
  data() {
    return {
      fbObj,
      articleObj,
      currentArticle_id,
      handleVisible,
      deps,
      fields,
      statuses,
    };
  },
  computed: {
    currentUser() {
      return this.$store.state.depAuth.dep;
    },
  },
  methods: {
    onHandle() {
      this.handleVisible = true;
    },

    onChangeDep(value) {
      this.deps.forEach((element) => {
        if (element.id === value) {
          this.fbObj.content = `Đã tiếp nhận và chuyển thông tin phản ánh cho ${element.fullname} xử lý.`;
        }
      });
    },
    closeHandle() {
      this.handleVisible = false;
    },
    saveHandle() {
      this.updateArticle();
    },

    // get Article object
    getDetailArticle() {
      ArticleService.findById(this.currentArticle_id)
        .then((response) => {
          this.articleObj = response.data;
        })
        .catch((e) => {
          this.$message.warning("Lỗi hệ thống, vui lòng thử lại sau");
        });
    },

    // get Statuses list
    getStatusesList() {
      this.statuses = [
        { status_id: 2, status_name: "Đang xử lí" },
        { status_id: 3, status_name: "Đã xử lí" },
      ];
    },

    // get Feedback object
    getFeedback() {
      FeedbackService.findByArticleId(this.currentArticle_id)
        .then((response) => {
          this.fbObj = response.data;
        })
        .catch((e) => {
          this.$message.warning("Lỗi hệ thống, vui lòng thử lại sau");
        });
    },

    updateArticle() {
      var date = new Date(Date.now());
      var currentDate =
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
      data.append("title", this.articleObj.title),
        data.append("status_id", this.articleObj.status.status_id),
        data.append("field_id", this.articleObj.field.field_id),
        data.append("content", this.fbObj.content);
      data.append("dateoffb", currentDate),
        data.append("dep_id", this.articleObj.dep.id),
        data.append("respondent", this.currentUser.fullname),
        data.append("article_id", this.currentArticle_id);

      ArticleService.update(data)
        .then((response) => {
          this.$message.success("Thay đổi bài đăng thành công");
        })
        .catch((error) => {
          this.$message.warning("Lỗi");
        });
    },

    saveFeedback() {
      var date = new Date(Date.now());
      var currentDate =
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
        data.append("id", this.fbObj.user.id),
        data.append("respondent", this.currentUser.fullname),
        data.append("article_id", this.currentArticle_id);

      FeedbackService.update(data)
        .then((response) => {
          this.$message.success("Đã lưu nội dung xử lí");
          this.handleVisible = false;
        })
        .catch((error) => {
          this.$message.warning(
            "Lưu nội dung xử lí thất bại, vui lòng thử lại!"
          );
        });
    },
  },
  mounted() {
    this.currentArticle_id = this.$route.params.id;
    this.getDetailArticle();
    this.getFeedback();
    this.getStatusesList();
  },
};
</script>
<style scoped>
.drawer-container {
  display: inline;
}
</style>