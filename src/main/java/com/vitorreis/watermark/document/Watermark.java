package com.vitorreis.watermark.document;

abstract class Watermark {
    private final String title;
    private final String author;

    Watermark(String title, String author) {
        this.title = title;
        this.author = author;
    }

    abstract Content getContent();

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
