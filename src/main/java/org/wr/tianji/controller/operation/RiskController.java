package org.wr.tianji.controller.operation;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wr.tianji.common.result.PageResult;
import org.wr.tianji.common.result.Result;
import org.wr.tianji.pojo.dto.RiskDTO;
import org.wr.tianji.pojo.dto.RiskPageQueryDTO;
import org.wr.tianji.pojo.entity.Risk;
import org.wr.tianji.service.RiskService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@Tag(name = "运营视角风险定义")
@RequestMapping("/operation/risk")
public class RiskController {

    @Autowired
    private RiskService riskService;

    /**
     * 添加风险
     * @param riskDTO
     * @return
     */
    @PostMapping
    @Operation(summary = "添加风险")
    public Result save(@RequestBody RiskDTO riskDTO){
        riskService.insert(riskDTO);
        return Result.success();
    }

    /**
     * 批量删除风险
     */
    @DeleteMapping
    @Operation(summary = "批量删除风险")
    public Result delete(@RequestParam String ids){
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());
        riskService.deleteBatch(idList);
        return Result.success();
    }

    /***
     * 分页查询风险
     * @param page
     * @param pageSize
     * @param riskName
     * @return
     */
    @GetMapping("/page")
    @Operation(summary = "风险分页查询")
    public Result<PageResult> page (@RequestParam Integer page,@RequestParam Integer pageSize,@RequestParam(required = false) String riskName){
        RiskPageQueryDTO riskPageQueryDTO = new RiskPageQueryDTO();
        riskPageQueryDTO.setPage(page);
        riskPageQueryDTO.setPageSize(pageSize);
        riskPageQueryDTO.setRiskName(riskName);

        PageResult pageResult = riskService.pageQuery(riskPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 更新风险
     * @param riskId
     * @param riskDTO
     * @return
     */
    @PutMapping("/{riskId}")
    @Operation(summary = "更新风险")
    public Result update(@PathVariable Integer riskId,@RequestBody(required = false) RiskDTO riskDTO){
        Risk risk = new Risk();
        risk.setRiskId(Long.valueOf(riskId));
        risk.setRiskName(riskDTO.getRiskName());
        risk.setRepairPeriod(riskDTO.getRepairPeriod());
        riskService.update(risk);
        return Result.success();
    }


}
