package com.justindodson.rusticridesgarage.blog.service;

import com.justindodson.rusticridesgarage.blog.model.entity.Post;
import com.justindodson.rusticridesgarage.blog.model.reository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

@Service
public class BlogPostServiceImpl implements BlogPostService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlogPostServiceImpl.class);
    private final PostRepository postRepository;

    @Autowired
    public BlogPostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void createOrUpdatePost(Post post) {
        post.setPublished(true);

        Optional<Post> postOptional = postRepository.findById(post.getId());
        if(postOptional.isPresent()) {
            LOGGER.info("Updating post in BlogPostService class...");
            Post updated = postOptional.get();
            updated.setTitle(post.getTitle());
            updated.setBody(post.getBody());
            postRepository.save(updated);
        } else {
            postRepository.save(post);
            LOGGER.info("Creating new article with id: " + post.getId());
        }
    }

    @Override
    public void savePostDraft(Post post) {
        postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getAllDraftPosts() {
        List<Post> drafts = new ArrayList<>();
        List<Post> allPosts = postRepository.findAll();
        allPosts.forEach(post -> {
            if(!post.isPublished()) {
                drafts.add(post);
            }
        });

        return drafts;
    }

    @Override
    public List<Post> getAllPublishedPosts() {
        List<Post> publishedList = new ArrayList<>();
        List<Post> allPosts = postRepository.findAll();

        allPosts.forEach(post -> {
            if(post.isPublished()){
                publishedList.add(post);
            }
        });

        return publishedList;
    }

    @Override
    public Post getPostById(long id) {
        Optional<Post> found = postRepository.findById(id);
        if(found.isPresent()) {
            return found.get();
        }
        return null;
    }

    @Override
    public Post getPostByTitle(String title) {
        return postRepository.findPostByTitle(title);
    }

    @Override
    public void deletePost(Post post) {
        postRepository.delete(post);
    }
}
