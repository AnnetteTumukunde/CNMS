package rw.auca.cnms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rw.auca.cnms.model.Nutrition;
import rw.auca.cnms.model.Users;
import rw.auca.cnms.service.INutritionService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/nutrition")
public class NutritionController {

    @Autowired
    private INutritionService nutritionService;

    @PostMapping("/admin/new_nutrition")
    public String createNutrition(@ModelAttribute Nutrition nutrition, @RequestParam("pictureFile") MultipartFile pictureFile) {
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
    }

    @PutMapping("/admin/update_nutrition/{id}")
    public String updateExistingNutrition(@PathVariable("id") Long id, @ModelAttribute Nutrition nutrition) {
        nutritionService.updateNutrition(nutrition, id);
        return "redirect:/Nutrition";
    }

    @DeleteMapping("/admin/delete_nutrition/{id}")
    public String deleteExistingNutrition(@PathVariable("id") Long id) {
        nutritionService.deleteNutrition(id);
        return "redirect:/Nutrition";
    }

    @GetMapping("/view_nutrition/{id}")
    public String viewOneNutrition(@PathVariable("id") Long id) {
        nutritionService.findOneNutrition(id);
        return "ViewNutrition";
    }

    @GetMapping("/view_nutritions")
    public String findAllNutritions(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Nutrition> nutritionPage = nutritionService.getPaginatedNutritions(pageable);
        List<Nutrition> nutritions = nutritionPage.getContent();
        model.addAttribute("nutritionsList", nutritions);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", nutritionPage.getTotalPages());
        return "Nutrition";
    }
}
