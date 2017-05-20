package com.maksym.controller.rest;

import com.maksym.model.Category;
import com.maksym.model.HospitalStaff;
import com.maksym.service.CategoryService;
import com.maksym.utils.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryApiController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/category/", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> listAllCategories() {
        List<Category> cities = categoryService.findAllCategories();
        if (cities.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public ResponseEntity<Category> getCategory(@PathVariable("id") Integer id) {
        Category category = categoryService.findById(id);
        if (category == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @RequestMapping(value = "/category/", method = RequestMethod.POST)
    public ResponseEntity<?> createCategory(@RequestBody Category category, UriComponentsBuilder ucBuilder) {
        if (categoryService.isCategoryExist(category)) {
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Category with name " +
                    category.getCategoryName() + " already exist."), HttpStatus.CONFLICT);
        }
        categoryService.saveCategory(category);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/category/{id}").buildAndExpand(category.getIdCat()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Category ------------------------------------------------

    @RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCategory(@PathVariable("id") int id, @RequestBody Category category) {
        Category currentCategory = categoryService.findById(id);

        if (currentCategory == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Category with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }


        categoryService.updateCategory(currentCategory);
        return new ResponseEntity<>(currentCategory, HttpStatus.OK);
    }

    // ------------------- Delete a Category-----------------------------------------

    @RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCategory(@PathVariable("id") int id) {
        Category category = categoryService.findById(id);
        if (category == null) {
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Category with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Categories-----------------------------

    @RequestMapping(value = "/category/", method = RequestMethod.DELETE)
    public ResponseEntity<Category> deleteAllCategories() {
        categoryService.deleteAllCategories();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/category/hospitalStaffs", method = RequestMethod.POST)
    public ResponseEntity<List<HospitalStaff>> getHospitalStaffByCategory(@RequestBody Category category) {
        List<HospitalStaff> hospitalStaffs = categoryService.findAllHospitalStaffs(category);
        if(category == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(hospitalStaffs, HttpStatus.OK);
    }
}
