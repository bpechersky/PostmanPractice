package com.conduit.model;

public class ArticleRequest {
    private Article article;

    public ArticleRequest() {}

    public ArticleRequest(Article article) {
        this.article = article;
    }

    public Article getArticle() { return article; }
    public void setArticle(Article article) { this.article = article; }
}
