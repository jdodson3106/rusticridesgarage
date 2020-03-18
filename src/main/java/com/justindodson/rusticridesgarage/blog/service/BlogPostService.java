package com.justindodson.rusticridesgarage.blog.service;

import com.justindodson.rusticridesgarage.blog.model.entity.Post;

import java.util.List;

public interface BlogPostService {
    void createOrUpdatePost(Post post);
    void savePostDraft(Post post);
    List<Post> getAllPosts();
    List<Post> getAllDraftPosts();
    List<Post> getAllPublishedPosts();
    Post getPostById(long id);
    Post getPostByTitle(String title);
}
