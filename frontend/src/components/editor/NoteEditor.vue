<template>
  <input type="text" v-model="title" placeholder="Title">
  <div id="editorjs"></div>
  <button @click="submit">Save</button>
</template>

<script>
import EditorJS from "@editorjs/editorjs";
import Header from "@editorjs/header";
import List from "@editorjs/list";
import {mapActions, mapState} from "pinia";
import {useNoteStore} from "../../stores/noteStore.js";

export default {
  data() {
    return {
      title: "",
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
        },
        defaultBlock: "header"
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
      const content = await this.editor.save();
      const note = {title: this.title, content};
      await this.saveNote(note);
      console.log(this.activeNote)
    }
  },
  name: "NoteEditor"
}
</script>

<style scoped>
#editorjs {
    border: 1px solid #aaa;
}
</style>
