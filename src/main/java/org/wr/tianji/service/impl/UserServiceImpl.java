package org.wr.tianji.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.wr.tianji.common.result.PageResult;
import org.wr.tianji.mapper.UserMapper;
import org.wr.tianji.pojo.dto.UserDTO;
import org.wr.tianji.pojo.dto.UserPageQueryDTO;
import org.wr.tianji.service.UserService;
import org.wr.tianji.pojo.entity.User;
import org.wr.tianji.common.context.BaseContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.wr.tianji.pojo.vo.UserVO;
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 默认密码
     */
    @Value("${default_password}")
    private String default_password;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 新增用户
     * @param userDTO
     */
    @Override
    public Boolean save(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        user.setPassword(passwordEncoder.encode(default_password));
        user.setCreateTime(LocalDateTime.now());
        try {
            int result = userMapper.insert(user);
            return result > 0;
        } catch (DuplicateKeyException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 批量删除
     * @param ids
     */
    @Override
    public void deleteBatch(List<Long> ids) {
        for (Long id:ids){
            // 删除用户时触发兜底措施
            userMapper.changeId(id);
            userMapper.deleteById(id);
        }
    }

    /***
     * 分页查询
     * @param userPageQueryDTO
     * @return
     */

    @Override
    public PageResult pageQuery(UserPageQueryDTO userPageQueryDTO) {
        PageHelper.startPage(userPageQueryDTO.getPage(),userPageQueryDTO.getPageSize());
        Page<User> page = userMapper.pageQuery(userPageQueryDTO);
        long total = page.getTotal();
        List<UserVO> userVOList = page.getResult().stream()
                .map(user -> {
                    UserVO userVO = new UserVO();
                    userVO.setUsername(user.getUsername());
                    userVO.setRole(user.getRole());
                    userVO.setCreateTime(user.getCreateTime());
                    userVO.setUserId(user.getUserId());
                    userVO.setName(user.getName());
                    userVO.setUserNumber(user.getUserNumber());
                    // 其他字段
                    return userVO;
                })
                .collect(Collectors.toList());
        return new PageResult(total,userVOList);
    }

    /**
     * 重置密码
     * @param id
     */
    @Override
    public void reset(long id) {
        userMapper.reset(id,passwordEncoder.encode(default_password));
    }

    /**
     * 修改密码
     * @param username
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @Override
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        // 查找用户
        User user = userMapper.selectOne(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户未找到");
        }

        // 验证旧密码
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return false; // 旧密码错误
        }

        // 更新为新密码
        user.setPassword(passwordEncoder.encode(newPassword));
        userMapper.update(user);

        return true;
    }

    /**
     * 获取用户
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {

        return userMapper.findByUsername(username);
    }


}
