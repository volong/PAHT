<template>
  <div>
    <div class="button">
      <a-button icon="edit" type="primary" @click="onOpen">Sửa</a-button>
      <a-button icon="form" type="primary" @click="onOpen"> Phản hồi</a-button>
      <a-button icon="delete">Xoá</a-button>
    </div>
    <a-drawer
      title="Xử lí bài đăng"
      :width="720"
      :visible="visible"
      :body-style="{ paddingBottom: '80px' }"
      @close="onClose"
    >
      <a-form :form="form" layout="vertical" hide-required-mark>
        <a-row :gutter="24">
          <a-col :span="24">
            <a-form-item label="Tiêu đề">
              <a-textarea
                placeholder="Nhập tiêu đề"
                :rows="3"
                v-model="articleObj.title"
              >
              </a-textarea>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row :gutter="24">
          <a-col :span="24">
            <a-form-item label="Địa điểm">
              <a-input
                placeholder="Địa điểm diễn ra"
                v-model="articleObj.location"
              >
                <a-icon
                  slot="prefix"
                  type="environment"
                  style="color: rgba(0, 0, 0, 0.25)"
                />
              </a-input>
            </a-form-item>
          </a-col>
        </a-row>
        <a-row :gutter="24">
          <a-col :span="12">
            <a-form-item label="Trạng thái">
              <a-select
                v-model="articleObj.status_id"
                placeholder="Chọn trạng thái"
              >
                <a-select-option
                  v-for="i in statuses"
                  :key="i.status_id"
                  :value="i.status_id"
                >
                  {{ i.status_name }}
                </a-select-option>
              </a-select>
            </a-form-item>
          </a-col>

          <a-col :span="12">
            <a-form-item label="Lĩnh vực">
              <a-select
                v-model="articleObj.field_id"
                placeholder="Chọn lĩnh vực"
              >
                <a-select-option
                  v-for="i in fields"
                  :key="i.field_id"
                  :value="i.field_id"
                >
                  {{ i.field_name }}
                </a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
        </a-row>

        <a-row :gutter="24"> </a-row>
        <a-row :gutter="16">
          <a-col :span="24">
            <a-form-item label="Phản hồi">
              <a-textarea
                :rows="4"
                placeholder="Nhập nội dung phản hồi cho bài đăng"
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
        <a-button :style="{ marginRight: '8px' }" @click="onClose">
          Huỷ
        </a-button>
        <a-button type="primary" @click="onSubmit"> Lưu </a-button>
      </div>
    </a-drawer>
  </div>
</template>
<script>
import ArticleService from "@/service/ArticleService.js";
import FeedbackService from "@/service/FeedbackService.js";
import StatusService from "@/service/StatusService.js";
import FieldService from "@/service/FieldService.js";
import DepartmentService from "@/service/DepartmentService.js";

var currentId;
var articleObj = new Object();
var fbObj = new Object();

var departments = [];
var fields = [];
var statuses = [];

export default {
  name: "AdminArticleEdit",
  data() {
    return {
      form: this.$form.createForm(this),
      visible: false,
      currentId,
      articleObj,
      fbObj,
      departments,
      fields,
      statuses,
    };
  },
  methods: {
    onOpen() {
      this.visible = true;
    },

    onClose() {
      this.visible = false;
    },

    onSubmit() {
      this.updateArticle();
      this.updateFeedback();
      this.getDetailArticle();
      this.getFeedback();
    },

    updateArticle() {
      var params = {
        article_id: currentId,
        title: this.articleObj.title,
        location: this.articleObj.location,
        status_id: this.articleObj.status_id,
        field_id: this.articleObj.field_id,
        dep_id: this.articleObj.dep_id,
      };
      ArticleService.update(params)
        .then((response) => {
          this.$message.success("Cập nhật bài đăng thành công");
          this.getDetailArticle();
        })
        .catch((error) => {
          this.$message.warning("Lỗi khi cập nhật bài đăng");
        });
    },

    updateFeedback() {
      var params = {
        article_id: currentId,
        content: this.fbObj.content,
        dep_id: this.articleObj.dep_id,
      };
      FeedbackService.update(params)
        .then((response) => {
          console.log(response.date);
        })
        .catch((error) => {
          console.log(error);
        });
    },

    getDetailArticle() {
      ArticleService.findById(currentId)
        .then((response) => {
          this.articleObj = response.data;
        })
        .catch((e) => {
          this.$message.warning("Lỗi hệ thống, vui lòng thử lại sau");
        });
    },

    getFeedback() {
      FeedbackService.findByArticleId(currentId)
        .then((response) => {
          this.fbObj = response.data;
        })
        .catch((e) => {
          this.$message.warning("Lỗi hệ thống, vui lòng thử lại sau");
        });
    },

    getListDepartment() {
      DepartmentService.findAll(0)
        .then((response) => {
          this.departments = response.data;
        })
        .catch((e) => {
          this.$message.warning("Lỗi khi lấy danh sách đơn vị xử lí");
        });
    },

    getListField() {
      FieldService.findAll(0)
        .then((response) => {
          this.fields = response.data;
        })
        .catch((e) => {
          this.$message.warning("Lỗi khi lấy danh sách lĩnh vực");
        });
    },

    getListStatus() {
      StatusService.findAll()
        .then((response) => {
          this.statuses = response.data;
        })
        .catch((e) => {
          this.$message.warning("Lỗi khi lấy danh sách trạng thái");
        });
    },
  },
  mounted() {
    currentId = this.$route.params.id;
    this.getDetailArticle();
    this.getFeedback();
    this.getListDepartment();
    this.getListField();
    this.getListStatus();
  },
};
</script>