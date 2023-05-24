<template>
  <form @submit.prevent="this.$emit('submit.prevent')">
    <div class="input-group mb-5">
      <input type="text" name="search" id="search" class="form-control" placeholder="Search">
      <div class="input-group-append">
        <button class="btn btn-outline-secondary" type="submit">Search</button>
      </div>
    </div>
    <CategoryList></CategoryList>
  </form>
</template>

<script>
import CategoryList from "../CategoryList.vue";

export default {
  name: "SearchForm",
  components: {CategoryList}
}
</script>

<style scoped>

</style>
