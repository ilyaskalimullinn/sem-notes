<template>
  <input type="text" v-model="activeNote.title" placeholder="Title">
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
import {mapActions, mapState, mapWritableState} from "pinia";
import {useNoteStore} from "../../stores/noteStore.js";

export default {
  data() {
    return {
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
    ...mapWritableState(useNoteStore, {
      error: (state) => state.requestData.error,
      activeNote: "activeNote"
    })
  },
  watch: {
    activeNote(newValue, oldValue) {
      if (newValue) {
        this.editor.isReady.then(() => {
          this.editor.render(newValue.content);
        })
      }
    }
  },
  methods: {
    ...mapActions(useNoteStore, ["saveNote"]),
    async submit() {
      await this.saveNote(await this.editor.save());
      console.log(this.activeNote)
      if (!this.error && !this.$route.params.id) {
        this.$router.push({name: "NoteEdit", params: {id: this.activeNote.id}})
      }
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
