<template>
  <BaseContainer>
    <h1>Sign in</h1>
    <br />
    <router-link :to="{name: 'Home'}">Main page</router-link>
  </BaseContainer>
</template>

<script>
import BaseContainer from "../containers/BaseContainer.vue";

export default {
  name: "Login",
  components: {BaseContainer}
}
</script>

<style scoped>

</style>
