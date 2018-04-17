package com.ucbcba.demo.controllers;

import com.ucbcba.demo.entities.Comment;
import com.ucbcba.demo.entities.Post;
import com.ucbcba.demo.services.CommentService;
import com.ucbcba.demo.services.CommentServiceImpl;
import com.ucbcba.demo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.java2d.pipe.SpanShapeRenderer;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Controller
public class CommentController {

    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    String save(Comment comment) {
        String[] commentWords = Arrays.stream(comment.getText().split(" "))
                .filter(element -> element != null && element.length() > 0).toArray(String[]::new);
        if (commentWords.length != 0 && commentWords.length < 50) {
            Date date = new Date();
            SimpleDateFormat formatted = new SimpleDateFormat("dd-MM-yyyy");
            String cad = formatted.format(date);
            comment.setCreationDate(cad);
            commentService.saveComment(comment);
        }
        return "redirect:/post/" + comment.getPost().getId();
    }

    @RequestMapping("/commentLike/{id}")
    String like(@PathVariable Integer id) {
        Comment comment = commentService.getComment(id);
        comment.setLikes(comment.getLikes()+1);
        commentService.saveComment(comment);
        return "redirect:/post/"+comment.getPost().getId();
    }

    @RequestMapping("/commentDislike/{id}")
    String dislike(@PathVariable Integer id) {
        Comment comment = commentService.getComment(id);
        if (comment.getLikes() > 0) {
            comment.setLikes(comment.getLikes() - 1);
            commentService.saveComment(comment);
        }
        return "redirect:/post/"+comment.getPost().getId();
    }
}
