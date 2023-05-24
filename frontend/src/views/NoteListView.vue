<template>
  <Loader v-if="this.loading" />
  <MainLayout v-else>
    <h2 class="mb-5">My Notes</h2>
    <SearchForm></SearchForm>
    <NoteList></NoteList>
    <div class="error" v-if="this.error">{{this.error}}</div>
  </MainLayout>
</template>

<script>
import NoteList from "../components/NoteList.vue";
import {mapActions, mapState, mapWritableState} from "pinia";
import {useNoteStore} from "../stores/noteStore.js";
import MainLayout from "../components/blocks/MainLayout.vue";
import SearchForm from "../components/forms/SearchForm.vue";
import Loader from "../components/Loader.vue";

export default {
  name: "NoteListView",
  components: {Loader, SearchForm, MainLayout, NoteList},
  data() {
    return {
      loading: true
    }
  },
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
  async created() {
    this.loading = true;
    await this.fetchNotes();
    await this.fetchCategories();
    this.loading = false;
  }
}
</script>

<style scoped>

</style>
