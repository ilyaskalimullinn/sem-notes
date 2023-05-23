<template>
  <router-view />
  <BaseContainer>
    <NoteEditor></NoteEditor>
  </BaseContainer>
</template>

<script>
import NoteEditor from "../components/editor/NoteEditor.vue";
import BaseContainer from "../containers/BaseContainer.vue";
import {useNoteStore} from "../stores/noteStore.js";
import {mapActions, mapState} from "pinia";
export default {
  name: "NoteEditorView",
  components: {NoteEditor, BaseContainer},
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
