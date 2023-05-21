<template>
  <BaseContainer>
    <NoteEditor></NoteEditor>
  </BaseContainer>
</template>

<script>
import NoteEditor from "../components/editor/NoteEditor.vue";
import BaseContainer from "../containers/BaseContainer.vue";
export default {
  name: "NoteEditorView",
  components: {NoteEditor, BaseContainer}
}
</script>

<style scoped>
</style>
