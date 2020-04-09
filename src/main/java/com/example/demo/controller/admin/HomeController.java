package com.example.demo.controller.admin;

import com.example.demo.dto.PostDTO;
import com.example.demo.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller(value = "HomeControllerOfAdmin")
public class HomeController {

    @Autowired
    private IPostService postService;

    @GetMapping("/admin/home")
    public String listPage(Model model, @RequestParam(value="message", required = false) String message) {
        List<PostDTO> posts = postService.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("message", message);
        return "list_post";
    }
}
