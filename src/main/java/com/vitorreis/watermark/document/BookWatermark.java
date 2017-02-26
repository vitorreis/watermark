package com.vitorreis.watermark.document;

class BookWatermark extends Watermark {
    private final Topic topic;

    public BookWatermark(String title, String author, Topic topic) {
        super(title, author);
        this.topic = topic;
    }

    public Topic getTopic() {
        return topic;
    }

    @Override
    public Content getContent() {
        return Content.BOOK;
    }
}
