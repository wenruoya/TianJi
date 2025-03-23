package org.wr.tianji.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wr.tianji.mapper.TrendMapper;
import org.wr.tianji.pojo.entity.StatusCount;
import org.wr.tianji.pojo.vo.TrendVO;
import org.wr.tianji.service.TrendService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.wr.tianji.common.constant.StatusConstant.STATUSMAP;

@Service
@Slf4j
public class TrendServiceImpl implements TrendService {

    @Autowired
    private TrendMapper trendMapper;

    @Override
    public TrendVO getTrend() {
        TrendVO trendVO = new TrendVO();
        trendVO.setTotal(trendMapper.getTotal());
        trendVO.setExtended(trendMapper.getExtended());
        trendVO.setSevenDay(trendMapper.getSevenDay());
        trendVO.setFinish(trendMapper.getFinish());
        List<StatusCount> StatusList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : STATUSMAP.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            Integer tmp = trendMapper.getSumByStatus(value);
            StatusList.add(new StatusCount(key,tmp));
        }
        trendVO.setStatus(StatusList);

        trendVO.setRisk(trendMapper.getCountRiskId());
        trendVO.setVuln(trendMapper.getCountVulnId());


        return trendVO;
    }
}
