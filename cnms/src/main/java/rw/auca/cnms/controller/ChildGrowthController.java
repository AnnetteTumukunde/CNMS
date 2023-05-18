package rw.auca.cnms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rw.auca.cnms.model.Child;
import rw.auca.cnms.model.ChildGrowth;
import rw.auca.cnms.model.Users;
import rw.auca.cnms.service.IChildGrowthService;

import java.util.List;

@RestController
@RequestMapping("/child_growth")
public class ChildGrowthController {

    @Autowired
    private IChildGrowthService childGrowthService;

    @PostMapping("/user/new_child_growth")
    public String saveNewChildGrowth(@ModelAttribute ChildGrowth childGrowth) {
        childGrowthService.addChildGrowth(childGrowth);
        return "redirect:/user/view_child_growth";
    }

    @PutMapping("/user/update_child_growth/{id}")
    public String updateExistingChildGrowth(@PathVariable("id") Long id, @ModelAttribute ChildGrowth childGrowth) {
        childGrowthService.updateChildGrowth(childGrowth, id);
        return "redirect:/user/view_child_growth";
    }

    @DeleteMapping("/user/delete_child_growth/{id}")
    public String deleteExistingChildGrowth(@PathVariable("id") Long id) {
        childGrowthService.deleteChildGrowth(id);
        return "redirect:/user/view_child_growth";
    }

    @RequestMapping(value = { "/user/view_child_growth/{id}", "/admin/view_child_growth/{id}" }, method = RequestMethod.GET)
    public String viewOneChildGrowth(@PathVariable("id") Long id) {
        childGrowthService.findOneChildGrowth(id);
        return "ViewChildGrowth";
    }

    @RequestMapping(value = { "/user/view_child_growth", "/admin/view_child_growth" }, method = RequestMethod.GET)
    public String viewChildGrowth(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<ChildGrowth> childGrowthPage = childGrowthService.getPaginatedChildGrowths(pageable);
        List<ChildGrowth> childGrowths = childGrowthPage.getContent();
        model.addAttribute("childGrowthList", childGrowths);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", childGrowthPage.getTotalPages());
        return "ChildGrowth";
    }
}
