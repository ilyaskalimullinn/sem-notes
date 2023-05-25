import {defineStore} from "pinia";
import {
  apiDeleteCategoryById,
  apiDeleteNoteById,
  apiGetCategories,
  apiGetNoteFull,
  apiGetNotes, apiSaveCategory,
  apiSaveNote, apiUpdateCategory,
  apiUpdateNote
} from "../services/api.js";
import {getCategoriesFromStorage, storeCategoriesInStorage} from "../services/localData.js";

export const useNoteStore = defineStore({
  id: "noteStore",
  state: () => ({
    activeNote: {
      id: null,
      content: null,
      title: "",
      categoryIds: []
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
      this.loading = true;
      this.activeNote.content = content;
      this.clearError();
      if (this.activeNote.id === null) {
        try {
          const response = await apiSaveNote(this.activeNote);
          this.activeNote.id = response.note.id;
        } catch(error) {
          this.requestData.error = error
        } finally {
          this.loading = false;
        }
      } else {
        try {
          const response = await apiUpdateNote(this.activeNote);
        } catch(error) {
          this.requestData.error = error
        } finally {
          this.loading = false;
        }
      }
    },
    async deleteActiveNote() {
      return await this.deleteNoteById(this.activeNote.id);
    },
    async deleteNoteById(id) {
      this.loading = true;
      this.clearError();
      try {
        const response = await apiDeleteNoteById(id);
      } catch (error) {
        this.requestData.error = error;
      } finally {
        this.loading = false;
      }
    },
    async fetchActiveNoteById(id) {
      this.loading = true;
      try {
        this.activeNote = await apiGetNoteFull(id);
        console.log(this.activeNote);
      } catch(error) {
        this.requestData.error = error
      } finally {
        this.loading = false;
      }
    },
    clearError() {
      this.requestData.error = null;
    },
    async fetchNotes() {
      this.loading = true;
      try {
        const response = await apiGetNotes(this.page, this.size);
        this.noteList = response.notes;
      } catch (error) {
        this.error = error;
      } finally {
        this.loading = false;
      }
    },
    async fetchCategories() {
      this.loading = true;
      try {
        const response = await apiGetCategories();
        this.categoryList = response.categories;

        storeCategoriesInStorage(this.categoryList);
      } catch (error) {
        this.error = error;
      } finally {
        this.loading = false;
      }
    },
    async saveCategory(category) {
      this.loading = true;
      try {
        const response = await apiSaveCategory(category);
        category = response.category;
        this.categoryList.push(category);
        storeCategoriesInStorage(this.categoryList);
      } catch (error) {
        this.error = error;
      } finally {
        this.loading = false;
      }
    },
    async updateCategoryById(newCategory) {
      this.loading = true;
      try {
        const response = await apiUpdateCategory(newCategory);
        let ind = this.categoryList.findIndex(cat => cat.id === newCategory.id);
        this.categoryList[ind] = newCategory;
        storeCategoriesInStorage(this.categoryList);
      } catch (error) {
        this.error = error;
      } finally {
        this.loading = false;
      }
    },
    async deleteCategoryById(category) {
      this.loading = true;
      try {
        const response = await apiDeleteCategoryById(category.id);
        this.categoryList = this.categoryList.filter(c => c.id !== category.id);
        storeCategoriesInStorage(this.categoryList);
      } catch (error) {
        this.error = error;
      } finally {
        this.loading = false;
      }
    },
    async loadCategories() {
      this.categoryList = getCategoriesFromStorage();
      if (this.categoryList === null) {
        await this.fetchCategories();
      }
      return this.categoryList
    },
    clearActiveNote() {
      this.activeNote.id = null;
      this.activeNote.content = null;
      this.activeNote.title = "";
      this.activeNote.categoryIds = [];
    }
  }
})
