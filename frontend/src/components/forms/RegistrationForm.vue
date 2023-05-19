<template>
  <BaseAuthForm @submit.prevent="this.register" :title="title" :error="this.errorMessage">
    <template v-slot:fields>
      <FormField name="email"
                 v-model="this.form.email"
                 :errors="v$.form.email.$errors"
                 label="Email"
                 type="email"
                 required/>

      <FormField name="fullName"
                 v-model="this.form.fullName"
                 :errors="v$.form.fullName.$errors"
                 label="Name"
                 required/>

      <FormField name="password"
                 v-model="this.form.password"
                 :errors="v$.form.password.$errors"
                 label="Password"
                 type="password"
                 required/>

      <FormField name="passwordRepeat"
                 v-model="this.form.passwordRepeat"
                 :errors="v$.form.passwordRepeat.$errors"
                 label="Password again"
                 type="password"
                 required/>
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
import useVuelidate from "@vuelidate/core";
import {email, helpers, maxLength, minLength, required} from "@vuelidate/validators";
import {storeToRefs} from "pinia";
import {useUserStore} from "../../stores/userStore.js";

export default {
  name: "RegistrationForm",
  components: {FormField, BaseAuthForm},
  setup() {
    const userStore = useUserStore();
    const {error} = storeToRefs(userStore);
    return {
      v$: useVuelidate(),
      error
    }
  },
  data() {
    return {
      title: "Register",
      form: {
        email: "",
        fullName: "",
        password: "",
        passwordRepeat: ""
      },
      errorMessagesMap: {
        409: "This email address is already taken",
        400: "Invalid request",
        500: "Error on the server"
      },
      errorMessage: null
    }
  },
  watch: {
    error(newValue, oldValue) {
      console.log(newValue);
      if (newValue == null) {
        this.errorMessage = null;
        return;
      }
      if (!newValue.response || !newValue.response.status) {
        this.errorMessage = "Unknown error";
        return;
      }
      this.errorMessage = this.errorMessagesMap[newValue.response.status] || "Unknown error";
    }
  },
  validations() {
    return {
      form: {
        email: {
          required: helpers.withMessage("This field is required", required),
          email: helpers.withMessage("Not a valid email", email),
        },
        password: {
          required: helpers.withMessage("This field is required", required),
          minLength: helpers.withMessage("Min length is 5 characters", minLength(5)),
          maxLength: helpers.withMessage("Max length is 255 characters", maxLength(255))
        },
        fullName: {
          required: helpers.withMessage("This field is required", required),
          minLength: helpers.withMessage("Min length is 5 characters", minLength(1)),
          maxLength: helpers.withMessage("Max length is 255 characters", maxLength(255)),
        },
        passwordRepeat: {
          sameAsPassword: helpers.withMessage("Must be the same as password", (v) => v === this.form.password)
        }
      }
    }

  },
  methods: {
    async register() {
      const userStore = useUserStore();
      this.v$.form.$touch();
      if (this.v$.form.$error) {
        return;
      }
      await register(this.form.email, this.form.fullName, this.form.password, this.form.passwordRepeat);
      if (!userStore.error) {
        this.$router.push({name: "Home"});
      }
    }
  }
}
</script>

<style scoped>

</style>
