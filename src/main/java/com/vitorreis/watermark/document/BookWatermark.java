package com.vitorreis.watermark.document;

class BookWatermark extends Watermark {
    private Topic topic;

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public Content getContent() {
        return Content.BOOK;
    }
}
