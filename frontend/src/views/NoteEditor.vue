<template>
  <div id="editorjs"></div>
</template>

<script>
import EditorJS from "@editorjs/editorjs";
import Header from "@editorjs/header";
import List from "@editorjs/list";

export default {
  setup() {
    const editor = new EditorJS({
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
  },
  name: "NoteEditor"
}
</script>

<style scoped>
  #editorjs {
      background-color: #aaa;
  }
</style>
