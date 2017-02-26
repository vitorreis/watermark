package com.vitorreis.watermark.document;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private final WatermarkProcessor watermarkProcessor;

    public ScheduledTasks(WatermarkProcessor watermarkProcessor) {
        this.watermarkProcessor = watermarkProcessor;
    }

    @Scheduled(fixedRate = 5000)
    public void createWatermarks() {
        watermarkProcessor.processDocuments();
    }
}
