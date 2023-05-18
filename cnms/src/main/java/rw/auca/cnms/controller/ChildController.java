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
import rw.auca.cnms.model.Users;
import rw.auca.cnms.repository.IChildRepository;
import rw.auca.cnms.service.IChildService;

import java.util.List;

@RestController
@RequestMapping("/child")
public class ChildController {

    @Autowired
    private IChildService childService;

    @PostMapping("/user/new_child")
    public String registerNewChild(@ModelAttribute Child child) {
        childService.registerChild(child);
        return "redirect:/Children";
    }

    @PutMapping("/user/update_child/{id}")
    public String updateExistingChild(@PathVariable("id") Long id, @ModelAttribute Child child) {
        childService.updateChild(child, id);
        return "redirect:/Children";
    }

    @DeleteMapping("/user/delete_child/{id}")
    public String deleteExistingChild(@PathVariable("id") Long id) {
        childService.deleteChild(id);
        return "redirect:/Children";
    }

    @RequestMapping(value = { "/user/view_child/{id}", "/admin/view_child/{id}" }, method = RequestMethod.GET)
    public String viewOneChild(@PathVariable("id") Long id) {
        childService.findOneChild(id);
        return "ViewChild";
    }

    @RequestMapping(value = { "/user/view_children", "/admin/view_children" })
    public String viewChildren(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<Child> childPage = childService.getPaginatedChildren(pageable);
        List<Child> children = childPage.getContent();
        model.addAttribute("childrenList", children);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", childPage.getTotalPages());
        return "Children";
    }
}
