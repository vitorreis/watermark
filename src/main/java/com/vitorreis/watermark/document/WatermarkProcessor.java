package com.vitorreis.watermark.document;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class WatermarkProcessor {
    private static final Logger log = LoggerFactory.getLogger(WatermarkProcessor.class);
    private final DocumentsService documentsService;

    public WatermarkProcessor(DocumentsService documentsService) {
        this.documentsService = documentsService;
    }

    void processDocuments() {
        List<Document> documents = documentsService.getDocumentsWithoutWatermark();

        log.info("Processing {} documents", documents.size());

        // process books
        documents.stream()
            .filter(document -> document.getContent() == Content.BOOK)
            .forEach(document -> {
                BookWatermark bookWatermark = new BookWatermark(
                    document.getTitle(),
                    document.getAuthor().getName(),
                    document.getTopic()
                );
                documentsService.setDocumentWatermark(document.getTicketId(), bookWatermark);
            });

        // process journals
        documents.stream()
            .filter(document -> document.getContent() == Content.JOURNAL)
            .forEach(document -> {
                JournalWatermark journalWatermark = new JournalWatermark(
                    document.getTitle(),
                    document.getAuthor().getName()
                );
                documentsService.setDocumentWatermark(document.getTicketId(), journalWatermark);
            });
    }
}
