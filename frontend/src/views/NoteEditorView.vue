<template>
  <MainLayout>
    <NoteEditor></NoteEditor>
  </MainLayout>
</template>

<script>
import NoteEditor from "../components/editor/NoteEditor.vue";
import BaseContainer from "../containers/BaseContainer.vue";
import {useNoteStore} from "../stores/noteStore.js";
import {mapActions, mapState} from "pinia";
import MainLayout from "../components/blocks/MainLayout.vue";
export default {
  name: "NoteEditorView",
  components: {MainLayout, NoteEditor, BaseContainer},
  computed: {
    ...mapState(useNoteStore, {
      error: store => store.requestData.error,
    })
  },
  methods: {
    ...mapActions(useNoteStore, ["fetchActiveNoteById"])
  },
  async created() {
    if (this.$route.params.id) {
      await this.fetchActiveNoteById(this.$route.params.id);
      if (this.error && this.error.statusCode && this.error.statusCode === 404) {
        this.$router.push({name: "NotFound404"});
      }
    }
  }
}
</script>

<style scoped>
</style>
