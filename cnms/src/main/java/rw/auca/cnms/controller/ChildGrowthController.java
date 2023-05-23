package rw.auca.cnms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rw.auca.cnms.model.Child;
import rw.auca.cnms.model.ChildGrowth;
import rw.auca.cnms.model.Users;
import rw.auca.cnms.service.IChildGrowthService;

import java.util.List;

@Controller
public class ChildGrowthController {

    @Autowired
    private IChildGrowthService childGrowthService;

    @RequestMapping(value = {"/user/new_child_growth"}, method = RequestMethod.GET)
    public String showNewChildGrowthRegistration(Model model) {
        try {
            model.addAttribute("childGrowth", new ChildGrowth());
            return "ChildqGrowthRegistration";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/user/new_child_growth")
    public String saveNewChildGrowth(@ModelAttribute("childGrowth") ChildGrowth childGrowth) {
        try {
            childGrowthService.addChildGrowth(childGrowth);
            return "redirect:/childGrowth";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/user/update_child_growth/{id}")
    public String updateExistingChildGrowth(@PathVariable("id") Long id, @ModelAttribute ChildGrowth childGrowth) {
        try {
            childGrowthService.updateChildGrowth(childGrowth, id);
            return "ChildGrowthEdit";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/user/delete_child_growth/{id}")
    public String deleteExistingChildGrowth(@PathVariable("id") Long id) {
        try {
            childGrowthService.deleteChildGrowth(id);
            return "redirect:/user/view_child_growth";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = { "/user/view_child_growth/{id}", "/admin/view_child_growth/{id}" }, method = RequestMethod.GET)
    public String viewOneChildGrowth(@PathVariable("id") Long id) {
        try {
            childGrowthService.findOneChildGrowth(id);
            return "ViewChildGrowth";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/childGrowth")
    public String viewChildGrowth(Model model, @RequestParam(defaultValue = "0") int page) {
        try {
            int pageSize = 10;
            Pageable pageable = PageRequest.of(page, pageSize);

            Page<ChildGrowth> childGrowthPage = childGrowthService.getPaginatedChildGrowths(pageable);
            List<ChildGrowth> childGrowths = childGrowthPage.getContent();
            model.addAttribute("childGrowthList", childGrowths);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", childGrowthPage.getTotalPages());
            return "ChildGrowth";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
