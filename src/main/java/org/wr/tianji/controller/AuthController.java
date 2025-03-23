package org.wr.tianji.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.wr.tianji.common.utils.RoleUtil;
import org.wr.tianji.pojo.dto.ChangePasswordDTO;
import org.wr.tianji.pojo.dto.LoginDTO;
import org.wr.tianji.common.utils.JwtUtil;
import org.wr.tianji.common.utils.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.wr.tianji.pojo.entity.User;
import org.wr.tianji.pojo.vo.LoginVO;
import org.wr.tianji.pojo.vo.UserDataVO;
import org.wr.tianji.service.UserService;



@RestController
@RequestMapping("/auth/")
@Tag(name = "登录")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    @Operation(summary = "登录")
    public LoginVO login(@RequestBody LoginDTO loginDTO) {
        try {
            // 验证用户名和密码
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
            User user = userService.findByUsername(loginDTO.getUsername());
            UserDataVO userDataVO = RoleUtil.convert(user);
            userDataVO.setName(user.getName());
            userDataVO.setUsername(user.getUsername());
            // 生成 JWT
            final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getUsername());
            userDataVO.setAccessToken(jwtUtil.generateToken(userDetails));
            return new LoginVO(true,userDataVO);

        } catch (Exception e) {
            return new LoginVO(false,null);
        }

    }
    @GetMapping("/logout")
    @Operation(summary = "登出")
    public ResponseEntity<?> logout() {
        // 提示前端删除 JWT
        return ResponseEntity.ok("登出成功");
    }

    @PostMapping("/change-password")
    @Operation(summary = "修改密码")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {

        boolean result = userService.changePassword(
                changePasswordDTO.getUsername(),
                changePasswordDTO.getOldPassword(),
                changePasswordDTO.getNewPassword()
        );
        if (result) {
            return ResponseEntity.ok("密码修改成功");
        } else {
            return ResponseEntity.badRequest().body("旧密码错误或用户不存在");
        }
    }

}
