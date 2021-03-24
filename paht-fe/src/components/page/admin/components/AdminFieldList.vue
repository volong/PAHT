<template>
  <div>
    <div class="editable-add-btn">
      <admin-field-add />
      <a-button type="dafault" @click="getListFieldsDeleted" v-if="trash === 0">
        <a-icon type="delete" /> Giỏ rác
      </a-button>

      <a-button
        type="dafault"
        @click="getListFieldsNotDelete"
        v-if="trash === 1"
      >
        <a-icon type="close" /> Đóng giỏ rác
      </a-button>
    </div>

    <!-- Fields Table -->
    <a-table
      bordered
      :pagination="false"
      :data-source="fields"
      :columns="columns"
      rowKey="field_id"
    >
      <template slot="name" slot-scope="text, record">
        <editable-cell
          :text="text"
          @change="onCellChange(record.key, 'name', $event)"
        />
      </template>
      <span slot="action" slot-scope="text, record">
        <!-- Edit onClick() -->
        <a v-if="record.is_delete == 0" @click="showEdit(record)">Sửa</a>
        <a-divider v-if="record.is_delete == 0" type="vertical" />
        <!-- Delete onClick() -->

        <a-popconfirm
          v-if="record.is_delete == 0"
          title="Bạn có chắc muốn xoá lĩnh vực này"
          ok-text="Yes"
          cancel-text="No"
          @confirm="confirmDelete(record.field_id)"
        >
          <a>Xoá</a>
        </a-popconfirm>

        <a-popconfirm
          v-if="record.is_delete == 1"
          title="Bạn có chắc muốn khôi phục lĩnh vực này"
          ok-text="Yes"
          cancel-text="No"
          @confirm="confirmRestore(record.field_id)"
        >
          <a>Khôi phục</a>
        </a-popconfirm>
      </span>
    </a-table>

    <a-modal v-model="editVisble" title="Chỉnh sửa lĩnh vực" @ok="updateField">
      <a-form layout="vertical" hide-required-mark>
        <a-form-item label="Lĩnh vực:">
          <a-input
            v-model="fieldObj.field_name"
            v-decorator="[
              'name',
              {
                rules: [{ required: true, message: 'Please enter user name' }],
              },
            ]"
            placeholder="Nhập tên lĩnh vực"
          />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>
<script>
import FieldService from "@/service/FieldService.js";
import AdminFieldAdd from "./AdminFieldAdd.vue";

var currentFieldID;
const fields = [];
var fieldObj = new Object();
var trash;
export default {
  components: { AdminFieldAdd },
  name: "AdminFieldList",
  data() {
    return {
      editVisble: false,
      currentFieldID,
      fields,
      fieldObj,
      trash: 0,
      columns: [
        {
          title: "Lĩnh vực",
          dataIndex: "field_name",
          scopedSlots: { customRender: "field_name" },
        },
        {
          title: "Action",
          key: "action",
          width: "10%",

          scopedSlots: { customRender: "action" },
        },
      ],
    };
  },
  created() {
    this.$root.$refs.Field = this;
  },

  methods: {
    showEdit(object) {
      this.editVisble = true;
      this.fieldObj = object;
    },

    // Close Edit Drawer Method
    closeEdit() {
      this.editVisble = false;
    },
    // Load Fields List data
    getListFields(trash) {
      FieldService.findAll(trash)
        .then((response) => {
          console.log(response.data);
          this.fields = response.data;
        })
        .catch((e) => {
          this.$message.warning("Lỗi hệ thống, vui lòng thử lại sau");
        });
    },

    getListFieldsNotDelete() {
      this.trash = 0;
      this.getListFields(0);
    },

    getListFieldsDeleted() {
      this.trash = 1;
      this.getListFields(1);
    },

    confirmDelete(id) {
      FieldService.remove(id)
        .then((response) => {
          this.$message.success("Đã xoá lĩnh vực");
          this.getListFieldsNotDelete();
        })
        .catch((error) => {
          this.$message.warning("Xoá lĩnh vực thất bại, vui lòng thử lại");
          this.getListFieldsNotDelete();
        });
    },

    confirmRestore(id) {
      FieldService.restore(id)
        .then((response) => {
          this.$message.success("Đã khôi phục");
          this.getListFieldsDeleted();
        })
        .catch((error) => {
          this.$message.warning(
            "Khôi phục thất bại thất bại, vui lòng thử lại"
          );
          this.getListFieldsDeleted();
        });
    },

    getFieldById(field_id) {
      FieldService.findById(field_id)
        .then((response) => {
          this.fieldObj = response.data;
        })
        .catch((e) => {
          this.$message.warning("Lỗi hệ thống, vui lòng thử lại sau");
        });
    },

    updateField() {
      var data = new FormData();
      data.append("field_id", this.fieldObj.field_id);
      data.append("field_name", this.fieldObj.field_name);

      FieldService.update(data)
        .then((response) => {
          this.$message.success("Cập nhật Lĩnh vực thành công");
          this.getListFields(0);
          this.editVisble = false;
        })
        .catch((error) => {
          this.$message.warning("Cập nhật thất bại, vui lòng thử lại");
          this.getListFields(0);
        });
    },
  },

  mounted() {
    this.getListFields(this.trash);
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