<template>
  <form @submit.prevent="this.$emit('submit.prevent')"
        method="post"
        novalidate
        class="d-flex flex-column justify-content-center align-items-center">
    <h3>{{this.title}}</h3>
    <div class="fields-row">
      <slot name="fields">

      </slot>
    </div>

    <slot name="submit-button">

    </slot>

    <div class="links-row mt-3">
      <slot name="links">

      </slot>
    </div>

    <div class="error" v-if="this.error">
      {{ this.error.message }}
    </div>

  </form>
</template>

<script>

export default {
  name: "BaseAuthForm",
  props: ["error", "title"]
}
</script>

<style scoped>
.error {
    color: red;
    font-weight: bold;
}
</style>
