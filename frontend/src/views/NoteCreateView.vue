<template>
  <MainLayout>
    <Loader v-if="loading" />
    <NoteEditor v-else />
  </MainLayout>
</template>

<script>
import NoteEditor from "../components/editor/NoteEditor.vue";
import Loader from "../components/Loader.vue";
import MainLayout from "../components/blocks/MainLayout.vue";
import {mapActions} from "pinia";
import {useNoteStore} from "../stores/noteStore.js";

export default {
  name: "NoteCreateView",
  components: {MainLayout, Loader, NoteEditor},
  data() {
    return {
      loading: true
    }
  },
  methods: {
    ...mapActions(useNoteStore, ["fetchCategories"])
  },
  async created() {
    // todo error handling here and in NoteEditorView too
    this.loading = true;
    await this.fetchCategories();
    this.loading = false;
  }
}
</script>

<style scoped>

</style>
