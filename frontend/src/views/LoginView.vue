<template>
  <BaseContainer>
    <h1>Sign in</h1>
    <br />
    <LoginForm />
    <router-link :to="{name: 'Home'}">Main page</router-link>
  </BaseContainer>
</template>

<script>
import BaseContainer from "../containers/BaseContainer.vue";
import LoginForm from "../components/forms/LoginForm.vue";

export default {
  name: "Login",
  components: {LoginForm, BaseContainer}
}
</script>

<style scoped>

</style>
