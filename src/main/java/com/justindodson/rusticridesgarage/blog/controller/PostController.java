package com.justindodson.rusticridesgarage.blog.controller;

import com.justindodson.rusticridesgarage.blog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
public class PostController {

    private final BlogPostService blogPostService;

    @Autowired
    public PostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

}
