package org.wr.tianji.common.utils;

import org.wr.tianji.pojo.entity.User;
import org.wr.tianji.pojo.vo.UserDataVO;

import java.util.Arrays;

/**
 * 权限相关
 */
public class RoleUtil {

    public static UserDataVO convert(User user){
        UserDataVO userDataVO = new UserDataVO();
        if(user.getRole()==0){
            userDataVO.setRoles(Arrays.asList("admin"));
            return  userDataVO;
        } else if (user.getRole()==1) {
            userDataVO.setRoles(Arrays.asList("operation"));
            return  userDataVO;
        }else if(user.getRole()==2){
            userDataVO.setRoles(Arrays.asList("security"));
            return  userDataVO;
        }else {
            userDataVO.setRoles(Arrays.asList("development"));
            return  userDataVO;
        }

    }
}
