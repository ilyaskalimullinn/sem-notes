<template>
  <form @submit.prevent="this.$emit('submit.prevent')">
    <div class="input-group mb-5">
      <input type="text" name="search" id="search" class="form-control" placeholder="Search" v-model="searchFilters.string">
      <div class="input-group-append">
        <button class="btn btn-outline-secondary" type="submit">Search</button>
      </div>
    </div>
    <CategoryList :active-category-id-list="searchFilters.categoryIdList" @update-active-id-list="updateSearchCategories"/>
  </form>
</template>

<script>
import CategoryList from "../CategoryList.vue";

export default {
  name: "SearchForm",
  components: {CategoryList},
  data() {
    return {
      searchFilters: {
        categoryIdList: [],
        string: ""
      }
    }
  },
  methods: {
    updateSearchCategories(newActiveList) {
      this.searchFilters.categoryIdList = newActiveList;
    }
  }
}
</script>

<style scoped>

</style>
