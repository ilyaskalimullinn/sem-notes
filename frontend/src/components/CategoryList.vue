<template>
  <div class="form-check" v-for="category in categoryList">
    <input class="form-check-input" type="checkbox"
           :value="category.id"
           v-model="activeCategoryList"
           :id="category.id">
    <label class="form-check-label" :for="category.id">
      {{category.name}}
    </label>
  </div>
</template>

<script>
import {mapState, mapWritableState} from "pinia";
import {useNoteStore} from "../stores/noteStore.js";

export default {
  name: "CategoryList",
  computed: {
    ...mapState(useNoteStore, ['categoryList']),
    ...mapWritableState(useNoteStore, ['activeCategoryList'])
  }
}
</script>

<style scoped>

</style>
