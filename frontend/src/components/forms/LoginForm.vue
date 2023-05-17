<template>
  <BaseAuthForm :title="title">
    <template v-slot:fields>
      <FormField name="email" label="Email" type="email" required/>
      <FormField name="password" label="Password" type="password" required/>
    </template>
    <template v-slot:submit-button>
      <input type="submit" value="Sign in"/>
    </template>
    <template v-slot:links>
      <router-link :to="{name: 'Register'}">
        Sign up
      </router-link>
    </template>
  </BaseAuthForm>
</template>

<script>
import BaseAuthForm from "./BaseAuthForm.vue";
import FormField from "./FormField.vue";

export default {
  name: "LoginForm",
  components: {FormField, BaseAuthForm},
  data() {
    return {
      title: "Login"
    }
  }
}
</script>

<style scoped>

</style>
