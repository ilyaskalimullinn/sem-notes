import {defineStore} from "pinia";
import {apiGetNoteFull, apiSaveNote} from "../services/api.js";

export const useNoteStore = defineStore({
  id: "noteStore",
  state: () => ({
    activeNote: {
      id: null,
      content: null,
      title: null
    },
    requestData: {
      loading: false,
      error: null
    }
  }),
  actions: {
    async saveNote(note) {
      this.activeNote = note;
      this.clearError();
      try {
        const response = await apiSaveNote(this.activeNote);
      } catch(error) {
        this.requestData.error = error
      }
    },
    async fetchActiveNoteById(id) {
      try {
        this.activeNote = await apiGetNoteFull(id);
        console.log(this.activeNote);
      } catch(error) {
        this.requestData.error = error
      }
    },
    clearError() {
      this.requestData.error = null;
    }
  }
})
