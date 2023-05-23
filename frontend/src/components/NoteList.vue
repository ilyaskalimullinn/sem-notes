<template>
  <ul>
    <li v-for="note in this.noteList" :key="note.id">
      <router-link :to="{name: 'NoteEdit', params: {id: note.id}}">
        {{note.title}}
      </router-link>
    </li>
  </ul>
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
