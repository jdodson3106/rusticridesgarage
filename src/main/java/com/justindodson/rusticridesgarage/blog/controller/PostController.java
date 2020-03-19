package com.justindodson.rusticridesgarage.blog.controller;

import com.justindodson.rusticridesgarage.blog.model.entity.Post;
import com.justindodson.rusticridesgarage.blog.service.BlogPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class PostController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostController.class);

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

        // redirect to all posts
        return "redirect:/admin/all-posts";
    }

    @GetMapping("/admin/all-posts")
    public String viewAllPosts(Model model, HttpServletRequest request) {
        Map<String, ?> inputMessages = RequestContextUtils.getInputFlashMap(request);
        if(inputMessages != null && !inputMessages.isEmpty()) {
            if(inputMessages.containsKey("success")){
                model.addAttribute("messageType", "success");
                model.addAttribute("message", inputMessages.get("success"));
            } else if(inputMessages.containsKey("error")) {
                model.addAttribute("messageType", "error");
                model.addAttribute("message", inputMessages.get("error"));
            }
        }
        model.addAttribute("posts", blogPostService.getAllPosts());

        return "admin/all_posts";
    }

    @GetMapping("/admin/delete/article")
    public String deletePost(@RequestParam(value = "id", required = true) long id, Model model, RedirectAttributes attributes) {
        Post post = blogPostService.getPostById(id);
        if(post != null) {
            String title = post.getTitle();
            attributes.addFlashAttribute("success", "Article: \"" + title + "\" was successfully deleted.");
            blogPostService.deletePost(post);
        } else {
            attributes.addFlashAttribute("error", "There was an error deleting the article.");
        }
        return "redirect:/admin/all-posts";
    }

    @GetMapping("/admin/edit/article")
    public String editArticle(@RequestParam(value="id", required = true) long id, Model model, RedirectAttributes attributes) {
        Post post = blogPostService.getPostById(id);
        LOGGER.info("Found article " + post.getTitle());
        model.addAttribute("article", post);
        return "admin/edit_post";
    }

    @PostMapping("/admin/edit/article/")
    public String editArticleProcessor(@ModelAttribute("article") Post article, Model model) {
        LOGGER.info("Updating post with id: " + article.getId());
        blogPostService.createOrUpdatePost(article);
        return "redirect:/admin/all-posts";
    }


}
