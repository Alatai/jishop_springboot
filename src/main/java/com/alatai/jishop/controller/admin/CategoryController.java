package com.alatai.jishop.controller.admin;

import com.alatai.jishop.entity.Category;
import com.alatai.jishop.service.CategoryService;
import com.alatai.jishop.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alatai
 * @version 1.0
 * @date 2021/07/12 18:17
 */
@RestController // 每个方法的返回值都会直接转换为JSON格式数据
@RequestMapping("/admin/data")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public PageResult<Category> list(@RequestParam(value = "start", defaultValue = "0") int start,
                                     @RequestParam(value = "size", defaultValue = "5") int size) {
        start = start < 0 ? 0 : start;

        return categoryService.findAll(start, size, 5);
    }

    @PostMapping("/categories")
    public void add(@RequestBody Category category) {
        categoryService.insert(category);
    }

    @DeleteMapping("/categories/{id}")
    public void delete(@PathVariable("id") int id) {
        categoryService.deleteById(id);
    }

    @PutMapping("/categories")
    public void update(@RequestBody Category category) {
        categoryService.update(category);
    }
}
