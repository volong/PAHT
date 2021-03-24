<template>
  <div>
    <div>
      <span>Thống kê theo:</span>
      <a-select default-value="lucy" style="width: 120px">
        <a-select-option value="lucy"> Tháng </a-select-option>
        <a-select-option value="disabled"> Năm </a-select-option>
        <a-select-option value="Yiminghe"> Tất cả </a-select-option>
      </a-select>
    </div>
    <br />
    <br />
    <div>
      <a-card title="Thống kê theo lĩnh vực" style="width: 800px">
        <a>
          <a-progress
            :percent="30"
            style="width: 600px"
            strokeColor="orange"
            :format="(percent) => `${percent}% - An toàn giao thông`"
        /></a>
        <a-progress
          :percent="70"
          style="width: 600px"
          status="active"
          :format="(percent) => `${percent}% - Bảo vệ môi trường`"
        />

        <a-progress
          :percent="50"
          style="width: 600px"
          strokeColor="#00e600"
          :format="(percent) => `${percent}% - An toàn thực phẩm`"
        />
        <a-progress
          :percent="50"
          style="width: 600px"
          strokeColor="red"
          :format="(percent) => `${percent}% - Bảo vệ trẻ em`"
        />
      </a-card>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
</style>