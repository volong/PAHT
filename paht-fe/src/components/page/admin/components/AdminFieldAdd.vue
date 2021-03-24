<template>
  <a-button type="primary" @click="showModal">
    <a-icon type="plus-circle" /> Tạo mới
    <a-modal v-model="visible" title="Tạo lĩnh vực" @ok="handleOk">
      <a-form layout="vertical" hide-required-mark>
        <a-form-item label="Lĩnh vực:">
          <a-input
            v-model="field.field_name"
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
  </a-button>
</template>
<script>
import FieldService from "@/service/FieldService.js";
export default {
  name: "AdminFieldAdd",
  data() {
    return {
      visible: false,
      field: {
        field_name: "",
      },
    };
  },
  methods: {
    showModal() {
      this.visible = true;
    },

    handleOk() {
      this.saveField();
    },

    saveField: function () {
      var params = {
        field_name: this.field.field_name,
        is_delete: 0,
      };
      FieldService.create(params)
        .then((response) => {
          this.$message.success("Tạo lĩnh vực thành công");
          this.visible = false;
          this.$root.$refs.Field.getListFields(0);
        })
        .catch((error) => {
          this.$message.warning("Tạo lĩnh vực thất bại, vui lòng thử lại");
        });
    },
  },
};
</script>
<style scoped>
</style>