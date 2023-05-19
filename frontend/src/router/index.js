import {createRouter, createWebHistory} from "vue-router";
import Login from "../views/Login.vue";
import Home from "../views/Home.vue";
import Register from "../views/Register.vue";
import Logout from "../views/Logout.vue";

const routes = [
  {
    path: "/login",
    name: "Login",
    component: Login
  },
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/register",
    name: "Register",
    component: Register
  },
  {
    path: "/logout",
    name: "Logout",
    component: Logout
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})
export default router
