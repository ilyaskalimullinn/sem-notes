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
  computed: {
    ...mapState(useNoteStore, ["categoryList"])
  },
  methods: {
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
    }
  }
};
</script>

<style scoped>

</style>
