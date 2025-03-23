package org.wr.tianji.service;

import org.wr.tianji.common.result.PageResult;
import org.wr.tianji.pojo.dto.ReportDTO;
import org.wr.tianji.pojo.dto.ReportPageQueryByRoleDTO;
import org.wr.tianji.pojo.dto.ReportPageQueryDTO;
import org.wr.tianji.pojo.dto.ReportUserDTO;
import org.wr.tianji.pojo.vo.*;

import java.util.List;

public interface ReportService {
    // 删除
    void delete(Long id);
    // 根据id查询
    ReportVO getById(Long id);
    // 分页查询
    PageResult pageQuery(ReportPageQueryDTO reportPageQueryDTO);
    // 更新工单
    void update(ReportDTO reportDTO);
    // 新增工单
    void insert(ReportDTO reportDTO);
    // 延期工单
    void delay(Long id, Long delay);
    // 获取用户关联的工单
    PageResult pageQueryById(ReportPageQueryByRoleDTO role);
    // 报告填写辅助
    List<reportVulnVO> getVuln();
    List<reportRiskVO> getRisk();
    // 获取用户
    List<reportUserVO> getUser(Integer typeId);
    // 指派工单负责人
    void putUser(ReportUserDTO reportUserDTO, Integer typeId);

}
