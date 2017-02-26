package com.vitorreis.watermark.document;

class DocumentResponse {
    private final Long id;

    public DocumentResponse(Document document) {
        this.id = document.getTicketId();
    }

    public Long getId() {
        return id;
    }
}
