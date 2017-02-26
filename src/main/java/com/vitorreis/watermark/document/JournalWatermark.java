package com.vitorreis.watermark.document;

public class JournalWatermark extends Watermark {

    public JournalWatermark(String title, String author) {
        super(title, author);
    }

    @Override
    public Content getContent() {
        return Content.JOURNAL;
    }
}
