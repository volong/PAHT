<template>
  <div>
    <a-descriptions :title="articleObj.title" class="content"> </a-descriptions>
    <!-- <a-descriptions>
      <a-descriptions-item>
        <span class="span-content-post">Trạng thái: </span>
        {{ articleObj.status.status_name }}
      </a-descriptions-item>
    </a-descriptions> -->

    <!-- <a-descriptions>
      <a-descriptions-item>
        <span class="span-content-post">Lĩnh vực: </span>
        {{ articleObj.field.field_name }}
      </a-descriptions-item>
    </a-descriptions> -->

    <a-descriptions>
      <a-descriptions-item>
        <span class="span-content-post">Ngày phản ánh: </span>
        {{ articleObj.dateofpost }}
      </a-descriptions-item>
    </a-descriptions>

    <a-descriptions>
      <a-descriptions-item>
        <span class="span-content-post">Địa điểm diễn ra: </span>
        {{ articleObj.location }}
      </a-descriptions-item>
    </a-descriptions>

    <a-descriptions>
      <a-descriptions-item>
        <span class="span-content-post">Nội dung phản ánh:</span>
      </a-descriptions-item></a-descriptions
    >

    <a-descriptions>
      <a-descriptions-item>
        <p class="content-post-span">{{ articleObj.content }}</p>
      </a-descriptions-item></a-descriptions
    >
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

    <div class="feedback">
      <div class="title-feedback">
        Trả lời của
        <span class="span-title-feedback">{{ feedbackObj.respondent }} </span>
      </div>
      <div class="content-feedback">
        <span> {{ feedbackObj.content }}</span>
      </div>

      <div class="date-feedback">
        <label>Ngày phản hồi: </label>
        <span>{{ feedbackObj.dateoffb }} </span>
      </div>
    </div>
  </div>
</template>

<script>
import ArticleService from "@/service/ArticleService.js";
import FileArticleService from "@/service/FileArticleService.js";
import FeedbackService from "@/service/FeedbackService.js";

var articleObj = new Object();
var feedbackObj = new Object();
var currentArticle_id;
var filesArticleVisible;

export default {
  name: "UserPostItemDetail",
  data() {
    return {
      filesArticleList: [],
      filesArticleVisible,
      articleObj,
      feedbackObj,
      currentArticle_id,
    };
  },

  methods: {
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

    getFeedback() {
      FeedbackService.findByArticleId(currentArticle_id)
        .then((response) => {
          this.feedbackObj = response.data;
        })
        .catch((e) => {
          this.$message.warning("Không tải được nội dung phản hồi");
        });
    },
  },

  mounted() {
    currentArticle_id = this.$route.params.article_id;
    this.getDetailArticle();
    this.getListFileArticle();
    this.getFeedback();
  },
};
</script>
<style scoped>
.span-content-post {
  font-weight: bold;
  color: #595959;
}
.content-post-span {
  white-space: pre-wrap;
  margin-left: 2rem;
}
.feedback {
  border: 1px solid #91d5ff;
  background-color: #e6f7ff;
  margin-left: 150px;
  margin-right: 150px;
  padding: 30px;
}

.span-title-feedback {
  font-weight: bold;
  color: black;
}

.content-feedback {
  padding-top: 20px;
}

.date-feedback {
  white-space: pre-wrap;
}

.title-feedback {
  text-align: center;
}

.date-feedback {
  padding-top: 20px;
  text-align: right;
}

label {
  color: black;
}

.image-container {
  text-align: center;
}

img {
  padding: 20px;
  width: 25%;
}
</style>