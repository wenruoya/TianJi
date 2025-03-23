package org.wr.tianji.service;

import org.wr.tianji.common.result.PageResult;
import org.wr.tianji.pojo.dto.RiskDTO;
import org.wr.tianji.pojo.dto.RiskPageQueryDTO;
import org.wr.tianji.pojo.entity.Risk;

import java.util.List;

public interface RiskService {
    // 增加
    void insert(RiskDTO riskDTO);
    // 分页
    PageResult pageQuery(RiskPageQueryDTO riskPageQueryDTO);
    // 更新
    void update(Risk risk);
    // 批量删除
    void deleteBatch(List<Long> idList);
}
