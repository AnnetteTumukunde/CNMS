package rw.auca.cnms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.auca.cnms.model.Nutrition;
import rw.auca.cnms.service.INutritionService;

import java.util.List;

@RestController
@RequestMapping("/nutrition")
public class NutritionController {

    @Autowired
    private INutritionService nutritionService;

    @PostMapping("/admin/new_nutrition")
    public ResponseEntity<Nutrition> createNutrition(@RequestBody Nutrition nutrition) {
        return new ResponseEntity<>(nutritionService.registerNutrition(nutrition), HttpStatus.CREATED);
    }

    @PutMapping("/admin/update_nutrition/{id}")
    public ResponseEntity<Nutrition> updateExistingNutrition(@PathVariable("id") Long id, @RequestBody Nutrition nutrition) {
        return new ResponseEntity<>(nutritionService.updateNutrition(nutrition, id), HttpStatus.OK);
    }

    @DeleteMapping("/admin/delete_nutrition/{id}")
    public ResponseEntity<String> deleteExistingNutrition(@PathVariable("id") Long id) {
        nutritionService.deleteNutrition(id);
        return new ResponseEntity<>("Nutrition deleted successfully!", HttpStatus.OK);
    }

    @GetMapping("/view_nutrition/{id}")
    public ResponseEntity<Nutrition> viewOneNutrition(@PathVariable("id") Long id) {
        return new ResponseEntity<>(nutritionService.findOneNutrition(id), HttpStatus.FOUND);
    }

    @GetMapping("/view_nutritions")
    public ResponseEntity<List<Nutrition>> viewNutritions() {
        return new ResponseEntity<>(nutritionService.findAllNutritions(), HttpStatus.FOUND);
    }
}
