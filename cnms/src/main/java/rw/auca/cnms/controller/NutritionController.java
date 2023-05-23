package rw.auca.cnms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rw.auca.cnms.model.Nutrition;
import rw.auca.cnms.service.INutritionService;

import java.io.IOException;
import java.util.List;

@Controller
public class NutritionController {

    @Autowired
    private INutritionService nutritionService;

    @PostMapping("/admin/new_nutrition")
    public String createNutrition(@ModelAttribute Nutrition nutrition, @RequestParam("pictureFile") MultipartFile pictureFile) {
        try {
            if (!pictureFile.isEmpty()) {
                try {
                    byte[] picture = pictureFile.getBytes();
                    nutrition.setFileName(pictureFile.getOriginalFilename());
                    nutrition.setPicture(picture);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            nutritionService.registerNutrition(nutrition, pictureFile);
            return "redirect:/Nutrition";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/admin/update_nutrition/{id}")
    public String updateExistingNutrition(@PathVariable("id") Long id, @ModelAttribute Nutrition nutrition) {
        try {
            nutritionService.updateNutrition(nutrition, id);
            return "redirect:/Nutrition";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/admin/delete_nutrition/{id}")
    public String deleteExistingNutrition(@PathVariable("id") Long id) {
        try {
            nutritionService.deleteNutrition(id);
            return "redirect:/Nutrition";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/view_nutrition/{id}")
    public String viewOneNutrition(@PathVariable("id") Long id) {
        try {
            nutritionService.findOneNutrition(id);
            return "ViewNutrition";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/nutritions")
    public String findAllNutritions(Model model, @RequestParam(defaultValue = "0") int page) {
        try {
            int pageSize = 10;
            Pageable pageable = PageRequest.of(page, pageSize);

            Page<Nutrition> nutritionPage = nutritionService.getPaginatedNutritions(pageable);
            List<Nutrition> nutritions = nutritionPage.getContent();
            model.addAttribute("nutritionList", nutritions);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", nutritionPage.getTotalPages());
            return "Nutrition";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
