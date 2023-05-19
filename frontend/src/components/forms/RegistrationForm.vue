<template>
  <BaseAuthForm @submit="this.register" :title="title">
    <template v-slot:fields>
      <FormField name="email" v-model="this.email" label="Email" type="email" required />
      <FormField name="fullName" v-model="this.fullName" label="Name" required/>
      <FormField name="password" v-model="this.password" label="Password" type="password" required/>
      <FormField name="passwordRepeat" v-model="this.passwordRepeat" label="Password again" type="password" required/>
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
import {register} from "../../services/api.js";

export default {
  name: "RegistrationForm",
  components: {FormField, BaseAuthForm},
  data() {
    return {
      title: "Register",
      email: "",
      fullName: "",
      password: "",
      passwordRepeat: ""
    }
  },
  methods: {
    register() {
      register(this.email, this.fullName, this.password, this.passwordRepeat);
    }
  }
}
</script>

<style scoped>

</style>
