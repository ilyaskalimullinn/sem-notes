import {defineStore} from "pinia";
import {
  apiDeleteCategory,
  apiDeleteNoteById,
  apiGetCategories,
  apiGetNoteFull,
  apiGetNotes, apiSaveCategory,
  apiSaveNote, apiUpdateCategory,
  apiUpdateNote
} from "../services/api.js";

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
    categoryList: [],
    activeCategoryList: [],
    page: 0,
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
    },
    async fetchCategories() {
      try {
        const response = await apiGetCategories();
        this.categoryList = response.categories;
      } catch (error) {
        this.error = error;
      }
    },
    async saveCategory(category) {
      try {
        const response = await apiSaveCategory(category);
        category = response.data.category;
        this.categoryList.push(category);
      } catch (error) {
        this.error = error;
      }
    },
    async updateCategoryById(newCategory) {
      try {
        const response = await apiUpdateCategory(newCategory);
        let ind = this.categoryList.findIndex(cat => cat.id === newCategory.id);
        this.categoryList[ind] = newCategory;
      } catch (error) {
        this.error = error;
      }
    },
    async deleteCategoryById(category) {
      try {
        const response = await apiDeleteCategory(category);
        this.categoryList = this.categoryList.filter(c => c.id !== category.id);
      } catch (error) {
        this.error = error;
      }
    },
  }
})
