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

  </form>
</template>

<script>
export default {
  name: "BaseAuthForm",
  props: ["title"]
}
</script>

<style scoped>

</style>
