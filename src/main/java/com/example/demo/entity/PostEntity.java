package com.example.demo.entity;

import org.hibernate.search.annotations.*;

import javax.persistence.*;

@Entity
@Indexed
@Table(name = "post")
public class PostEntity extends BaseEntity{

    @Column(name = "title")
    @Field(termVector = TermVector.YES, analyze= Analyze.YES, store= Store.NO)
    private String title;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "shortdescription", columnDefinition = "TEXT")
    private String shortDescription;

    @Column(name = "content", columnDefinition = "TEXT")
    @Field(termVector = TermVector.YES, analyze=Analyze.YES, store=Store.NO)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryid")
    private CategoryEntity category;

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
