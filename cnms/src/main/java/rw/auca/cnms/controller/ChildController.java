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
import rw.auca.cnms.model.Users;
import rw.auca.cnms.repository.IChildRepository;
import rw.auca.cnms.service.IChildService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ChildController {

    @Autowired
    private IChildService childService;

    @RequestMapping(value = {"/user/new_child"}, method = RequestMethod.GET)
    public String showNewChildRegistration(Model model) {
        try {
            model.addAttribute("child", new Child());
            return "ChildRegistration";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/user/new_child")
    public String registerNewChild(@ModelAttribute("child") Child child) {
        try {
            child.setDateOfBirth(new Date());
            childService.registerChild(child);
            return "redirect:/children";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/user/update_child/{id}")
    public String updateExistingChild(@PathVariable("id") Long id, @ModelAttribute("child") Child child) {
        try {
            childService.updateChild(child, id);
            return "ChildEdit";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/user/delete_child/{id}")
    public String deleteExistingChild(@PathVariable("id") Long id) {
        try {
            childService.deleteChild(id);
            return "redirect:/Children";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = { "/user/view_child/{id}", "/admin/view_child/{id}" }, method = RequestMethod.GET)
    public String viewOneChild(@PathVariable("id") Long id) {
        try {
            childService.findOneChild(id);
            return "ViewChild";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/children")
    public String viewChildren(Model model, @RequestParam(defaultValue = "0") int page) {
        try {
            int pageSize = 10;
            Pageable pageable = PageRequest.of(page, pageSize);

            Page<Child> childPage = childService.getPaginatedChildren(pageable);
            List<Child> children = childPage.getContent();
            model.addAttribute("childrenList", children);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", childPage.getTotalPages());
            return "Children";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
