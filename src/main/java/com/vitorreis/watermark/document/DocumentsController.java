package com.vitorreis.watermark.document;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

@RestController
class DocumentsController {
    private final DocumentsService documentsService;

    DocumentsController(DocumentsService documentsService) {
        this.documentsService = documentsService;
    }

    @RequestMapping(path = "/v1/documents", method = RequestMethod.POST)
    void newDocument(
        @RequestBody Document document,
        HttpServletResponse response
    ) {
        documentsService.save(document);

        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        response.addHeader("Location", "/v1/queue/" + document.getTicketId());
    }

    @RequestMapping(path = "/v1/documents/{ticketId}/watermark", method = RequestMethod.GET)
    Watermark getWatermark(
        @PathVariable Long ticketId,
        HttpServletResponse response
    ) {
        Optional<Document> documentOptional = documentsService.findById(ticketId);

        if (documentOptional.isPresent()) {
            return documentOptional.get().getWatermark();
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }

    @RequestMapping(path = "/v1/queue/{ticketId}", method = RequestMethod.GET)
    QueueItem checkQueue(
        @PathVariable Long ticketId,
        HttpServletResponse response
    ) {
        Optional<Document> documentOptional = documentsService.findById(ticketId);
        if (documentOptional.isPresent()) {
            QueueItem queueItem = new QueueItem(documentOptional.get());
            if (queueItem.getStatus() == QueueItem.QueueItemStatus.PROCESSED) {
                response.addHeader("Location",  "/v1/documents/" + ticketId + "/watermark");
            }

            return queueItem;
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }
}
