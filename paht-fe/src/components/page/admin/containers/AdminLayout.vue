<template>
  <a-layout id="components-layout-demo-custom-trigger">
    <a-layout-sider v-model="collapsed" :trigger="null" collapsible>
      <div class="logo" />

      <a-menu theme="dark" mode="vertical" :default-selected-keys="['1']">
        <a-sub-menu key="sub1">
          <span slot="title"><a-icon type="edit" /><span>Bài viết</span></span>
          <a-menu-item key="1">
            <router-link to="/admin" exact></router-link>
            Bài mới
          </a-menu-item>
          <a-menu-item key="2">
            <router-link to="/admin/processing" exact></router-link>

            Đang xử lí
          </a-menu-item>

          <a-menu-item key="3">
            <router-link to="/admin/processed" exact></router-link>
            Đã xử lí
          </a-menu-item>

          <!-- <a-menu-item key="4">
            <router-link to="/admin/deleted" exact></router-link>
            Bài không hợp lệ
          </a-menu-item> -->
        </a-sub-menu>

        <a-menu-item v-for="i in menu" :key="i.key">
          <router-link :to="i.path" exact>
            <a-icon :type="i.icon" />
            <span>{{ i.title }}</span>
          </router-link>
        </a-menu-item>

        <a-menu-item @click="logOut">
          <a-icon type="logout" />
          <span>Đăng xuất</span>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>

    <a-layout>
      <a-layout-header style="background: #fff; padding: 0">
        <a-icon
          class="trigger"
          :type="collapsed ? 'menu-unfold' : 'menu-fold'"
          @click="() => (collapsed = !collapsed)"
        />
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
  name: "AdminLayout",
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
        // {
        //   path: "/admin/users",
        //   title: "Người dùng",
        //   icon: "team",
        // },
      ],
    };
  },
  computed: {
    currentUser() {
      return this.$store.state.adminAuth.admin;
    },
  },

  methods: {
    logOut() {
      this.$store.dispatch("adminAuth/logout");
      this.$router.push("/");
    },
  },

  created() {
    if (!this.currentUser) {
      this.$router.push("/admin/login");
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
.admin-drop {
  padding-left: 500px;
}
</style>