<template>
  <div v-for="note in this.noteList" :key="note.id" class="border-1 border p-3 mb-2 mt-2 d-flex">
    <h3>
      <router-link :to="{name: 'NoteEdit', params: {id: note.id}}">
        {{note.title}}
      </router-link>
    </h3>
  </div>
</template>

<script>
import {mapState} from "pinia";
import {useNoteStore} from "../stores/noteStore.js";

export default {
  name: "NoteList",
  computed: {
    ...mapState(useNoteStore, ["noteList"])
  }
}
</script>

<style scoped>

</style>
