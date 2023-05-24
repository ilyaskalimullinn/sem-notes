<template>
  <MainLayout>
    <h1>Sign in</h1>
    <br />
    <LoginForm />
    <router-link :to="{name: 'Home'}">Main page</router-link>
  </MainLayout>
</template>

<script>
import BaseContainer from "../containers/BaseContainer.vue";
import LoginForm from "../components/forms/LoginForm.vue";
import MainLayout from "../components/blocks/MainLayout.vue";

export default {
  name: "LoginView",
  components: {MainLayout, LoginForm, BaseContainer}
}
</script>

<style scoped>

</style>
