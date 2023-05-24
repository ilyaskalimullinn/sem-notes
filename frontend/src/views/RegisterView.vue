<template>
  <MainLayout>
    <h1>Sign up</h1>
    <br />
    <RegistrationForm />
    <router-link :to="{name: 'Home'}">Main page</router-link>
  </MainLayout>
</template>

<script>
import RegistrationForm from "../components/forms/RegistrationForm.vue";
import BaseContainer from "../containers/BaseContainer.vue";
import MainLayout from "../components/blocks/MainLayout.vue";

export default {
  name: "RegisterView",
  components: {MainLayout, BaseContainer, RegistrationForm}
}
</script>

<style scoped>

</style>
