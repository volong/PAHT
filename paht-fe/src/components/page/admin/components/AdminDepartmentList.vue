<template>
  <div>
    <div class="editable-add-btn">
      <admin-department-add />
      <!-- <a-button type="dafault" @click="getListDepsDeleted" v-if="trash === 0">
        <a-icon type="delete" /> Giỏ rác
      </a-button>

      <a-button type="dafault" @click="getListDepsNotDelete" v-if="trash === 1">
        <a-icon type="close" /> Đóng giỏ rác
      </a-button> -->
    </div>

    <!-- Fields Table -->
    <a-table
      bordered
      :pagination="false"
      :data-source="deps"
      :columns="columns"
      rowKey="dep_id"
    >
      <template slot="name" slot-scope="text, record">
        <editable-cell
          :text="text"
          @change="onCellChange(record.key, 'name', $event)"
        />
      </template>
      <span slot="action" slot-scope="text, record">
        <!-- Edit onClick() -->
        <a v-if="record.is_delete == 0" @click="showEdit(record.dep_id)">Sửa</a>
        <a-divider v-if="record.is_delete == 0" type="vertical" />
        <!-- Delete onClick() -->

        <!-- <a-popconfirm
          v-if="record.is_delete == 0"
          title="Bạn có chắc muốn xoá đơn vị này"
          ok-text="Yes"
          cancel-text="No"
          @confirm="confirmDelete(record.dep_id)"
        >
          <a>Xoá</a>
        </a-popconfirm> -->

        <!-- <a-popconfirm
          v-if="record.is_delete == 1"
          title="Bạn có chắc muốn khôi phục đơn vị này này"
          ok-text="Yes"
          cancel-text="No"
          @confirm="confirmRestore(record.dep_id)"
        >
          <a>Khôi phục</a>
        </a-popconfirm> -->
      </span>
    </a-table>

    <a-modal
      v-model="editVisble"
      title="Chỉnh sửa Đơn vị xử lí"
      @ok="updateDep"
    >
      <a-form layout="vertical" hide-required-mark>
        <a-form-item label="Đơn vị xử lí:">
          <a-input
            v-model="depObj.dep_name"
            v-decorator="[
              'name',
              {
                rules: [{ required: true, message: 'Please enter user name' }],
              },
            ]"
            placeholder="Nhập tên đơn vị"
          />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>
<script>
import DepartmentService from "@/service/DepartmentService.js";
import AdminDepartmentAdd from "./AdminDepartmentAdd.vue";

var currentDepID;
const deps = [];
var depObj = new Object();
var trash = 0;
export default {
  components: { AdminDepartmentAdd },
  name: "AdminDepartmentList",
  data() {
    return {
      editVisble: false,
      currentDepID,
      deps,
      depObj,
      trash,
      columns: [
        {
          title: "Tên Đơn vị",
          dataIndex: "fullname",
          width: "20%",
        },
        {
          title: "Tài khoản",
          dataIndex: "username",
          width: "20%",
        },
        {
          title: "Email",
          dataIndex: "email",
          width: "20%",
        },
      ],
    };
  },
  created() {
    this.$root.$refs.Department = this;
  },

  methods: {
    showEdit(depID) {
      this.getDepById(depID);
      this.editVisble = true;
      this.currentDepID = depID;
    },

    // Close Edit Drawer Method
    closeEdit() {
      this.editVisble = false;
    },
    // Load Fields List data
    getListDeps() {
      DepartmentService.findAll()
        .then((response) => {
          this.deps = response.data;
        })
        .catch((e) => {
          this.$message.warning("Lỗi hệ thống, vui lòng thử lại sau");
        });
    },

    getListDepsNotDelete() {
      this.trash = 0;
      this.getListDeps(0);
    },

    getListDepsDeleted() {
      this.trash = 1;
      this.getListDeps(1);
    },

    confirmDelete(id) {
      DepartmentService.remove(id)
        .then((response) => {
          this.$message.success("Đã xoá đơn vị");
          this.getListDepsNotDelete();
        })
        .catch((error) => {
          this.$message.warning("Xoá đơn vị thất bại, vui lòng thử lại");
          this.getListDepsNotDelete();
        });
    },

    confirmRestore(id) {
      DepartmentService.restore(id)
        .then((response) => {
          this.$message.success("Đã khôi phục");
          this.getListDepsDeleted();
        })
        .catch((error) => {
          this.$message.warning("Khôi phục thất bại, vui lòng thử lại");
          this.getListDepsDeleted();
        });
    },

    getDepById(dep_id) {
      DepartmentService.findById(dep_id)
        .then((response) => {
          this.depObj = response.data;
        })
        .catch((e) => {
          this.$message.warning("Lỗi hệ thống, vui lòng thử lại sau");
        });
    },

    updateDep() {
      var params = {
        dep_id: this.currentDepID,
        dep_name: this.depObj.dep_name,
      };
      DepartmentService.update(params)
        .then((response) => {
          this.$message.success("Cập nhật Đơn vị xử lí thành công");
          this.getListDeps(0);
          this.editVisble = false;
        })
        .catch((error) => {
          this.$message.warning("Cập nhật thất bại, vui lòng thử lại");
          this.getListDeps(0);
        });
    },
  },

  mounted() {
    this.getListDeps();
  },
};
</script>
<style>
.editable-cell {
  position: relative;
}

.editable-cell-input-wrapper,
.editable-cell-text-wrapper {
  padding-right: 24px;
}

.editable-cell-text-wrapper {
  padding: 5px 24px 5px 5px;
}

.editable-cell-icon,
.editable-cell-icon-check {
  position: absolute;
  right: 0;
  width: 20px;
  cursor: pointer;
}

.editable-cell-icon {
  line-height: 18px;
  display: none;
}

.editable-cell-icon-check {
  line-height: 28px;
}

.editable-cell:hover .editable-cell-icon {
  display: inline-block;
}

.editable-cell-icon:hover,
.editable-cell-icon-check:hover {
  color: #108ee9;
}

.editable-add-btn {
  margin-bottom: 8px;
}
</style>