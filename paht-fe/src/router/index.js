import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      component: () => import("@/components/page/user/containers/UserLayout"),
      children: [
        {
          path: "/",
          component: () => import("@/components/page/user/components/UserPostItems")
        },
        {
          path: "article/:article_id",
          component: () => import("@/components/page/user/components/UserPostItemDetail")
        },
        {
          path: "create",
          component: () => import("@/components/page/user/components/UserCreateArticle")
        },
        {
          path: "login",
          component: () => import("@/components/page/user/components/UserLogin")
        },
        {
          path: "admin/login",
          component: () => import("@/components/page/user/components/AdminLogin"),
        },
        {
          path: "dep/login",
          component: () => import("@/components/page/user/components/DepLogin"),
        },
        {
          path: "register",
          component: () => import("@/components/page/user/components/UserRegister")
        },
        {
          path: "profile",
          component: () => import("@/components/page/user/components/UserProfile")
        }
      ]
    },
    {
      path: '/admin',
      component: () => import("@/components/page/admin/containers/AdminLayout"),
      children: [

        {
          path: "fields",
          component: () => import("@/components/page/admin/components/AdminFieldList"),
        },
        {
          path: "deps",
          component: () => import("@/components/page/admin/components/AdminDepartmentList"),
        },
        {
          path: "users",
          component: () => import("@/components/page/admin/components/AdminUserAccount")
        },
        {
          path: "dashboard",
          component: () => import("@/components/page/admin/components/AdminDashboard"),
        },
        {
          path: "/",
          component: () => import("@/components/page/admin/components/AdminArticlesListNew"),
        },
        {
          path: 'page=:pageIndex',
          component: () => import('@/components/page/admin/components/AdminArticlesListNew'),
        },
        {
          path: 'processing',
          component: () => import("@/components/page/admin/components/AdminArticlesListProcessing")
        },
        {
          path: 'processing/page=:pageIndex',
          component: () => import("@/components/page/admin/components/AdminArticlesListProcessing")
        },

        {
          path: 'processed/page=:pageIndex',
          component: () => import("@/components/page/admin/components/AdminArticlesListProcessed")
        },

        {
          path: 'processed',
          component: () => import("@/components/page/admin/components/AdminArticlesListProcessed")
        },
        {
          path: "deleted",
          component: () => import("@/components/page/admin/components/AdminArticlesListDeleted"),
        },
        {
          path: "article/:id",
          component: () => import("@/components/page/admin/components/AdminArticleDetail")
        },
      ]
    },
    {
      path: '/dep',
      component: () => import("@/components/page/dep/containers/DepLayout"),
      children: [
        {
          path: '/',
          redirect: to => {
            return 'processing';
          }
        },
        {
          path: 'processing',
          component: () => import("@/components/page/dep/components/DepArticlesListProcessing")
        },
        {
          path: 'processed',
          component: () => import("@/components/page/dep/components/DepArticlesListProcessed")
        },
        {
          path: 'processing/page:=pageIndex',
          component: () => import("@/components/page/dep/components/DepArticlesListProcessing")
        },
        {
          path: "article/:id",
          component: () => import("@/components/page/dep/components/DepArticleDetail")
        },
      ]
    },


  ]


})
