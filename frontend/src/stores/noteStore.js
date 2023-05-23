import {defineStore} from "pinia";
import {apiDeleteNoteById, apiGetNoteFull, apiGetNotes, apiSaveNote, apiUpdateNote} from "../services/api.js";

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
    },
    noteList: [],
    page: 1,
    size: 5
  }),
  actions: {
    async saveNote(content) {
      this.activeNote.content = content;
      this.clearError();
      if (this.activeNote.id === null) {
        try {
          const response = await apiSaveNote(this.activeNote);
          this.activeNote.id = response.note.id;
        } catch(error) {
          this.requestData.error = error
        }
      } else {
        try {
          const response = await apiUpdateNote(this.activeNote);
        } catch(error) {
          this.requestData.error = error
        }
      }
    },
    async deleteActiveNote() {
      return await this.deleteNoteById(this.activeNote.id);
    },
    async deleteNoteById(id) {
      this.clearError();
      try {
        const response = await apiDeleteNoteById(id);
      } catch (error) {
        this.requestData.error = error;
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
    },
    async fetchNotes() {
      try {
        const response = await apiGetNotes(this.page, this.size);
        this.noteList = response.notes;
      } catch (error) {
        this.error = error;
      }
    }
  }
})
