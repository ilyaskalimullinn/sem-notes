<template>
  <BaseAuthForm :title="title">
    <template v-slot:fields>
      <FormField name="email" label="Email" type="email" required />
      <FormField name="fullName" label="Name" required/>
      <FormField name="password" label="Password" type="password" required/>
      <FormField name="passwordRepeat" label="Password again" type="password" required/>
    </template>
    <template v-slot:submit-button>
      <input type="submit" value="Sign in"/>
    </template>
    <template v-slot:links>
      <router-link :to="{name: 'Login'}">
        Sign in
      </router-link>
    </template>
  </BaseAuthForm>
</template>

<script>
import BaseAuthForm from "./BaseAuthForm.vue";
import FormField from "./FormField.vue";

export default {
  name: "RegistrationForm",
  components: {FormField, BaseAuthForm},
  data() {
    return {
      title: "Register"
    }
  }
}
</script>

<style scoped>

</style>
