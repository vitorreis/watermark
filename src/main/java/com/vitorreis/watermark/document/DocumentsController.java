package com.vitorreis.watermark.document;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class DocumentsController {
    private final DocumentsService documentsService;

    public DocumentsController(DocumentsService documentsService) {
        this.documentsService = documentsService;
    }

    @RequestMapping(path = "/v1/documents", method = RequestMethod.POST)
    public void newDocument(
        @RequestBody Document document,
        HttpServletResponse response
    ) {
        documentsService.save(document);

        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        response.addHeader("Location", "/v1/queue/" + document.getTicketId());
    }

    @RequestMapping(path = "/v1/documents/{id}/watermark", method = RequestMethod.GET)
    public Watermark getWatermark(
        @PathVariable Long id
    ) {
        return documentsService.findById(id).get().getWatermark();
    }

    @RequestMapping(path = "/v1/queue/{ticketId}", method = RequestMethod.GET)
    public QueueItem checkQueue(
        @PathVariable Long ticketId,
        HttpServletResponse response
    ) {
        QueueItem queueItem = new QueueItem(documentsService.findById(ticketId).get());
        if (queueItem.getStatus() == QueueItem.QueueItemStatus.PROCESSED) {
            response.addHeader("Location",  "/v1/documents/" + ticketId + "/watermark");
        }

        return queueItem;
    }
}
