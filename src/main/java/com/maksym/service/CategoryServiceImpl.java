package com.maksym.service;

import com.maksym.model.Category;
import com.maksym.model.HospitalStaff;
import com.maksym.repositories.CategoryRepository;
import com.maksym.repositories.HospitalStaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    HospitalStaffRepository hospitalStaffRepository;

    public Category findById(Integer id) {
        return categoryRepository.findOne(id);
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public void updateCategory(Category category) {
        saveCategory(category);
    }

    public void deleteCategoryById(Integer id) {
        categoryRepository.delete(id);
    }

    public void deleteAllCategories() {
        categoryRepository.deleteAll();
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public boolean isCategoryExist(Category category) {
        return categoryRepository.exists(category.getIdCat());
    }

    public List<HospitalStaff> findAllHospitalStaffs(Category category){
        return hospitalStaffRepository.findByIdCat(category);
    }
}
