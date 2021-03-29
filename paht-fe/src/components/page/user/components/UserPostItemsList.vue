<template>
  <div class="postitem-container">
    <a-list
      class="item"
      item-layout="vertical"
      size="large"
      :pagination="false"
      v-for="article in listPostItem"
      :key="article.article_id"
    >
      <a-list-item>
        <img slot="extra" width="272" alt="logo" :src="article.avatar" />

        <div class="status-tag">
          <a-tag :color="article.status.status_color"
            >{{ article.status.status_name }}
          </a-tag>
        </div>

        <div class="field-tag">
          <a-tag
            color=" #ff4d4d
"
            >{{ article.field.field_name }}
          </a-tag>
        </div>

        <div>
          <a-list-item-meta :description="article.dateofpost">
            <a slot="title" @click="pageDetail(article.article_id)">{{
              article.title
            }}</a>
            <a-avatar slot="avatar" :src="article.user.avatar" />
          </a-list-item-meta>
        </div>
        <div class="content">
          {{ article.content }}
        </div>
      </a-list-item>
      <a-descriptions></a-descriptions>
    </a-list>

    <div class="loading-container">
      <a-spin :spinning="loadMoreSpinning" tip="Đang tải...">
        <a-button @click="loadMore" type="default">Xem thêm.. </a-button>
      </a-spin>
    </div>
  </div>
</template>


<script>
import ArticleService from "@/service/ArticleService.js";

const articles = [];
const listPostItem = [];

export default {
  name: "UserPostItems-List",

  data() {
    return {
      articles,
      listPostItem: [],
      busy: false,
      pageIndex: 0,
      page: 1,
      loadMoreSpinning: false,
      pageSize: 9,
      images: [],
    };
  },
  methods: {
    getListArticlesChecked(index) {
      this.loadMoreSpinning = true;
      ArticleService.findAllChecked(index)
        .then((response) => {
          this.articles = response.data;
          this.listPostItem = this.listPostItem.concat(this.articles);
          this.loadMoreSpinning = false;
        })
        .catch((error) => {
          this.$message.warning("Lỗi hệ thống, vui lòng thử lại sau");
          this.loadMoreSpinning = false;
        });
    },

    pageDetail(article_id) {
      this.$router.push("/article/" + article_id);
    },

    loadMore() {
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
.content {
  width: 800px;
  height: 85px;
  overflow: hidden;
  text-overflow: ellipsis;
}

.info {
  padding-top: 5px;
}
.info-label {
  font-weight: bold;
  color: #808080;
  padding-right: 4px;
}
.loading-container {
  text-align: center;
}

.status-tag {
  position: absolute;
  top: 6px;
  right: -8px;
}

.field-tag {
  position: absolute;
  top: 29px;
  right: -8px;
}
.info-date {
  padding-left: 10px;
  font-size: 13px;
}

.item {
  height: 250px;
  overflow: hidden;
  text-overflow: ellipsis;
}
</style>