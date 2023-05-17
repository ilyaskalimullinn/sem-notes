<template>
  <BaseContainer>
    <h1>Welcome to Notes</h1>
    <br />
    <router-link :to="{name: 'Login'}">Login</router-link>
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
