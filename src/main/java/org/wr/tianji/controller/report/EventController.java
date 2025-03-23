package org.wr.tianji.controller.report;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.wr.tianji.common.result.Result;
import org.wr.tianji.pojo.vo.LogVO;
import org.wr.tianji.service.EventSerice;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "事件相关接口")
@RequestMapping("/report")
public class EventController {

    @Autowired
    private EventSerice eventSerice;

    /**
     * 根据工单id获取日志
     * @param id
     * @return
     */
    @GetMapping("/event")
    @Operation(summary = "获取日志事件")
    public Result<List<LogVO>> getById(@RequestParam Long id){
        List<LogVO> logVOList = eventSerice.getById(id);
        return Result.success(logVOList);
    }
}
