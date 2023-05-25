<template>
  <form @submit.prevent="submit" class="col-4">
    <h5>Create new category</h5>
    <div class="input-group mb-5">
      <input type="text" name="name" id="name" class="form-control" placeholder="Name" v-model="newCategory.name">
      <div class="input-group-append">
        <button class="btn btn-outline-secondary" type="submit">Create</button>
      </div>
    </div>
  </form>
</template>

<script>
import {mapActions} from "pinia";
import {useNoteStore} from "../../stores/noteStore.js";

export default {
  name: "CreateCategoryForm",
  data() {
    return {
      newCategory: {
        name: ""
      }
    }
  },
  methods: {
    ...mapActions(useNoteStore, ["saveCategory"]),
    submit() {
      this.saveCategory(this.newCategory);
    }
  }
}
</script>

<style scoped>

</style>
