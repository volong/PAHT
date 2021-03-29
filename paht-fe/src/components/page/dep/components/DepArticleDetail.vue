<template>
  <!-- Buttons default template -->
  <div>
    <div class="button">
      <dep-article-detail-hanlder />
    </div>
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
          <span>
            {{ articleObj.title }}
          </span>
        </a-descriptions-item>
      </a-descriptions>

      <a-descriptions>
        <a-descriptions-item label="Trạng thái">
          <span>
            {{ articleObj.status.status_name }}
          </span>
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
          <span>
            {{ articleObj.field.field_name }}
          </span>
        </a-descriptions-item>
      </a-descriptions>

      <a-descriptions>
        <a-descriptions-item label="Nội dung"> </a-descriptions-item>
      </a-descriptions>

      <a-descriptions>
        <a-descriptions-item>
          <span class="content-post">{{ articleObj.content }}</span>
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
  </div>
</template>

<script>
import ArticleService from "@/service/ArticleService.js";
import FileArticleService from "@/service/FileArticleService.js";
import DepArticleDetailHanlder from "./DepArticleDetailHanlder.vue";

var articleObj = new Object();
var currentArticle_id;
var filesArticleVisible = 1;
var imageInfos = [];
var imageParams = [];
var currentDate;
export default {
  components: { DepArticleDetailHanlder },
  name: "DepArticleDetail",
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
      currentArticle_id,
      filesArticleVisible,
      filesArticleList: [],
      handleVisibale: false,
      visible: false,
      previewVisible: false,
      previewImage: "",
      currentDate,
    };
  },

  methods: {
    // open handle form
    onHandle() {
      this.handleVisibale = true;
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
  },
};
</script>

<style lang="sass" scoped>
.button
    padding-bottom: 2rem

.image-container
    text-align: center

.content-post
    white-space: pre-wrap

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