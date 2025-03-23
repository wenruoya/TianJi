package org.wr.tianji.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wr.tianji.common.result.PageResult;
import org.wr.tianji.mapper.LogMapper;
import org.wr.tianji.mapper.ReportMapper;
import org.wr.tianji.pojo.dto.ReportDTO;
import org.wr.tianji.pojo.dto.ReportPageQueryByRoleDTO;
import org.wr.tianji.pojo.dto.ReportPageQueryDTO;
import org.wr.tianji.pojo.dto.ReportUserDTO;
import org.wr.tianji.pojo.entity.Report;
import org.wr.tianji.pojo.entity.User;
import org.wr.tianji.pojo.entity.Vuln;
import org.wr.tianji.pojo.vo.*;
import org.wr.tianji.service.ReportService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.wr.tianji.common.constant.ReportStatusConstant.RECOGNIZEDRISK_CODE;
import static org.wr.tianji.common.constant.ReportStatusConstant.UNRECOGNIZEDRISK_CODE;
import static org.wr.tianji.common.utils.StatusUtil.convertStatus;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;
    @Autowired
    private LogMapper logMapper;
    /**
     * 删除工单
     * @param id
     */
    @Override
    public void delete(Long id) {
        reportMapper.delete(id);
    }

    /**
     * 查询工单id
     * @param id
     * @return
     */
    @Override
    public ReportVO getById(Long id) {
        Report report = reportMapper.getById(id);

        return  ReportVO.builder()
                .reportId(report.getReportId())
                .reportName(report.getReportName())
                .reportUrl(report.getReportUrl())
                .reportApp(report.getReportApp())
                .reportContent(report.getReportContent())
                .reportSource(report.getReportSource())
                .status(report.getStatus())
                // 风险
                .riskId(report.getRiskId())
                .riskName(getRiskNameById(report.getRiskId()))
                // 漏洞
                .vulnId(report.getVulnId())
                .vulnerabilityType(getVulnById(report.getVulnId()).getVulnerabilityType())
                .vulnerabilitySuggest(getVulnById(report.getVulnId()).getVulnerabilitySuggest())
                // 安全人员
                .secUserId(report.getSecUserId())
                .secName(getUserNameById(report.getSecUserId()))
                // 开发人员
                .devUserId(report.getDevUserId())
                .devName(getUserNameById(report.getDevUserId()))
                .reportPeriod(report.getReportPeriod())
                .CreateTime(report.getCreateTime())
                .CreateUser(report.getCreateUser())
                .remark(report.getRemark())
                .feedback(report.getFeedback())
                .build();
    }

    /**
     * 分页工单
     * @param reportPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(ReportPageQueryDTO reportPageQueryDTO) {
        PageHelper.startPage(reportPageQueryDTO.getPage(),reportPageQueryDTO.getPageSize());
        Page<Report> page = reportMapper.pageQuery(reportPageQueryDTO);
        long total = page.getTotal();
        List<ReportPageVO> reportVOList = page.getResult().stream()
                .map(report -> {
                    ReportPageVO reportPageVO = new ReportPageVO();
                    reportPageVO.setReportId(report.getReportId());
                    reportPageVO.setReportName(report.getReportName());
                    reportPageVO.setRiskName(getRiskNameById(report.getRiskId()));
                    reportPageVO.setVulnerabilityType(getVulnById(report.getVulnId()).getVulnerabilityType());
                    reportPageVO.setReportPeriod(report.getReportPeriod());
                    reportPageVO.setStatus(convertStatus(report.getStatus()));
                    reportPageVO.setDevOwner(getUserNameById(report.getDevUserId()));
                    reportPageVO.setSecOwner(getUserNameById(report.getSecUserId()));
                    return reportPageVO;
                })
                .collect(Collectors.toList());
        return new PageResult(total,reportVOList);
    }

    /**
     * 更新工单
     * @param reportDTO
     */
    @Override
    public void update(ReportDTO reportDTO) {
        reportMapper.update(reportDTO);

    }

    /**
     * 新增工单
     * @param reportDTO
     */
    @Override
    public void insert(ReportDTO reportDTO) {
        Report report = new Report();
        int subtime = reportMapper.getRepairPeriodById(reportDTO.getRiskId());
        BeanUtils.copyProperties(reportDTO,report);
        report.setStatus(RECOGNIZEDRISK_CODE);
        report.setReportPeriod(LocalDateTime.now().plusHours(subtime));
        report.setCreateTime(LocalDateTime.now());

        reportMapper.insert(report);
    }

    /**
     * 延期工单
     * @param id
     * @param delay
     */
    @Override
    public void delay(Long id, Long delay) {
        LocalDateTime newTime =  getReportPeriodById(delay);
        reportMapper.delay(id,newTime);
    }

    /**
     * 获取用户关联的工单
     * @param
     * @return
     */

    @Override
    public PageResult pageQueryById(ReportPageQueryByRoleDTO reportPageQueryByRoleDTO) {
        PageHelper.startPage(reportPageQueryByRoleDTO.getPage(), reportPageQueryByRoleDTO.getPageSize());
        User user = getUserByUsername(reportPageQueryByRoleDTO.getUsername());
        List<Integer> statusList = null;
        if (user.getRole() == 3) {
            statusList = Arrays.asList(102);
        }else {
            statusList = Arrays.asList(100, 101, 103, 104);
        }

        Page<Report> page = reportMapper.pageQueryByUserId(user.getUserId(), statusList,reportPageQueryByRoleDTO.getReportName(),reportPageQueryByRoleDTO.getRiskId(),reportPageQueryByRoleDTO.getPeriod());

        long total = page.getTotal();
        List<ReportPageVO> reportVOList = page.getResult().stream()
                .map(report -> {
                    ReportPageVO reportPageVO = new ReportPageVO();
                    reportPageVO.setReportId(report.getReportId());
                    reportPageVO.setReportName(report.getReportName());
                    reportPageVO.setRiskName(getRiskNameById(report.getRiskId()));
                    reportPageVO.setVulnerabilityType(getVulnById(report.getVulnId()).getVulnerabilityType());
                    reportPageVO.setReportPeriod(report.getReportPeriod());
                    reportPageVO.setStatus(convertStatus(report.getStatus()));
                    reportPageVO.setDevOwner(getUserNameById(report.getDevUserId()));
                    reportPageVO.setSecOwner(getUserNameById(report.getSecUserId()));
                    return reportPageVO;
                })
                .collect(Collectors.toList());
        return new PageResult(total, reportVOList);
    }

    // 报告填写help
    @Override
    public List<reportVulnVO> getVuln() {
        return reportMapper.getVuln();
    }

    @Override
    public List<reportRiskVO> getRisk() {
        return reportMapper.getRisk();
    }

    @Override
    public List<reportUserVO> getUser(Integer typeId) {
            return reportMapper.getUser(typeId);
    }

    @Override
    public void putUser(ReportUserDTO reportUserDTO, Integer typeId) {
        int tmp;
        if (typeId==2){
            tmp = 2;
        }else {
            tmp = 3;
        }
        reportMapper.putUser(Long.valueOf(reportUserDTO.getReportId()),reportUserDTO.getUserId(), tmp);
    }


    private LocalDateTime getReportPeriodById(Long id){
        return reportMapper.getReportPeriodById(id);
    }

    private String getUserNameById(Long id){
        return reportMapper.getUserNameById(id);
    }

    private User getUserByUsername(String username){
        return reportMapper.getUserByUsername(username);
    }

    private String getRiskNameById(Long id){
        return reportMapper.getRiskNameById(id);
    }

    private Vuln getVulnById(Long id){
        return reportMapper.getVulnById(id);
    }
}
