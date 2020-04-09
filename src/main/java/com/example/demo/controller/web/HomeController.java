package com.example.demo.controller.web;

import com.example.demo.dto.PostDTO;
import com.example.demo.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

    @Autowired
    private IPostService postService;

    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public String homePage(Model model) {
        PostDTO postDTO = new PostDTO();
        postDTO.setListResults(postService.findAll());
        List<PostDTO> posts = postDTO.getListResults();
        model.addAttribute("posts", posts);
        return "index";
    }

}
