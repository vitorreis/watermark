package com.vitorreis.watermark.document;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
class DocumentsService {
    private final AtomicLong counter = new AtomicLong();

    // in memory db
    // todo: replace with another type of persistance
    private final HashMap<Long, Document> documents = new HashMap<>();

    void save(Document document) {
        if (document.getId() != null && document.getId() != 0L) {
            throw new RuntimeException("Document already exists");
        }

        document.setId(counter.incrementAndGet());

        documents.put(document.getId(), document);
    }
}
