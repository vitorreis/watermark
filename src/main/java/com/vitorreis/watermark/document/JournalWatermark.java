package com.vitorreis.watermark.document;

public class JournalWatermark extends Watermark {

    @Override
    public Content getContent() {
        return Content.JOURNAL;
    }
}
