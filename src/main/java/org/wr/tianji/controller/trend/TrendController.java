package org.wr.tianji.controller.trend;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wr.tianji.common.result.Result;
import org.wr.tianji.pojo.vo.TrendVO;
import org.wr.tianji.service.TrendService;

@RestController
@RequestMapping("/trend")
@Slf4j
@Tag(name = "运营态势相关")
public class TrendController {

    @Autowired
    private TrendService trendService;


    @GetMapping
    @Operation(summary = "获取运营态势")
    public Result<TrendVO> getTrend(){
        TrendVO trendVO  = trendService.getTrend();
        return Result.success(trendVO);
    }
}
