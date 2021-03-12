package com.tunix70.javaio.model;

public class Post {
    private Long id;
    private String content;
    private Long created;
    private Long updated;
    private PostStatus postStatus;

    public Post() {
    }

    public Post( String content, Long created, Long updated, PostStatus postStatus) {
        this.content = content;
        this.created = created;
        this.updated = updated;
        this.postStatus = postStatus;
    }

    public Post(Long id, String content, Long created, Long updated, PostStatus postStatus) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.updated = updated;
        this.postStatus = postStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    public PostStatus getPostStatus() {
        return postStatus;
    }

    public void setPostStatus(PostStatus postStatus) {
        this.postStatus = postStatus;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", postStatus=" + postStatus +
                '}';
    }
}