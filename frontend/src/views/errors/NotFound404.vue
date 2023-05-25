<template>
  <MainLayout>
    <h1>Page not found</h1>
    <router-link :to="{name: 'Home'}">Main page</router-link>
  </MainLayout>
</template>

<script>
import MainLayout from "../../components/blocks/MainLayout.vue";

export default {
  name: "NotFound404",
  components: {MainLayout}
}
</script>

<style scoped>

</style>
