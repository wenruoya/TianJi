package org.wr.tianji.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wr.tianji.mapper.StatusMapper;
import org.wr.tianji.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService {

    @Autowired
    private StatusMapper statusMapper;

    @Override
    public void changeStatus(Long id, Integer recognizedriskCode) {
        statusMapper.changeStatus(id,recognizedriskCode);
    }

    @Override
    public void chageDevStatus(Long reportId, String description, Integer recognizedriskCode) {
        statusMapper.chageDevStatus(reportId, description, recognizedriskCode);
    }

    @Override
    public void chageSecStatus(Long reportId, String description, Integer processedCode) {
        statusMapper.chageSecStatus(reportId, description, processedCode);
    }
}
