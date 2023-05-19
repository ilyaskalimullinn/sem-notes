<template>
  <BaseContainer>
    <h1>Welcome to Notes</h1>
    <h2 v-if="user">{{user.fullName}}</h2>
    <br />
    <ul>
      <li>
        <router-link :to="{name: 'Login'}">Login</router-link>
      </li>
      <li>
        <router-link :to="{name: 'Register'}">Register</router-link>
      </li>
    </ul>
  </BaseContainer>
</template>

<script>
import BaseContainer from "../containers/BaseContainer.vue";
import {useUserStore} from "../stores/userStore.js";
import {mapState} from "pinia";

export default {
  name: "Home",
  components: {BaseContainer},
  computed: {
    ...mapState(useUserStore, ['user'])
  }
}
</script>

<style scoped>

</style>
