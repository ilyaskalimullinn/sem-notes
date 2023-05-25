<template>
  <div class="form-check" v-for="category in this.categoryList">
    <input class="form-check-input" type="checkbox"
           :value="category.id"
           :checked="isChecked(category.id)"
           @change="toggleCategoryId(category.id)"
           :id="category.id"/>
    <label class="form-check-label" :for="category.id">
      {{category.name}}
    </label>
    <button type="button"
            class="btn btn-primary small"
            data-bs-toggle="modal"
            data-bs-target="#exampleModal"
            @click="selectedCategory = {...category}">
      Options
    </button>
  </div>


  <!-- Modal -->
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="exampleModalLabel">Edit category</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">

          <form @submit.prevent="updateCategory">
            <div v-if="selectedCategory">
              <h6>{{ selectedCategory.name }}</h6>
              <div class="mb-3">
                <label for="categoryName" class="form-label">Name</label>
                <input type="text" class="form-control" id="categoryName" v-model="selectedCategory.name" required>
              </div>
            </div>
            <div v-else>
              <p>No category selected.</p>
            </div>
          </form>

        </div>
        <div class="modal-footer flex justify-content-between">
          <button type="button" class="btn btn-danger" data-bs-dismiss="modal" @click="deleteCategory">Delete</button>
          <button type="button" class="btn btn-primary" data-bs-dismiss="modal" @click="updateCategory">Save</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {mapActions, mapState, mapWritableState} from "pinia";
import {useNoteStore} from "../stores/noteStore.js";
import Loader from "./Loader.vue";

export default {
  name: "CategoryList",
  components: {Loader},
  props: {
    activeCategoryIdList: {
      type: Array,
      required: true
    }
  },
  data() {
    return {
      selectedCategory: null
    }
  },
  computed: {
    ...mapState(useNoteStore, ["categoryList"])
  },
  methods: {
    ...mapActions(useNoteStore, ["deleteCategoryById", "updateCategoryById"]),
    isChecked(categoryId) {
      return this.activeCategoryIdList.includes(categoryId);
    },
    toggleCategoryId(categoryId) {
      if (this.isChecked(categoryId)) {
        this.removeFromActiveCategoryIdList(categoryId);
      } else {
        this.addToActiveCategoryIdList(categoryId);
      }
    },
    addToActiveCategoryIdList(item) {
      const updatedActiveCategoryIdList = [...this.activeCategoryIdList, item];
      this.$emit('update-active-id-list', updatedActiveCategoryIdList);
    },
    removeFromActiveCategoryIdList(item) {
      const updatedActiveCategoryIdList = this.activeCategoryIdList.filter(i => i !== item);
      this.$emit('update-active-id-list', updatedActiveCategoryIdList);
    },
    updateCategory() {
      this.updateCategoryById(this.selectedCategory)
    },
    deleteCategory() {
      this.deleteCategoryById(this.selectedCategory);
    }
  }
};
</script>

<style scoped>

</style>
