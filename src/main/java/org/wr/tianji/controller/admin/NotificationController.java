package org.wr.tianji.controller.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wr.tianji.common.result.Result;
import org.wr.tianji.pojo.dto.NotificationDTO;
import org.wr.tianji.pojo.vo.NotificationVO;
import org.wr.tianji.service.NotificationService;

import static org.wr.tianji.common.constant.NotificationConstant.NOTIFICATIONNUM;

@RestController
@RequestMapping("/admin/notification")
@Slf4j
@Tag(name = "管理视角通知配置相关接口")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    /**
     * 添加通知
     */
    @PostMapping
    @Operation(summary = "添加或更新通知配置")
    public Result save(@RequestBody NotificationDTO notificationDTO){
        if(!notificationService.selectById(NOTIFICATIONNUM)){
            notificationService.insert(notificationDTO);
            return Result.success();
        }
        else {
            notificationService.update(notificationDTO,NOTIFICATIONNUM);
            return Result.success();
        }
    }
    /**
     * 查询通知
     */
    @GetMapping
    @Operation(summary = "查询")
    public Result<NotificationVO> getById(){
        NotificationVO notificationVO = notificationService.getById(NOTIFICATIONNUM);
        return Result.success(notificationVO);
    }

}
