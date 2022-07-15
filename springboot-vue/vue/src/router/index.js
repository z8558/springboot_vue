import { createRouter, createWebHistory } from 'vue-router'
import Layout from "@/layout/Layout";

const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect:"/login",//重定向
    children: [
      {
        path: 'user',
        name: 'User',
        component: () => import("@/views/User")
      },
      {
        path: 'person',
        name: 'Person',
        component: () => import("@/views/Person")
      },
      {
        path: '/book',
        name: 'Book',
        component: () => import("@/views/Book")
      },
      {
        path: '/news',
        name: 'News',
        component: () => import("@/views/News")
      },
    ],
  },

  {
    path: '/login',
    name: 'Login',
    component: () => import("@/views/Login")
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import("@/views/Register")
  },

]


const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
})

//取出session里的数据
let userJson = JSON.parse(sessionStorage.getItem(""))
if (!userJson){
    router.push("/login")
}

export default router
