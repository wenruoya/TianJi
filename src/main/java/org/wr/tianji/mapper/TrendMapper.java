package org.wr.tianji.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.wr.tianji.pojo.entity.RiskCount;
import org.wr.tianji.pojo.entity.VulnCount;

import java.util.List;
import java.util.Map;

@Mapper
public interface TrendMapper {

    @Select("SELECT COUNT(*) FROM tianji.report")
    Integer getTotal();

    @Select("SELECT COUNT(*) FROM tianji.report WHERE report_period > CURRENT_TIMESTAMP ")
    Integer getExtended();

    @Select("SELECT COUNT(*) FROM tianji.report WHERE create_time >= DATE_SUB(CURRENT_TIMESTAMP, INTERVAL 7 DAY)")
    Integer getSevenDay();

    Integer getSumByStatus(Integer status);


    List<RiskCount> getCountRiskId();

    List<VulnCount>  getCountVulnId();

    @Select("SELECT COUNT(*)  FROM tianji.report WHERE status=200 or status=201")
    Integer getFinish();
}
