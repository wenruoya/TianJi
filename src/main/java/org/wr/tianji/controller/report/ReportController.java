package org.wr.tianji.controller.report;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.wr.tianji.common.result.PageResult;
import org.wr.tianji.common.result.Result;
import org.wr.tianji.pojo.dto.ReportDTO;
import org.wr.tianji.pojo.dto.ReportPageQueryByRoleDTO;
import org.wr.tianji.pojo.dto.ReportPageQueryDTO;
import org.wr.tianji.pojo.dto.ReportUserDTO;
import org.wr.tianji.pojo.vo.ReportVO;
import org.wr.tianji.pojo.vo.reportRiskVO;
import org.wr.tianji.pojo.vo.reportUserVO;
import org.wr.tianji.pojo.vo.reportVulnVO;
import org.wr.tianji.service.EventSerice;
import org.wr.tianji.service.ReportService;

import java.util.List;


@RestController
@RequestMapping("/report")
@Slf4j
@Tag(name = "工单相关接口")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private EventSerice eventSerice;

    /**
     * 根据工单 id查询接口
     */
    @GetMapping
    @Operation(summary = "查询工单")
    public Result<ReportVO> getById(@RequestParam Long id){
        ReportVO reportVO = reportService.getById(id);
        return Result.success(reportVO);
    }

    /**
     * 删除工单
     */
    @DeleteMapping
    @Operation(summary = "删除工单")
    public Result delelte(@RequestParam Long id){
        reportService.delete(id);
        return Result.success();
    }

    /**
     * 修改工单
     */
    @PutMapping("{reportId}")
    @Operation(summary = "修改工单")
    public Result update(@PathVariable Integer reportId, @RequestBody ReportDTO reportDTO){
        ReportVO reportVO = reportService.getById(Long.valueOf(reportId));
        eventSerice.compare(reportVO, reportDTO);
        reportService.update(reportDTO);
        return Result.success();

    }

    /**
     * 新增工单
     */
    @PostMapping
    @Operation(summary = "新增工单")
    public Result insert(@RequestBody ReportDTO reportDTO){
        reportService.insert(reportDTO);
        return  Result.success();
    }

    /***
     * 延期工单
     */
    @GetMapping("/delay")
    @Operation(summary = "延期工单")
    public Result delay(@RequestParam Long id, @RequestParam Long delay){
        reportService.delay(id,delay);
        return Result.success();
    }


    /**
     *  分页查询全量接口
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询全量接口")
    public Result<PageResult> page(@RequestParam Integer page,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(required = false) String reportName,
                                   @RequestParam(required = false) Integer status,
                                   @RequestParam(required = false) Integer riskId,
                                   @RequestParam(required = false) Integer period){

        ReportPageQueryDTO reportPageQueryDTO = new ReportPageQueryDTO();


        reportPageQueryDTO.setPeriod(period);
        reportPageQueryDTO.setStatus(status);
        reportPageQueryDTO.setRiskId(riskId);
        reportPageQueryDTO.setReportName(reportName);

        reportPageQueryDTO.setPage(page);
        reportPageQueryDTO.setPageSize(pageSize);

        PageResult pageResult = reportService.pageQuery(reportPageQueryDTO);
        return Result.success(pageResult);

    }


    /**
     * 获取当前用户关联的工单
     * @return
     */
    @GetMapping("/Opage")
    @Operation(summary = "获取当前用户关联的工单")
    public Result<PageResult> pageById(@RequestParam Integer page,
                                       @RequestParam Integer pageSize,
                                       @RequestParam(required = false) String reportName,
                                       @RequestParam(required = false) Integer riskId,
                                       @RequestParam(required = false) Integer period,
                                       @AuthenticationPrincipal UserDetails userDetails){


        ReportPageQueryByRoleDTO reportPageQueryByRoleDTO = new ReportPageQueryByRoleDTO();

        reportPageQueryByRoleDTO.setUsername(userDetails.getUsername());
        reportPageQueryByRoleDTO.setPeriod(period);
        reportPageQueryByRoleDTO.setRiskId(riskId);
        reportPageQueryByRoleDTO.setReportName(reportName);

        reportPageQueryByRoleDTO.setPage(page);
        reportPageQueryByRoleDTO.setPageSize(pageSize);

        PageResult pageResult  = reportService.pageQueryById(reportPageQueryByRoleDTO);
        return Result.success(pageResult);
    }

    // 报告填写辅助

    /***
     * 获取所有风险
     */
    @GetMapping("/Risk")
    @Operation(summary = "获取全量风险")
    public Result<List<reportRiskVO>> getRisk(){
        return Result.success(reportService.getRisk());
    }

    /***
     * 获取所有漏洞
     */
    @GetMapping("/Vuln")
    @Operation(summary = "获取全量漏洞")
    public Result<List<reportVulnVO>> getVuln(){
        return Result.success(reportService.getVuln());
    }

    /**
     * 获取用户
     * @param typeId
     * @return
     */
    @GetMapping("/User/{typeId}")
    @Operation(summary = "获取用户")
    public Result<List<reportUserVO>> getUserByrole(@PathVariable Integer typeId){
            return Result.success(reportService.getUser(typeId));
    }

    /***
     * 指派工单负责人
     * @param typeId
     * @param reportUserDTO
     * @return
     */
    @PutMapping("/User/{typeId}")
    @Operation(summary = "指派工单负责人")
    public Result putUserByrole(@PathVariable Integer typeId, @RequestBody ReportUserDTO reportUserDTO,
                                @AuthenticationPrincipal UserDetails userDetails){
            reportService.putUser(reportUserDTO, typeId);
            eventSerice.updateUser(reportUserDTO, typeId, userDetails.getUsername());
            return Result.success();
    }


}
