<template>
  <BaseAuthForm @submit="this.login" :title="title">
    <template v-slot:fields>
      <FormField name="email" v-model="this.form.email" :errors="v$.form.email.$errors" label="Email" type="email" required/>
      <FormField name="password" v-model="this.form.password" :errors="v$.form.password.$errors" label="Password" type="password" required/>
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
import useVuelidate from "@vuelidate/core";
import {email, maxLength, minLength, required, helpers} from "@vuelidate/validators";


export default {
  name: "LoginForm",
  components: {FormField, BaseAuthForm},
  setup () {
    return {
      v$: useVuelidate()
    }
  },
  data() {
    return {
      title: "Login",
      form: {
        email: "",
        password: ""
      }
    }
  },
  validations: {
    form: {
      email: {
        required: helpers.withMessage("This field is required", required),
        email: helpers.withMessage("Not a valid email", email),
      },
      password: {
        required: helpers.withMessage("This field is required", required),
        minLength: helpers.withMessage("Min length is 5 characters", minLength(5)),
        maxLength: helpers.withMessage("Max length is 255 characters", maxLength(255))
      }
    }
  },
  methods: {
    async login() {
      this.v$.form.$touch();
      console.log(this.v$.form)
      if (this.v$.form.$error) {
        return;
      }
      const res = await login(this.form.email, this.form.password);
      if (res) {
        this.$router.push({name: 'Home'});
      }
    }
  }
}
</script>

<style scoped>

</style>
