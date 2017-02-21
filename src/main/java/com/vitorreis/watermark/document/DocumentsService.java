package com.vitorreis.watermark.document;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

class DocumentsService {
    private final AtomicLong counter = new AtomicLong();
    private static DocumentsService instance = null;

    // in memory db
    // todo: replace with another type of persistance
    private final HashMap<Long, Document> documents = new HashMap<>();

    static DocumentsService getInstance() {
        if(instance == null) {
            synchronized(DocumentsService.class) {
                if (instance == null) {
                    instance = new DocumentsService();
                }
            }
        }

        return instance;
    }

    void save(Document document) {
        if (document.getId() != null && document.getId() != 0L) {
            throw new RuntimeException("Document already exists");
        }

        document.setId(counter.incrementAndGet());

        documents.put(document.getId(), document);
    }
}
