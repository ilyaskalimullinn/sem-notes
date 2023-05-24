<template>
  <input type="text" v-model="activeNote.title" placeholder="Title">
  <div id="editorjs"></div>
  <button @click="submit">Save</button>
  <button @click="this.delete">Delete</button>
  <CategoryList :active-category-id-list="activeNote.categoryIds" @update-active-id-list="updateActiveNoteCategories" />
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
import CategoryList from "../CategoryList.vue";

export default {
  components: {CategoryList},
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
    ...mapState(useNoteStore, {
      error: (state) => state.requestData.error,
      activeNote: "activeNote"
    })
  },
  created() {
    if (this.activeNote !== null && this.activeNote.content !== null) {
      this.editor.isReady.then(() => {
        this.editor.render(this.activeNote.content);
      })
    }
  },
  watch: {
    activeNote(newValue, oldValue) {
      if (newValue && newValue.content) {
        this.editor.isReady.then(() => {
          this.editor.render(newValue.content);
        })
      } else {
        this.editor.clear();
      }
    }
  },
  methods: {
    ...mapActions(useNoteStore, ["saveNote", "deleteActiveNote"]),
    async submit() {
      const content = await this.editor.save();
      await this.saveNote(content);
      alert(this.error || "Saved");
      if (!this.error && !this.$route.params.id) {
        this.$router.push({name: "NoteEdit", params: {id: this.activeNote.id}})
      }
    },
    delete() {
      if (confirm("Are you sure you want to delete this note?")) {
        this.doDelete();
      }
    },
    async doDelete() {
      await this.deleteActiveNote();
      if (!this.error) {
        this.$router.push({name: "NoteList"});
      }
    },
    updateActiveNoteCategories(newCategoryList) {
      this.activeNote.categoryIds = newCategoryList;
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
