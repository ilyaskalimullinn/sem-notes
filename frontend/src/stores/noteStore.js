import {defineStore} from "pinia";
import {apiSaveNote} from "../services/api.js";

export const useNoteStore = defineStore({
  id: "noteStore",
  state: () => ({
    activeNote: null,
    requestData: {
      loading: false,
      error: false
    }
  }),
  actions: {
    async saveNote(note) {
      this.activeNote = note;
      try {
        const response = await apiSaveNote(this.activeNote);
      } catch(error) {
        this.requestData.error = error.message
      }
    }
  }
})
