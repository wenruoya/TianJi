package org.wr.tianji.service;


public interface StatusService {
    void changeStatus(Long id, Integer recognizedriskCode);

    void chageDevStatus(Long reportId,String description, Integer rejectedCode);

    void chageSecStatus(Long reportId, String description, Integer processedCode);
}
