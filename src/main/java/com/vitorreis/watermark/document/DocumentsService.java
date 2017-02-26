package com.vitorreis.watermark.document;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

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

    List<Document> getDocumentsWithoutWatermark() {
        return documents.values().stream()
            .filter(document -> document.getWatermark() == null)
            .collect(Collectors.toList());
    }

    void setDocumentWatermark(Long documentTicket, Watermark watermark) {
        documents.get(documentTicket).setWatermark(watermark);
    }
}
