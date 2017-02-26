package com.vitorreis.watermark.document;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
class DocumentsService {
    private final AtomicLong counter = new AtomicLong();

    // in memory db
    // todo: replace with another type of persistance
    private final HashMap<Long, Document> documents = new HashMap<>();

    void save(Document document) {
        if (document.getTicketId() != null && document.getTicketId() != 0L) {
            throw new RuntimeException("Document already exists");
        }

        document.setTicketId(counter.incrementAndGet());

        documents.put(document.getTicketId(), document);
    }

    Optional<Document> findById(Long id) {
        return Optional.ofNullable(documents.get(id));
    }
}
