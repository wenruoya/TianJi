package org.wr.tianji.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.wr.tianji.pojo.dto.RiskPageQueryDTO;
import org.wr.tianji.pojo.entity.Risk;


@Mapper
public interface RiskMapper {

    /**
     * 添加风险
     * @param risk
     */
    @Insert("insert into tianji.risk(risk_id, risk_name, repair_period) " +
            "VALUES " +
            "(#{riskId},#{riskName},#{repairPeriod})")
    void insert(Risk risk);

    /**
     * 删除风险
     * @param id
     */
    @Delete("delete from tianji.risk where risk_id = #{id}")
    void delete(Long id);

    /**
     * 分页查询
     * @param riskPageQueryDTO
     * @return
     */
    Page<Risk> pageQuery(RiskPageQueryDTO riskPageQueryDTO);

    /**
     * 更新
     * @param risk
     */
    void update(Risk risk);
}
