package com.vitorreis.watermark.document;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class DocumentsController {
    private final DocumentsService documentsService = DocumentsService.getInstance();

    @RequestMapping(path = "/v1/documents", method = RequestMethod.POST)
    public DocumentResponse newDocument(
        @RequestBody Document document,
        HttpServletResponse response
    ) {
        documentsService.save(document);

        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        return new DocumentResponse(document);
    }
}
