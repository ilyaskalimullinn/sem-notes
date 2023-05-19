<template>
  <form @submit.prevent="this.$emit('submit')" method="post">
    <div class="form-title">{{title}}</div>
    <div class="fields-row">
      <slot name="fields">

      </slot>
    </div>

    <slot name="submit-button">

    </slot>

    <div class="links-row">
      <slot name="links">

      </slot>
    </div>

    <div class="errors" v-if="this.errors">
      <AuthFormError v-for="error in this.errors"
        :text="error"
      />
    </div>

  </form>
</template>

<script>
import {mapState} from "pinia";
import {useUserStore} from "../../stores/userStore.js";
import AuthFormError from "./AuthFormError.vue";

export default {
  name: "BaseAuthForm",
  components: {AuthFormError},
  props: ["title"],
  computed: {
    ...mapState(useUserStore, ['errors'])
  }
}
</script>

<style scoped>

</style>
