<template>
  <input type="text" v-model="title" placeholder="Title">
  <div id="editorjs"></div>
  <button @click="submit">Save</button>
  <div class="error" v-if="this.error">{{error.message}}</div>
</template>

<script>
import EditorJS from "@editorjs/editorjs";
import Header from "@editorjs/header";
import List from "@editorjs/list";
import Checklist from "@editorjs/checklist";
import Quote from "@editorjs/quote";
import CodeTool from "@editorjs/code";
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
          },
          checkList: {
            class: Checklist,
            inlineToolbar: true
          },
          quote: {
            class: Quote,
            inlineToolbar: true
          },
          code: {
            class: CodeTool,
            inlineToolbar: true
          }
        },
        defaultBlock: "paragraph",
        autofocus: true
      })
    }
  },
  computed: {
    ...mapState(useNoteStore, {
      error: (state) => state.requestData.error,
      activeNote: "activeNote"
    })
  },
  watch: {
    activeNote(newValue, oldValue) {
      if (newValue) {
        this.editor.isReady.then(() => {
          this.title = newValue.title;
          this.editor.render(newValue.content);
        })
      }
    }
  },
  methods: {
    ...mapActions(useNoteStore, ["saveNote"]),
    async submit() {
      const content = await this.editor.save();
      const note = {title: this.title, content};
      await this.saveNote(note);
      console.log(this.activeNote)
      alert(this.error || "Saved");
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
