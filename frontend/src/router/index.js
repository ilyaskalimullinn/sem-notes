import {createRouter, createWebHistory} from "vue-router";
import LoginView from "../views/LoginView.vue";
import HomeView from "../views/HomeView.vue";
import RegisterView from "../views/RegisterView.vue";
import LogoutView from "../views/LogoutView.vue";
import {useUserStore} from "../stores/userStore.js";
import NoteEditorView from "../views/NoteEditorView.vue";

const routes = [
  {
    path: "/login",
    name: "Login",
    component: LoginView,
    meta: {
      requiresGuest: true
    }
  },
  {
    path: "/",
    name: "Home",
    component: HomeView
  },
  {
    path: "/register",
    name: "Register",
    component: RegisterView,
    meta: {
      requiresGuest: true
    }
  },
  {
    path: "/logout",
    name: "Logout",
    component: LogoutView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/note/new",
    name: "NewNote",
    component: NoteEditorView,
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
