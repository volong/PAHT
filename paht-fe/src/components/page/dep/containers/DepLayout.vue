<template>
  <a-layout id="components-layout-demo-custom-trigger">
    <a-layout-sider v-model="collapsed" :trigger="null" collapsible>
      <div class="logo" />

      <a-menu theme="dark" mode="inline" :default-selected-keys="['1']">
        <!-- <a-menu-item>
          <router-link to="/admin/dashboard" exact></router-link>
          <a-icon type="dashboard" />

          <span>Thống kê</span>
        </a-menu-item> -->

        <a-sub-menu key="sub1">
          <span slot="title"><a-icon type="edit" /><span>Bài viết</span></span>
          <a-menu-item key="1">
            <router-link to="/dep/processing" exact></router-link>

            Đang xử lí
          </a-menu-item>

          <a-menu-item key="2">
            <router-link to="/dep/processed" exact></router-link>
            Đã xử lí
          </a-menu-item>
        </a-sub-menu>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-header style="background: #fff; padding: 0">
        <a-icon
          class="trigger"
          :type="collapsed ? 'menu-unfold' : 'menu-fold'"
          @click="() => (collapsed = !collapsed)"
        />
        <template class="admin-dropdown">
          <a-dropdown>
            <a-button type="link">{{ currentUser.fullname }}</a-button>
            <a-menu slot="overlay">
              <!-- <a-menu-item
                ><a href=""> <a-icon type="user" /> Thông tin </a>
              </a-menu-item>
              <a-menu-item
                ><a href="">
                  <a-icon type="setting" />
                  Tài khoản
                </a>
              </a-menu-item> -->

              <a-menu-item
                ><a @click="logOut">
                  <a-icon type="logout" />
                  Đăng xuất
                </a>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </template>
      </a-layout-header>
      <a-layout-content
        :style="{
          margin: '24px 16px',
          padding: '24px',
          background: '#fff',
          minHeight: '700px',
        }"
      >
        <router-view />
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script>
export default {
  name: "DepLayout",
  data() {
    return {
      collapsed: false,
      menu: [
        {
          path: "/admin/deps",
          title: "Đơn vị",
          icon: "property-safety",
        },
        {
          path: "/admin/fields",
          title: "Lĩnh vực",
          icon: "book",
        },
      ],
    };
  },
  computed: {
    currentUser() {
      return this.$store.state.depAuth.dep;
    },
  },

  methods: {
    logOut() {
      this.$store.dispatch("depAuth/logout");
      this.$router.push("/dep/login");
    },
  },

  mounted() {
    if (!this.currentUser) {
      this.$router.push("/dep/login");
    }
  },
};
</script>

<style>
#components-layout-demo-custom-trigger .trigger {
  font-size: 18px;
  line-height: 64px;
  padding: 0 24px;
  cursor: pointer;
  transition: color 0.3s;
}

#components-layout-demo-custom-trigger .trigger:hover {
  color: #1890ff;
}

#components-layout-demo-custom-trigger .logo {
  height: 32px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px;
}
</style>