import {createRouter, createWebHistory} from "vue-router";
import LoginView from "../views/LoginView.vue";
import HomeView from "../views/HomeView.vue";
import RegisterView from "../views/RegisterView.vue";
import LogoutView from "../views/LogoutView.vue";
import {useUserStore} from "../stores/userStore.js";
import NoteEditorView from "../views/NoteEditorView.vue";
import NotFound404 from "../views/errors/NotFound404.vue";
import NoteListView from "../views/NoteListView.vue";
import {useNoteStore} from "../stores/noteStore.js";
import NoteCreateView from "../views/NoteCreateView.vue";

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
    path: '/notes',
    children: [
      {
        path: "",
        name: "NoteList",
        component: NoteListView,
        meta: {
          requiresAuth: true
        }
      },
      {
        path: ':id',
        name: 'NoteEdit',
        component: NoteEditorView,
        meta: {
          requiresAuth: true
        },
      },
      {
        path: 'new',
        name: 'NoteCreate',
        component: NoteCreateView,
        meta: {
          requiresAuth: true
        },
      }
    ]
  },
  {
    path: '/error',
    redirect: {name: 'NotFound404'},
    children: [
      {
        path: 'not-found',
        name: 'NotFound404',
        component: NotFound404
      },
    ]
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: {name: 'NotFound404'}
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from) => {
  useNoteStore().$reset();

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
