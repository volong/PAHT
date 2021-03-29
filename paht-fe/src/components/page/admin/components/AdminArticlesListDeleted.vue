<template>
  <div>
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
            {{ record.content }}
          </p>
          <span slot="action" slot-scope="text, record">
            <!-- Edit onClick() -->
            <a @click="detailPage(record.article_id)">Xem</a>

            <a-divider type="vertical" />
            <!-- Delete onClick() -->
            <a-popconfirm
              title="Bạn có chắc muốn xoá bài đăng này"
              ok-text="Yes"
              cancel-text="No"
              @confirm="confirmDelete(record.article_id)"
            >
              <a>Xoá</a>
            </a-popconfirm>
          </span>
        </a-table>
      </div>

      <div class="pagination">
        <a-pagination
          v-if="visblePagin"
          :total="totalPage"
          :page-size.sync="this.pageSize"
          :default-current="pageSize"
          @change="onChangePage"
        />
      </div>
    </div>
  </div>
</template>

<script>
import ArticleService from "@/service/ArticleService.js";
import StatusService from "@/service/StatusService.js";

const articles = [];
var statuses = [];
var tableVisble;
var currentStatus_id;
var pageIndex;
var totalPage;
var pageSize;
var keyword;
var offset;

export default {
  name: "AdminArticlesListNew",
  data() {
    return {
      data: [],
      pagination: {},
      articles,
      pageSize: 9,
      status_id: 1,
      offset,
      currentStatus_id,
      tableVisble,
      pageIndex,
      visblePagin: true,
      totalPage,
      keyword,
      statuses,

      sort: 0,
      loading: false,

      columns: [
        {
          title: "Nội dung bài đăng",
          dataIndex: "content",
          width: "35%",
          ellipsis: true,
        },
        {
          title: "Địa điểm",
          dataIndex: "location",
          width: "30%",
          ellipsis: true,
        },
        {
          title: "Ngày đăng",
          dataIndex: "dateofpost",
          width: "14%",

          ellipsis: true,
        },
        {
          key: "action",
          width: "10%",
          scopedSlots: { customRender: "action" },
        },
      ],
    };
  },
  methods: {
    onChangePage(result) {
      this.$router.push("/admin/page=" + result);
      this.onLoadPage();
    },

    onChangeSearch() {
      if (this.keyword == "") {
        this.getAllArticles();
      } else {
        this.searchByKeyword();
      }
    },

    getAllArticlesASC() {
      this.visblePagin = true;
      ArticleService.findArticlesIsDeleted(this.pageSize, this.pageIndex)
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

    getListStatus() {
      StatusService.findAll()
        .then((response) => {
          this.statuses = response.data;
        })
        .catch((e) => {
          this.$message.warning("Lỗi khi lấy danh sách trạng thái");
        });
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
        this.pageIndex = this.offset - 1;
      } else {
        this.pageIndex = (this.offset - 1) * this.pageSize;
      }
    },

    searchByKeyword() {
      this.visblePagin = false;
      ArticleService.search(1, this.keyword)
        .then((response) => {
          this.articles = response.data;
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
</style>