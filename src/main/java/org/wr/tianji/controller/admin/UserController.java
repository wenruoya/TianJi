package org.wr.tianji.controller.admin;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wr.tianji.common.result.PageResult;
import org.wr.tianji.common.result.Result;
import org.wr.tianji.pojo.dto.UserDTO;
import org.wr.tianji.pojo.dto.UserPageQueryDTO;
import org.wr.tianji.service.UserService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/user")
@Tag(name = "管理视角用户相关接口")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /***
     * 新增用户
     * @param userDTO
     * @return
     */
    @PostMapping
    @Operation(summary = "新增用户")
    public Result save(@RequestBody UserDTO userDTO){
        if(userService.save(userDTO)){
            return Result.success();
        }else {
            return Result.error("用户名重复");
        }
    }

    /**
     * 删除用户
     * @param ids
     * @return
     */
    @DeleteMapping
    @Operation(summary = "批量删除用户")
    public Result delete(@RequestParam String ids){
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::valueOf)
                .collect(Collectors.toList());
        userService.deleteBatch(idList);
        return Result.success();
    }

    /***
     * 分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    @Operation(summary = "用户分页查询")
    public Result<PageResult> page (@RequestParam Integer page, @RequestParam Integer pageSize, @RequestParam(required = false) String name, @RequestParam(required = false) String userNumber){
        UserPageQueryDTO userPageQueryDTO = new UserPageQueryDTO();
        userPageQueryDTO.setPage(page);
        userPageQueryDTO.setPageSize(pageSize);
        userPageQueryDTO.setName(name);
        userPageQueryDTO.setUserNumber(userNumber);

        PageResult pageResult = userService.pageQuery(userPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 重置用户密码
     */
    @GetMapping("/{id}")
    @Operation(summary = "重置用户密码")
    public Result reset(@PathVariable long id){
        userService.reset(id);
        return Result.success();
    }

}
