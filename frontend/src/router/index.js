import {createRouter, createWebHistory} from "vue-router";
import Login from "../views/Login.vue";
import Home from "../views/Home.vue";
import Register from "../views/Register.vue";
import Logout from "../views/Logout.vue";
import {useUserStore} from "../stores/userStore.js";
import NoteEditor from "../views/NoteEditor.vue";

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
  {
    path: "/note/new",
    name: "NewNote",
    component: NoteEditor,
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
  const logged = useUserStore().isAuthenticated;
  const authRequired = to.meta["requiresAuth"] || false
  const guestRequired = to.meta["requiresGuest"] || false

  if (!logged && authRequired) {
    return {name: "Login"}
  }
  if (logged && guestRequired) {
    return {name: "Home"}
  }
})
export default router
