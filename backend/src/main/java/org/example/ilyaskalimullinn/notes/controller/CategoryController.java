package org.example.ilyaskalimullinn.notes.controller;

import org.example.ilyaskalimullinn.notes.data.entity.User;
import org.example.ilyaskalimullinn.notes.data.request.CategoryEditRequest;
import org.example.ilyaskalimullinn.notes.data.response.CategoryEditResponse;
import org.example.ilyaskalimullinn.notes.data.response.CategoryListResponse;
import org.example.ilyaskalimullinn.notes.data.service.CategoryService;
import org.example.ilyaskalimullinn.notes.exception.FieldsValidationException;
import org.example.ilyaskalimullinn.notes.exception.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("${api.uri}/category")
public class CategoryController {
    // need: list, post, edit, delete
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("")
    public CategoryListResponse list(Principal principal) {
        User user = (User) userDetailsService.loadUserByUsername(principal.getName());
        return categoryService.getCategoryListResponseByAuthor(user);
    }

    @PostMapping("")
    public CategoryEditResponse create(@RequestBody @Valid CategoryEditRequest categoryEditRequest,
                                       BindingResult result,
                                       Principal principal) {
        if (result.hasErrors()) {
            throw new FieldsValidationException("Errors in category creation request", result.getFieldErrors());
        }
        if (categoryEditRequest.getCategory().getId() != null) {
            throw new InvalidRequestException("Bad request, new category already has an ID");
        }
        User user = (User) userDetailsService.loadUserByUsername(principal.getName());
        return categoryService.createCategory(categoryEditRequest.getCategory(), user);
    }

    @PutMapping("/{categoryId}")
    public CategoryEditResponse edit(@RequestBody @Valid CategoryEditRequest categoryEditRequest,
                                       BindingResult result,
                                       @PathVariable("categoryId") Long categoryId,
                                       Principal principal) {
        if (result.hasErrors()) {
            throw new FieldsValidationException("Errors in category update request", result.getFieldErrors());
        }
        if (!categoryId.equals(categoryEditRequest.getCategory().getId())) {
            throw new InvalidRequestException("Category ID's mismatch");
        }
        User user = (User) userDetailsService.loadUserByUsername(principal.getName());
        return categoryService.editCategory(categoryEditRequest.getCategory(), user);
    }

    @DeleteMapping("/{categoryId}")
    public CategoryEditResponse delete(@PathVariable("categoryId") Long categoryId,
                                       Principal principal) {
        User user = (User) userDetailsService.loadUserByUsername(principal.getName());
        return categoryService.deleteCategoryByIdAndAuthor(categoryId, user);
    }
}
