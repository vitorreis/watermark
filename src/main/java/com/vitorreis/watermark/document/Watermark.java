package com.vitorreis.watermark.document;

import com.vitorreis.watermark.document.Content;

abstract class Watermark {
    private String title;
    private String author;
    public abstract Content getContent();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
