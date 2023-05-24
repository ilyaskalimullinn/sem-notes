<template>
  <MainLayout>
    <div class="flex-grow-1 d-flex align-items-center justify-content-center">
      <LoginForm />
    </div>
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
