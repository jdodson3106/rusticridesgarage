package com.justindodson.rusticridesgarage.blog.controller;

import com.justindodson.rusticridesgarage.blog.model.entity.Post;
import com.justindodson.rusticridesgarage.blog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

    private final BlogPostService blogPostService;

    @Autowired
    public PostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    /*
    *   Admin Section of the blog application
    */
    @GetMapping("/admin/new-post")
    public String createNewPost(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "admin/new_post";
    }

    @PostMapping("/admin/new-post")
    public String createNewPostProcessor(Model model, @ModelAttribute("post") Post post) {
        blogPostService.createOrUpdatePost(post);

        // redirect to blog post dashboard
        return "redirect:/dashboard";
    }

    @GetMapping("/admin/all-posts")
    public String viewAllPosts(Model model) {
        model.addAttribute("posts", blogPostService.getAllPosts());

        return "admin/all_posts";
    }
}
