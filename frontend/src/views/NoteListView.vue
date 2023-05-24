<template>
  <router-link :to="{name: 'Home'}">Home</router-link>
  <CategoryList></CategoryList>
  <NoteList></NoteList>
  <div class="error" v-if="this.error">{{this.error}}</div>
</template>

<script>
import NoteList from "../components/NoteList.vue";
import {mapActions, mapWritableState} from "pinia";
import {useNoteStore} from "../stores/noteStore.js";
import CategoryList from "../components/CategoryList.vue";

export default {
  name: "NoteListView",
  components: {CategoryList, NoteList},
  computed: {
    ...mapWritableState(useNoteStore, {
      page: "page",
      size: "size",
      error: state => state.requestData.error
    })
  },
  methods: {
    ...mapActions(useNoteStore, ["fetchNotes", "fetchCategories"])
  },
  async mounted() {
    await this.fetchNotes();
    await this.fetchCategories();
  }
}
</script>

<style scoped>

</style>
