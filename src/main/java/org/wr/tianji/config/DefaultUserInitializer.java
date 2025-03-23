package org.wr.tianji.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.wr.tianji.mapper.UserMapper;
import org.wr.tianji.pojo.entity.User;

import java.time.LocalDateTime;

@Component
public class DefaultUserInitializer implements CommandLineRunner {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${default_username}")
    private String DEFAULT_USERNAME;
    @Value("${default_password}")
    private String DEFAULT_PASSWORD;
    private static final String DEFAULT_ROLE = "0";
    private static final String DEFAULT_NAME = "天机";
    private static final String DEFAULT_USERNUMBER = "0";

    @Override
    public void run(String... args) throws Exception {
        // 检查默认用户是否已存在
        User existingUser = userMapper.selectOne(DEFAULT_USERNAME);
        if (existingUser == null) {
            // 插入默认用户
            User user = new User();
            user.setUsername(DEFAULT_USERNAME);
            user.setPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
            user.setName(DEFAULT_NAME);
            user.setRole(Integer.parseInt(DEFAULT_ROLE));
            user.setCreateTime(LocalDateTime.now());
            user.setUserNumber(DEFAULT_USERNUMBER);
            userMapper.insert(user);
            System.out.println("默认用户已插入: " + DEFAULT_USERNAME);
        } else {
            System.out.println("默认用户已存在: " + DEFAULT_USERNAME);
        }
    }
}
