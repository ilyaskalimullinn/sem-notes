<template>
  <div class="container h-100 d-flex flex-column">

    <Header />
    <main class="mt-5 mb-5">
      <slot>

      </slot>
    </main>

    <Footer/>
  </div>

</template>

<script>
import Header from "./Header.vue";
import Footer from "./Footer.vue";

export default {
  name: "MainLayout",
  components: {Footer, Header}
}
</script>

<style scoped>

</style>
