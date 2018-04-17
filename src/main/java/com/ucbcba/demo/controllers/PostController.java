package com.ucbcba.demo.controllers;

import com.ucbcba.demo.entities.Comment;
import com.ucbcba.demo.entities.Post;
import com.ucbcba.demo.entities.PostCategory;
import com.ucbcba.demo.entities.User;
import com.ucbcba.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;

@Controller
public class PostController {
    private PostService postService;
    private PostCategoryService postCategoryService;
    private UserService userService;

    @Autowired
    public void setPostService(PostService productService) {
        this.postService = productService;
    }

    @Autowired
    public void setPostCategoryService(PostCategoryService postCategoryService) {
        this.postCategoryService = postCategoryService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public String list(Model model) {
        Iterable<Post> postList = postService.listAllPosts();
        model.addAttribute("postList", postList);
        return "posts";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        Iterable<Post> postList = postService.listAllPosts();
        model.addAttribute("postList", postList);
        return "index";
    }

    @RequestMapping("/newPost")
    String newPost(Model model) {
        Iterable<PostCategory> postCategories = postCategoryService.listAllPostCategorys();
        Iterable<User> users = userService.listAllUsers();
        model.addAttribute("postCategories", postCategories);
        model.addAttribute("users", users);
        return "newPost";
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    String save(Post post) {
        String[] textWords = Arrays.stream(post.getText().split(" "))
                .filter(element -> element != null && element.length() > 0).toArray(String[]::new);
        String[] titleWords = Arrays.stream(post.getTitle().split(" "))
                .filter(element -> element != null && element.length() > 0).toArray(String[]::new);
        if (textWords.length != 0 && textWords.length < 100 && titleWords.length != 0 && titleWords.length < 20) {
            postService.savePost(post);
        }
        return "redirect:/posts";
    }

    @RequestMapping("/post/{id}")
    String show(@PathVariable Integer id, Model model) {
        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        return "show";
    }

    @RequestMapping("/editPost/{id}")
    String editPost(@PathVariable Integer id, Model model) {
        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        Iterable<PostCategory> postCategories = postCategoryService.listAllPostCategorys();
        model.addAttribute("postCategories", postCategories);
        Iterable<User> users = userService.listAllUsers();
        model.addAttribute("users", users);
        return "editPost";
    }

    @RequestMapping("/deletePost/{id}")
    String delete(@PathVariable Integer id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }

    @RequestMapping("/like/{id}")
    String like(@PathVariable Integer id) {
        Post post = postService.getPost(id);
        post.setLikes(post.getLikes()+1);
        postService.savePost(post);
        return "redirect:/post/"+post.getId();
    }

    @RequestMapping("/dislike/{id}")
    String dislike(@PathVariable Integer id) {
        Post post = postService.getPost(id);
        if (post.getLikes() > 0) {
            post.setLikes(post.getLikes() - 1);
            postService.savePost(post);
        }
        return "redirect:/post/"+post.getId();
    }
}
