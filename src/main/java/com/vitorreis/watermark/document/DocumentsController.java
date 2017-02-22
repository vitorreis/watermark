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
    public DocumentResponse newDocument(
        @RequestBody Document document,
        HttpServletResponse response
    ) {
        documentsService.save(document);

        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        return new DocumentResponse(document);
    }

    @RequestMapping(path = "/v1/documents/{id}/watermark", method = RequestMethod.GET)
    public Watermark getWatermark(
        @PathVariable Long id
    ) {
        return documentsService.findById(id).get().getWatermark();
    }
}
