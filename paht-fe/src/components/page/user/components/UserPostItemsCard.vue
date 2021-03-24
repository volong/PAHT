<template>
  <div class="card-container">
    <a-row :gutter="16">
      <a-col
        :span="8"
        v-for="article in listPostItem"
        :key="article.article_id"
      >
        <a-card hoverable class="card">
          <img slot="cover" alt="example" :src="article.avatar" />
          <div class="top-right">
            <a-tag :color="article.status.status_color"
              >{{ article.status.status_name }}
            </a-tag>
          </div>

          <a-card-meta :title="article.title"> </a-card-meta>

          <div class="info-card-container">
            <div>
              <span>{{ article.content }}</span>
            </div>
          </div>
        </a-card>
      </a-col>
    </a-row>
  </div>
</template>

<script>
import ArticleService from "@/service/ArticleService.js";

const articles = [];
const listPostItem = [];

export default {
  name: "UserPostItems-Card",

  data() {
    return {
      articles,
      listPostItem: [],
      busy: false,
      pageIndex: 0,
      loadMore: true,
      page: 1,
      pageSize: 9,
      images: [],
    };
  },
  methods: {
    getListArticlesChecked(index) {
      ArticleService.findAllChecked(index)
        .then((response) => {
          this.articles = response.data;
          this.listPostItem = this.listPostItem.concat(this.articles);
        })
        .catch((error) => {
          this.$message.warning("Lỗi hệ thống, vui lòng thử lại sau");
        });
    },

    pageDetail(article_id) {
      this.$router.push("/article/" + article_id);
    },

    handleInfiniteOnLoad() {},

    getImagesInfo() {
      this.pageIndex = this.pageIndex + 9;
      this.getListArticlesChecked(this.pageIndex);
    },
  },

  mounted() {
    this.getListArticlesChecked(this.pageIndex);
  },
};
</script>

<style scoped>
.card-container {
  padding-top: 20px;
}

.info-card-container {
  padding-top: 5px;
}
.card {
  width: 350px;
  height: 410px;
  overflow: hidden;
  text-overflow: ellipsis;
}
.content {
  white-space: nowrap;
  overflow: hidden;
  word-break: break-word;
  text-overflow: ellipsis;
}

.info {
  padding-top: 5px;
}

.info-date {
  padding-left: 10px;
  font-size: 13px;
}

.loading-container {
  text-align: center;
}

img {
  padding: 15px 15px 0px 15px;
}
.top-right {
  position: absolute;
  top: 6px;
  right: 6px;
}
</style>