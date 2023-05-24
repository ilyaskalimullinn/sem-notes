<template>
  <ul>
    <li v-for="category in categoryList" :key="category.id">
      {{ category.name }}
    </li>
  </ul>
</template>

<script>
import {mapWritableState} from "pinia";
import {useNoteStore} from "../stores/noteStore.js";

export default {
  name: "CategoryList",
  computed: {
    ...mapWritableState(useNoteStore, ["categoryList"])
  }
}
</script>

<style scoped>

</style>
