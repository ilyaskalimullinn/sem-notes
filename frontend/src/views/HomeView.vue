<template>
  <BaseContainer>
    <h1>Welcome to Notes
    <span v-if="this.isAuthenticated">, {{user.fullName}}</span>
    </h1>
    <ul v-if="!this.isAuthenticated">
      <li>
        <router-link :to="{name: 'Login'}">Login</router-link>
      </li>
      <li>
        <router-link :to="{name: 'Register'}">Register</router-link>
      </li>
    </ul>
    <ul v-else>
      <li>
        <router-link :to="{name: 'Logout'}">Logout</router-link>
      </li>
      <li>
        <router-link :to="{name: 'NewNote'}">Create note</router-link>
      </li>
    </ul>
  </BaseContainer>
</template>

<script>
import BaseContainer from "../containers/BaseContainer.vue";
import {useUserStore} from "../stores/userStore.js";
import {mapGetters, mapState} from "pinia";

export default {
  name: "Home",
  components: {BaseContainer},
  computed: {
    ...mapState(useUserStore, ["user", "isAuthenticated"])
  }
}
</script>

<style scoped>

</style>
