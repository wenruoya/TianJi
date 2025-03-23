package org.wr.tianji.service;

import org.wr.tianji.common.result.PageResult;
import org.wr.tianji.pojo.dto.UserDTO;
import org.wr.tianji.pojo.dto.UserPageQueryDTO;
import org.wr.tianji.pojo.entity.User;

import java.util.List;

public interface UserService {

    /**
     * 新增用户
     */
    Boolean save(UserDTO userDTO);

    /***
     * 批量删除
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 分页查询
     * @param userPageQueryDTO
     * @return
     */
    PageResult pageQuery(UserPageQueryDTO userPageQueryDTO);

    /**
     * 重置密码
     * @param id
     */
    void reset(long id);

    /***
     * 修改密码
     * @param username
     * @param oldPassword
     * @param newPassword
     * @return
     */
    boolean changePassword(String username, String oldPassword, String newPassword);

    /**
     * 获取用户
     * @param username
     * @return
     */
    User findByUsername(String username);
}
