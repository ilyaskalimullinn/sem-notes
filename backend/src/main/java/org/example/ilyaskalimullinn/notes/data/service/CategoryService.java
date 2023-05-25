package org.example.ilyaskalimullinn.notes.data.service;

import org.example.ilyaskalimullinn.notes.data.entity.User;
import org.example.ilyaskalimullinn.notes.data.entity.note.Category;
import org.example.ilyaskalimullinn.notes.data.repository.CategoryRepository;
import org.example.ilyaskalimullinn.notes.data.repository.NoteRepository;
import org.example.ilyaskalimullinn.notes.data.response.CategoryEditResponse;
import org.example.ilyaskalimullinn.notes.data.response.CategoryListResponse;
import org.example.ilyaskalimullinn.notes.data.serializer.note.CategorySerializer;
import org.example.ilyaskalimullinn.notes.exception.EntityPersistenceException;
import org.example.ilyaskalimullinn.notes.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private NoteRepository noteRepository;

    public CategoryListResponse getCategoryListResponseByAuthor(User user) {
        try {
            List<CategorySerializer> categories = categoryRepository.findByAuthor(user).stream()
                    .map(category -> CategorySerializer.builder()
                            .name(category.getName())
                            .id(category.getId())
                            .build()
                    )
                    .toList();

            return CategoryListResponse.builder()
                    .categories(categories)
                    .build();
        } catch (Exception e) {
            throw new EntityPersistenceException("Could not find categories. Please, try again");
        }
    }

    public CategoryEditResponse createCategory(CategorySerializer categorySerializer, User user) {
        try {
            Category category = new Category();
            category.setAuthor(user);
            category.setName(categorySerializer.getName());
            categoryRepository.save(category);

            categorySerializer.setId(category.getId());
            categorySerializer.setName(category.getName());

            return CategoryEditResponse.builder()
                    .detail("Created")
                    .category(categorySerializer)
                    .build();
        } catch (Exception e) {
            throw new EntityPersistenceException("Could not create category. Please, try again");
        }
    }

    public CategoryEditResponse editCategory(CategorySerializer categorySerializer, User user) {
        Category category = categoryRepository.findByIdAndAuthor(categorySerializer.getId(), user)
                .orElseThrow(() -> new NotFoundException("Can't find a category"));
        try {
            category.setName(categorySerializer.getName());
            categoryRepository.save(category);

            return CategoryEditResponse.builder()
                    .detail("Updated")
                    .category(categorySerializer)
                    .build();
        } catch (Exception e) {
            throw new EntityPersistenceException("Could not update category. Please, try again");
        }
    }

    public CategoryEditResponse deleteCategoryByIdAndAuthor(Long categoryId, User user) {
        Category category = categoryRepository.findByIdAndAuthor(categoryId, user)
                .orElseThrow(() -> new NotFoundException("Can't find a category"));
        try {
            categoryRepository.delete(category);

            return CategoryEditResponse.builder()
                    .detail("Deleted")
                    .category(CategorySerializer.builder()
                            .id(category.getId())
                            .name(category.getName())
                            .build())
                    .build();
        } catch (Exception e) {
            throw new EntityPersistenceException("Could not delete category. Please, try again");
        }
    }
}
