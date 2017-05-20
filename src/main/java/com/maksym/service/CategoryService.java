package com.maksym.service;

import com.maksym.model.Category;
import com.maksym.model.HospitalStaff;

import java.util.List;

public interface CategoryService {
    Category findById(Integer id);

    void saveCategory(Category category);

    void updateCategory(Category category);

    void deleteCategoryById(Integer id);

    void deleteAllCategories();

    List<Category> findAllCategories();

    boolean isCategoryExist(Category category);

    List<HospitalStaff> findAllHospitalStaffs(Category category);
}
