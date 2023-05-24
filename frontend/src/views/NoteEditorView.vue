<template>
  <MainLayout>
    <Loader v-if="loading" />
    <NoteEditor v-else />
  </MainLayout>
</template>

<script>
import NoteEditor from "../components/editor/NoteEditor.vue";
import BaseContainer from "../containers/BaseContainer.vue";
import {useNoteStore} from "../stores/noteStore.js";
import {mapActions, mapState, mapWritableState} from "pinia";
import MainLayout from "../components/blocks/MainLayout.vue";
import Loader from "../components/Loader.vue";
export default {
  name: "NoteEditorView",
  components: {Loader, MainLayout, NoteEditor, BaseContainer},
  computed: {
    ...mapState(useNoteStore, {
      error: store => store.requestData.error,
    })
  },
  data() {
    return {
      loading: true
    }
  },
  methods: {
    ...mapActions(useNoteStore, ["fetchActiveNoteById", "loadCategories"])
  },
  async created() {
    this.loading = true;
    if (this.$route.params.id) {
      await this.fetchActiveNoteById(this.$route.params.id);
      if (this.error && this.error.statusCode && this.error.statusCode === 404) {
        this.$router.push({name: "NotFound404"});
      }
      await this.loadCategories();
    }
    this.loading = false;
  }
}
</script>

<style scoped>
</style>
