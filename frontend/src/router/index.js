import {createRouter, createWebHistory} from "vue-router";
import Login from "../views/Login.vue";
import Home from "../views/Home.vue";
import Register from "../views/Register.vue";
import Logout from "../views/Logout.vue";
import {useUserStore} from "../stores/userStore.js";

const routes = [
  {
    path: "/login",
    name: "Login",
    component: Login,
    meta: {
      requiresGuest: true
    }
  },
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/register",
    name: "Register",
    component: Register,
    meta: {
      requiresGuest: true
    }
  },
  {
    path: "/logout",
    name: "Logout",
    component: Logout,
    meta: {
      requiresAuth: true
    }
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from) => {
  const logged = useUserStore().user !== null;

  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!logged ) {
      return {name: "Login"}
    }
  } else if (to.matched.some(record => record.meta.requiresGuest)) {
    if (!logged) {
      return {name: "Home"}
    }
  }
})
export default router
