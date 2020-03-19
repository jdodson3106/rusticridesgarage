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
        // TODO: 3/18/20 : Create separate save and update methods for articles
        post.setPublished(true);

        Optional<Post> found = postRepository.findById(post.getId());

        if(found.isPresent()) {
            LOGGER.info("Found article with id:" + post.getId());
            Post updated = found.get();
            updated.setBody(post.getBody());
            updated.setDate(post.getDate());
            updated.setTitle(post.getTitle());
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
