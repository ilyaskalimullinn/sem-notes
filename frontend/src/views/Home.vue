<template>
  <BaseContainer>
    <h1>Welcome to Notes</h1>
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

export default {
  name: "Home",
  components: {BaseContainer}
}
</script>

<style scoped>

</style>
