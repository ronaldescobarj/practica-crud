package com.ucbcba.demo.entities;


import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String text;

    @NotNull
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull
    @Min(0)
    @Column(columnDefinition="int(11) default 0")
    private Integer likes=0;

    @ManyToOne
    @JoinColumn(name = "post_category_id")
    private PostCategory postCategory;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Column(columnDefinition="int(11) default 0")
    private Integer showPost=0;

    @NotNull
    private String creationDate;

   public Integer getShowPost() {
        return showPost;
    }

    public void setShowPost(Integer showPost) {
        this.showPost = showPost;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Post() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public PostCategory getPostCategory() {
        return postCategory;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public void setPostCategory(PostCategory postCategory) {
        this.postCategory = postCategory;
    }

    public void setComments(List<Comment> comments){
        this.comments = comments;
    }
    public List<Comment> getComments(){
        return this.comments;
    }
}
