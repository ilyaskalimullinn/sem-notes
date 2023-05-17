<template>
  <BaseContainer>
    <h1>Sign up</h1>
    <br />
    <RegistrationForm />
    <router-link :to="{name: 'Home'}">Main page</router-link>
  </BaseContainer>
</template>

<script>
import RegistrationForm from "../components/forms/RegistrationForm.vue";
import BaseContainer from "../containers/BaseContainer.vue";

export default {
  name: "Register",
  components: {BaseContainer, RegistrationForm}
}
</script>

<style scoped>

</style>
