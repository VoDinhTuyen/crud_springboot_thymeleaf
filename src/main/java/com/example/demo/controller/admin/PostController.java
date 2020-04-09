package com.example.demo.controller.admin;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.dto.PostDTO;
import com.example.demo.entity.PostEntity;
import com.example.demo.service.ICategoryService;
import com.example.demo.service.IPostService;
import com.example.demo.validator.PostValidator;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

import javax.persistence.EntityManager;
import java.util.List;

@Controller(value = "HomeControllerOfPost")
@RequestMapping("/admin/post")
public class PostController {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private IPostService postService;

    @Autowired
    private PostValidator postValidator;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/add")
    public String addPage(Model model) {
        List<CategoryDTO> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("postForm", new PostDTO());
        return "add_post";
    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute("postForm") PostDTO postForm, BindingResult bindingResult, Model model) {
        postValidator.validate(postForm, bindingResult);
        List<CategoryDTO> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        if(bindingResult.hasErrors()) {
            return "add_post";
        }
        postService.save(postForm);
        return "redirect:/admin/home";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable("id") long id, Model model) {
        PostDTO postDTO = postService.findOne(id);
        List<CategoryDTO> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("post", postDTO);
        return "edit_post";
    }

    @PostMapping("/edit/{id}")
    public String editPage(@PathVariable("id") long id, @ModelAttribute("post") PostDTO post, BindingResult bindingResult, Model model) {
        postValidator.validate(post, bindingResult);
        if(bindingResult.hasErrors()) {
            List<CategoryDTO> categories = categoryService.findAll();
            model.addAttribute("categories", categories);
            post.setId(id);
            return "edit_post";
        }
        postService.update(post);
        return "redirect:/admin/home?message=Update success!";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable("id") long id, Model model) {
        postService.delete(id);
        return "redirect:/admin/home?message=Delete success!";
    }

    @GetMapping("/search")
    public String searchPage(@RequestParam("searchKey") String searchKey, Model model) {
        if(StringUtils.isEmpty(searchKey)) {
            return "redirect:/home";
        }
        String key = "%"+searchKey+"%";
        model.addAttribute("posts", postService.search(key));
        return "index";
    }
}
