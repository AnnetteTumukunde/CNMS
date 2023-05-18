package rw.auca.cnms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rw.auca.cnms.model.Nutrition;
import rw.auca.cnms.model.Serving;
import rw.auca.cnms.model.Users;
import rw.auca.cnms.service.IServingService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/serving")
public class ServingController {

    @Autowired
    private IServingService servingService;

    @PostMapping("/admin/new_serving")
    public String createNewServing(@ModelAttribute Serving serving, @RequestParam("recipeFile") MultipartFile recipeFile) {
        if (!recipeFile.isEmpty()) {
            try {
                byte[] recipe = recipeFile.getBytes();
                serving.setFileName(recipeFile.getOriginalFilename());
                serving.setRecipe(recipe);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        servingService.createServing(serving, recipeFile);
        return "redirect:/Serving";
    }

    @PutMapping("/admin/update_serving/{id}")
    public String updateExistingServing(@PathVariable("id") Long id, @ModelAttribute("serving") Serving serving) {
        servingService.updateServing(serving, id);
        return "EditServing";
    }

    @DeleteMapping("/admin/delete_serving/{id}")
    public String deleteExistingServing(@PathVariable("id") Long id) {
        servingService.deleteServing(id);
        return "/user/view_servings";
    }

    @RequestMapping(value = { "/user/view_serving/{id}", "/admin/view_serving/{id}" }, method = RequestMethod.GET)
    public String viewOneServing(@PathVariable("id") Long id) {
        servingService.findOneServing(id);
        return "ViewServing";
    }

    @RequestMapping(value = { "/user/view_servings", "/admin/view_servings" }, method = RequestMethod.GET)
    public String viewServings(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Serving> servingPage = servingService.getPaginatedServings(pageable);
        List<Serving> servings = servingPage.getContent();
        model.addAttribute("servingList", servings);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", servingPage.getTotalPages());
        return "Serving";
    }

}
