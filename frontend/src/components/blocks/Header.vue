<template>
  <header class="d-flex flex-wrap justify-content-center border-bottom p-3">
    <h3 class="d-flex align-items-center mb-md-0 me-md-auto ">
      <router-link class="text-dark text-decoration-none" :to="{name: 'Home'}">
        Home
      </router-link>
    </h3>
    <ul class="nav nav-pills d-flex">
      <li class="nav-item">
        <router-link v-for="route in this.getRoutes"
                     class="nav-link d-inline"
                     aria-current="page"
                     :to="{name: route.name}">
          {{route.text}}
        </router-link>
      </li>
    </ul>
  </header>
</template>

<script>
import {mapActions, mapState} from "pinia";
import {useNoteStore} from "../../stores/noteStore.js";
import {useUserStore} from "../../stores/userStore.js";

export default {
  name: "Header",
  data() {
    return {
      routes: [
        {
          name: "Home",
          text: "Home"
        },
        {
          name: "NoteList",
          text: "My Notes",
          authRequired: true
        },
        {
          name: "NoteCreate",
          text: "Create Note",
          authRequired: true
        },
        {
          name: "Login",
          text: "Sign in",
          guestRequired: true,
        },
        {
          name: "Logout",
          text: "Logout",
          authRequired: true
        },
        {
          name: "Register",
          text: "Sign up",
          guestRequired: true,
        }
      ],
      activeRoutes: []
    }
  },
  computed: {
    ...mapState(useUserStore, ["isAuthenticated"]),
    getRoutes() {
      if (this.isAuthenticated) {
        return this.routes.filter(route => !route.guestRequired)
      } else {
        return this.routes.filter(route => !route.authRequired)
      }
    }
  }
}
</script>

<style scoped>

</style>
