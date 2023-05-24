<template>
  <MainLayout>
    <div class="flex-grow-1 d-flex align-items-center justify-content-center">
      <RegistrationForm />
    </div>
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
