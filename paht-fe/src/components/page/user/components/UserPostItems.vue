<template @scroll="handleScroll">
  <div>
    <a-card title="Danh sách Góp ý - Phản ánh" :bordered="false">
      <!-- <div slot="extra" class="filter-container">
        <a @click="changeListType(true)"><a-icon type="appstore" /></a>
        <a @click="changeListType(false)"><a-icon type="profile" /></a>
      </div> -->
      <user-post-items-card v-if="listType === true"></user-post-items-card>
      <user-post-items-list v-if="listType === false"></user-post-items-list>
    </a-card>
  </div>
</template>

<script>
import ArticleService from "@/service/ArticleService.js";
import UserPostItemsCard from "./UserPostItemsCard";
import UserPostItemsList from "./UserPostItemsList";

const articles = [];
const listPostItem = [];

export default {
  components: { UserPostItemsCard, UserPostItemsList },
  name: "UserPostItems",

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
      listType: false,
    };
  },
  methods: {},
};
</script>
<style scoped>
.content {
  width: 700px;
  white-space: nowrap;
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
.filter-container {
  text-align: right;
  font-size: 25px;
}
</style>