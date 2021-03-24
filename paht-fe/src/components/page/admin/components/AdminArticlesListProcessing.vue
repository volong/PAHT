<template>
  <div>
    <div class="search-button">
      <a-input
        placeholder="Nhập từ khoá tìm kiếm"
        widy
        v-model="keyword"
        allow-clear
        style="width: 250px"
        @change="onChangeSearch"
      />
      <br />
    </div>

    <div>
      <a-table
        bordered
        :data-source="articles"
        :columns="columns"
        :pagination="false"
        rowKey="article_id"
      >
        <p slot="expandedRowRender" slot-scope="record" style="margin: 0">
          <span class="span">{{ record.title }}</span>

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
        v-if="visiblePagin"
        :total="totalPage"
        :page-size.sync="this.pageSize"
        :default-current="pageSize"
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
      pageSize: 9,
      status_id: 2,
      offset,
      visiblePagin: true,
      currentStatus_id,
      tableVisble,
      pageIndex,
      totalPage,
      keyword,
      statuses,

      sort: 0,
      loading: false,

      columns: [
        {
          dataIndex: "title",
          width: "35%",
          ellipsis: true,
          scopedSlots: { customRender: "textTitle" },
          slots: { title: "customTitle" },
        },
        {
          title: "Địa điểm",
          dataIndex: "location",
          width: "30%",
          ellipsis: true,
        },
        {
          dataIndex: "field.field_name",
          width: "13%",
          slots: { title: "customField" },
          ellipsis: true,
        },
        {
          title: "Ngày đăng",
          dataIndex: "dateofpost",
          width: "15%",

          ellipsis: true,
        },
      ],
    };
  },
  methods: {
    onChangePage(result) {
      this.$router.push("/admin/page=" + result);
      this.onLoadPage();
    },

    detailPage(article_id) {
      this.$router.push("/admin/article/" + article_id);
    },

    onChangeSearch() {
      if (this.keyword == "") {
        this.getAllArticles();
      } else {
        this.searchByKeyword();
      }
    },

    getAllArticlesASC() {
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
      this.visiblePagin = true;
      this.getOFFSET();
      this.getAllArticlesASC();
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

    getTotalPage() {
      ArticleService.getSumArticles(this.status_id)
        .then((response) => {
          this.totalPage = response.data;
        })
        .catch((e) => {});
    },

    getOFFSET() {
      if (this.pageIndex === 1) {
        this.pageIndex = this.offset - 1;
      } else {
        this.pageIndex = (this.offset - 1) * this.pageSize;
      }
    },

    searchByKeyword() {
      this.visiblePagin = false;
      ArticleService.search(2, this.keyword)
        .then((response) => {
          this.articles = response.data;
          console.log("obj", response.data);
        })
        .catch((e) => {});
    },

    getPageIndex() {
      if (this.$route.params.pageIndex === undefined) {
        this.offset = 1;
      } else {
        this.offset = +this.$route.params.pageIndex;
      }
    },
    onLoadPage() {
      this.getPageIndex();
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
.span {
  color: black;
  padding-bottom: 2px;
}
</style>