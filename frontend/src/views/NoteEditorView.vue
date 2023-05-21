<template>
  <div id="editorjs"></div>
  <button @click="submit">Save</button>
</template>

<script>
import EditorJS from "@editorjs/editorjs";
import Header from "@editorjs/header";
import List from "@editorjs/list";
import {mapActions, mapState} from "pinia";
import {useNoteStore} from "../stores/noteStore.js";

export default {
  data() {
    return {
      error: null,
      editor: new EditorJS({
        holder: "editorjs",
        tools: {
          header: {
            class: Header,
            inlineToolbar: ['link']
          },
          list: {
            class: List,
            inlineToolbar: true
          }
        }
      })
    }
  },
  computed: {
    ...mapState(useNoteStore, {
      error: (state) => state.requestData.error,
      activeNote: "activeNote"
    })
  },
  methods: {
    ...mapActions(useNoteStore, ["saveNote"]),
    async submit() {
      const note = await this.editor.save();
      await this.saveNote(note);
      console.log(this.activeNote)
    }
  },
  name: "NoteEditorView"
}
</script>

<style scoped>
  #editorjs {
      background-color: #aaa;
  }
</style>
