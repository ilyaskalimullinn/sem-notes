<template>
  <div class="container">
    <slot>

    </slot>
  </div>
</template>

<script>
export default {
  name: "BaseContainer"
}
</script>

<style scoped>
.container {
    width: 90%;
    margin: 0 auto;
    max-width: 1300px;
}
</style>
