package com.alatai.jishop.controller.admin;

import com.alatai.jishop.entity.Property;
import com.alatai.jishop.service.PropertyService;
import com.alatai.jishop.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PropertyController
 *
 * @author M20W0324 saihou
 * @version 1.0
 * @date 2021/07/15 10:00
 */
@RestController("propertyController")
@RequestMapping("/admin/data")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping("/categories/{cid}/properties")
    public PageResult<Property> list(@PathVariable("cid") Integer cid,
                                     @RequestParam(value = "start", defaultValue = "0") Integer start,
                                     @RequestParam(value = "size", defaultValue = "5") Integer size) {
        start = start < 0 ? 0 : start;

        return propertyService.findAll(cid, start, size, 5);
    }

    @PostMapping("/properties")
    public void add(@RequestBody Property property) {
        propertyService.insert(property);
    }

    @DeleteMapping("/properties/{id}")
    public void delete(@PathVariable("id") Integer id) {
        propertyService.deleteById(id);
    }

    @PutMapping("/properties")
    public void update(@RequestBody Property property) {
        propertyService.update(property);
    }
}
