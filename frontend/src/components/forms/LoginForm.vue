<template>
  <BaseAuthForm @submit="this.login" :title="title">
    <template v-slot:fields>
      <FormField name="email" v-model="this.email" label="Email" type="email" required/>
      <FormField name="password" v-model="this.password" label="Password" type="password" required/>
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
import {login} from "../../services/api.js";

export default {
  name: "LoginForm",
  components: {FormField, BaseAuthForm},
  data() {
    return {
      title: "Login",
      email: "",
      password: ""
    }
  },
  methods: {
    async login() {
      const res = await login(this.email, this.password);
      if (res) {
        this.$router.push({name: 'Home'});
      }
    }
  }
}
</script>

<style scoped>

</style>
