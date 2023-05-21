<template>

</template>

<script>
import {useUserStore} from "../stores/userStore.js";

export default {
  name: "Logout",
  mounted() {
    const userStore = useUserStore();
    userStore.logout();
    this.$router.push({name: "Home"});
  }
}
</script>

<style scoped>

</style>
