<template>
  <MainLayout>
    <h2 class="mb-5">My Notes</h2>
    <SearchForm></SearchForm>
    <NoteList></NoteList>
    <div class="error" v-if="this.error">{{this.error}}</div>
  </MainLayout>
</template>

<script>
import NoteList from "../components/NoteList.vue";
import {mapActions, mapWritableState} from "pinia";
import {useNoteStore} from "../stores/noteStore.js";
import MainLayout from "../components/blocks/MainLayout.vue";
import SearchForm from "../components/forms/SearchForm.vue";

export default {
  name: "NoteListView",
  components: {SearchForm, MainLayout, NoteList},
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
