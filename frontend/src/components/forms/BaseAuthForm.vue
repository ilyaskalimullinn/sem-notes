<template>
  <form @submit.prevent="this.$emit('submit.prevent')" method="post">
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

    <div class="error" v-if="this.error">
      {{ this.error.message }}
    </div>

  </form>
</template>

<script>

export default {
  name: "BaseAuthForm",
  props: ["title", "error"]
}
</script>

<style scoped>
.error {
    color: red;
    font-weight: bold;
}
</style>
