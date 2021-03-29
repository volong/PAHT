<template>
  <div>
    <!-- <div class="search-button">
      <a-input
        placeholder="Nhập từ khoá tìm kiếm"
        widy
        v-model="keyword"
        allow-clear
        style="width: 250px"
        @change="onChangeSearch"
      />
      <br />
    </div> -->

    <div>
      <a-table
        bordered
        :data-source="articles"
        :columns="columns"
        :pagination="false"
        rowKey="article_id"
      >
        <p slot="expandedRowRender" slot-scope="record" style="margin: 0">
          <span class="span-title">{{ record.title }}</span>
          <br />
          <span class="span-location">• {{ record.location }}</span>

          <br />
          {{ record.content }}
        </p>
        <a
          slot="textTitle"
          slot-scope="text, record"
          @click="detailPage(record.article_id)"
        >
          {{ text }}</a
        >

        <span slot="customTitle"> Tiêu đề</span>
        <span slot="customField">Lĩnh vực </span>
      </a-table>
    </div>

    <div class="pagination">
      <a-pagination
        v-if="visblePagin"
        :total="totalPage"
        :page-size.sync="pageSize"
        :default-current="pageIndex"
        @change="onChangePage"
      />
    </div>
  </div>
</template>

<script>
import ArticleService from "@/service/ArticleService.js";
import StatusService from "@/service/StatusService.js";

const columns = [];
const articles = [];
var statuses = [];
var tableVisble;
var currentStatus_id;
var sort;
var pageIndex;
var visblePagin;
var totalPage;
var pageSize;
var keyword;
var offset;

export default {
  name: "AdminArticlesListProcessing",
  data() {
    return {
      data: [],
      pagination: {},
      articles,
      visblePagin: true,
      pageSize: 9,
      status_id: 2,
      offset,
      visiblePagin: true,
      currentStatus_id,
      tableVisble,
      pageIndex: 1,
      totalPage,
      keyword,
      statuses,

      sort: 0,
      loading: false,

      columns: [
        {
          dataIndex: "title",
          width: "25%",
          ellipsis: true,
          scopedSlots: { customRender: "textTitle" },
          slots: { title: "customTitle" },
        },
        {
          title: "Đơn vị xử lí",
          dataIndex: "dep.fullname",
          width: "13%",
          ellipsis: true,
        },
        {
          dataIndex: "field.field_name",
          width: "10%",
          slots: { title: "customField" },
          ellipsis: true,
        },
        {
          title: "Ngày đăng",
          dataIndex: "dateofpost",
          width: "10%",

          ellipsis: true,
        },
      ],
    };
  },
  methods: {
    onChangePage(result) {
      this.pageIndex = result;
      this.getAllArticles();
    },

    onChangeSearch() {
      if (this.keyword === "") {
        this.getAllArticlesASC();
      } else {
        this.searchByKeyword();
      }
    },

    getAllArticlesASC() {
      this.visblePagin = true;
      ArticleService.findNewArticlesASC(
        this.status_id,
        this.pageSize,
        this.pageIndex
      )
        .then((response) => {
          this.articles = response.data;
        })
        .catch((error) => {
          this.$message.warning("Lỗi hệ thống, vui lòng thử lại sau");
        });
    },

    getAllArticles() {
      this.getOFFSET();
      this.getAllArticlesASC();
    },

    detailPage(article_id) {
      this.$router.push("/admin/article/" + article_id);
    },

    getTotalPage() {
      ArticleService.getSumArticles(this.status_id)
        .then((response) => {
          this.totalPage = response.data;
        })
        .catch((e) => {});
    },

    getOFFSET() {
      if (this.pageIndex === 1) {
        this.pageIndex = this.pageIndex - 1;
      } else {
        this.pageIndex = (this.pageIndex - 1) * this.pageSize;
      }
    },

    onLoadPage() {
      this.getTotalPage();
      this.getAllArticles();
    },
  },

  mounted() {
    this.onLoadPage();
  },
};
</script>
<style scoped>
.option {
  padding-bottom: 10px;
}

.search-button {
  padding-bottom: 10px;
}

.pagination {
  padding-top: 10px;
  text-align: right;
}
.span-title {
  color: black;
  padding-bottom: 2px;
}
.span-location {
  padding-left: 10px;
}
</style>