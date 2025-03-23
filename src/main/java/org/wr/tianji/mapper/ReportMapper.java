package org.wr.tianji.mapper;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;
import org.wr.tianji.pojo.dto.ReportDTO;
import org.wr.tianji.pojo.dto.ReportPageQueryByRoleDTO;
import org.wr.tianji.pojo.dto.ReportPageQueryDTO;
import org.wr.tianji.pojo.dto.ReportUserDTO;
import org.wr.tianji.pojo.entity.Report;
import org.wr.tianji.pojo.entity.User;
import org.wr.tianji.pojo.entity.Vuln;
import org.wr.tianji.pojo.vo.reportRiskVO;
import org.wr.tianji.pojo.vo.reportUserVO;
import org.wr.tianji.pojo.vo.reportVulnVO;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ReportMapper {

    @Delete("delete from tianji.report where report_id = #{id}")
    void delete(Long id);

    @Select("select * from tianji.report where report_id = #{id}")
    Report getById(Long id);

    @Select("select name from tianji.user where user_id=#{id}")
    String getUserNameById(Long id);

    @Select("select risk_name from tianji.risk where risk_id=#{id}")
    String getRiskNameById(Long id);

    @Select("select * from tianji.vuln where vuln_id=#{id}")
    Vuln getVulnById(Long id);

    @Select("select vulnerability_type from tianji.vuln where vuln_id=#{id}")
    Vuln getVulnNameById(Long id);

    @Select("select repair_period from tianji.risk where risk_id=#{id}")
    Integer getRepairPeriodById(Long id);

    @Update("update tianji.report " +
            "set report.report_name = #{reportName}," +
            "report.report_url = #{reportUrl} ," +
            "report.report_app = #{reportApp}," +
            "report.report_content = #{reportContent}," +
            "report.report_source = #{reportSource}," +
            "report.status = #{status}," +
            "report.risk_id = #{riskId}," +
            "report.vuln_id = #{vulnId}," +
            "report.sec_user_id = #{secUserId}," +
            "report.dev_user_id = #{devUserId}," +
            "report.remark = report.remark + #{remark}," +
            "report.feedback = report.feedback + #{feedback}")
    void update(ReportDTO reportDTO);

    @Insert("insert into tianji.report(report_id, report_name, report_url, report_app, report_content, report_source, status, risk_id, vuln_id,  report_period, create_time) " +
            "VALUES" +
            "(#{reportId}, #{reportName}, #{reportUrl}, #{reportApp}, #{reportContent}, #{reportSource}, #{status}, #{riskId}, #{vulnId}, #{reportPeriod}, #{CreateTime})")
    void insert(Report report);


    @Update("update tianji.report set report_period= #{delay} where report_id=#{id}")
    void delay(Long id, LocalDateTime delay);

    @Select("select report_period from tianji.report where report_id=#{id}")
    LocalDateTime getReportPeriodById(Long id);

    Page<Report> pageQuery(ReportPageQueryDTO reportPageQueryDTO);


    @Select("select * from tianji.vuln")
    List<reportVulnVO> getVuln();
    @Select("select * from tianji.risk")
    List<reportRiskVO> getRisk();
    @Select("select * from tianji.user where role=#{typeId}")
    List<reportUserVO> getUser(Integer typeId);

    void putUser(ReportUserDTO reportUserDTO, Integer role);

    void putUser(Long reportId, Integer userId, int tmp);

    @Select("select * from tianji.user where username=#{username}")
    User getUserByUsername(String usename);


    Page<Report> pageQueryByUserId(Long userId, List<Integer> statusList, String reportName, Integer riskId, Integer period);
}
