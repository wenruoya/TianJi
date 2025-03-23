package org.wr.tianji.controller.report;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.wr.tianji.common.result.Result;
import org.wr.tianji.pojo.dto.StatusChangeDTO;
import org.wr.tianji.service.EventSerice;
import org.wr.tianji.service.StatusService;

import static org.wr.tianji.common.constant.ReportStatusConstant.*;

@RestController
@RequestMapping("/status")
@Slf4j
@Tag(name = "工单状态变更")
public class StatusController {

    @Autowired
    private StatusService statusService;
    @Autowired
    private EventSerice eventSerice;

    /**
     * 工单确认
     * @param id
     * @return
     */
    @GetMapping("/confirmation")
    @Operation(summary = "工单确认")
    public Result confirmation (@RequestParam int id,
                                @AuthenticationPrincipal UserDetails userDetails){
        statusService.changeStatus((long) id,PENDINGRISK_CODE);
        eventSerice.insertConfirm((long) id,userDetails.getUsername());
        return Result.success();
    }


    /**
     * 工单待确认修复
     * @param
     * @return
     */
    @PostMapping("/verify")
    @Operation(summary = "工单待确认修复")
    public Result verify(@RequestBody StatusChangeDTO statusChangeDTO,
                         @AuthenticationPrincipal UserDetails userDetails){
        statusService.chageDevStatus(Long.valueOf(statusChangeDTO.getReportId()), statusChangeDTO.getDescription(), RECOGNITIONRISKTREATMENT_CODE);
        eventSerice.insertVerify(Long.valueOf(statusChangeDTO.getReportId()),userDetails.getUsername(), statusChangeDTO.getDescription());
        return Result.success();
    }

    /**
     * 工单确认修复
     * @param
     * @return
     */
    @PostMapping("/repair")
    @Operation(summary = "工单确认修复")
    public Result repair(@RequestBody StatusChangeDTO statusChangeDTO,
                         @AuthenticationPrincipal UserDetails userDetails){
        statusService.chageSecStatus(Long.valueOf(statusChangeDTO.getReportId()), statusChangeDTO.getDescription(),PROCESSED_CODE);
        eventSerice.insertRepair(Long.valueOf(statusChangeDTO.getReportId()),userDetails.getUsername(), statusChangeDTO.getDescription());
        return Result.success();
    }

    /**
     * 工单待驳回确认
     * @param
     * @return
     */
    @PostMapping("/toreject")
    @Operation(summary = "工单待驳回确认")
    public Result toreject(@RequestBody StatusChangeDTO statusChangeDTO,
                           @AuthenticationPrincipal UserDetails userDetails){
        statusService.chageSecStatus(Long.valueOf(statusChangeDTO.getReportId()), statusChangeDTO.getDescription(), REJECTED_CODE);
        eventSerice.insertToreject(Long.valueOf(statusChangeDTO.getReportId()),userDetails.getUsername(), statusChangeDTO.getDescription());
        return Result.success();
    }

    /**
     * 工单不同意驳回
     * @param
     * @return
     */
    @PostMapping("/noreject")
    @Operation(summary = "工单待驳回确认")
    public Result noreject(@RequestBody StatusChangeDTO statusChangeDTO){
        statusService.chageSecStatus(Long.valueOf(statusChangeDTO.getReportId()), statusChangeDTO.getDescription(), PENDINGRISK_CODE);
        return Result.success();
    }

    /**
     * 工单不同意修复
     * @param
     * @return
     */
    @PostMapping("/norepair")
    @Operation(summary = "工单确认修复")
    public Result norepair(@RequestBody StatusChangeDTO statusChangeDTO){
        statusService.chageSecStatus(Long.valueOf(statusChangeDTO.getReportId()), statusChangeDTO.getDescription(),PENDINGRISK_CODE);
        return Result.success();
    }

    /**
     * 工单驳回
     * @param
     * @return
     */
    @PostMapping("/reject")
    @Operation(summary = "工单驳回")
    public Result reject(@RequestBody StatusChangeDTO statusChangeDTO,
                         @AuthenticationPrincipal UserDetails userDetails){
        statusService.chageDevStatus(Long.valueOf(statusChangeDTO.getReportId()), statusChangeDTO.getDescription(), REJECTIONRISK_CODE);
        eventSerice.insertReject(Long.valueOf(statusChangeDTO.getReportId()),userDetails.getUsername(), statusChangeDTO.getDescription());
        return Result.success();
    }
}
