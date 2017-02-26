package com.vitorreis.watermark.document;

class QueueItem {
    enum QueueItemStatus {
        IN_PROCESSING,
        PROCESSED
    }

    private final QueueItemStatus status;

    public QueueItem(Document document) {
        this.status = document.getWatermark() == null ? QueueItemStatus.IN_PROCESSING : QueueItemStatus.PROCESSED;
    }

    public QueueItemStatus getStatus() {
        return status;
    }
}
