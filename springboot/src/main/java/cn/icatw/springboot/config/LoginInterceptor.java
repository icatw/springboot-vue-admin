package cn.icatw.springboot.config;

import cn.hutool.core.util.StrUtil;
import cn.icatw.springboot.common.ResultStatusEnum;
import cn.icatw.springboot.entity.SysUser;
import cn.icatw.springboot.exception.CustomException;
import cn.icatw.springboot.service.SysUserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author icatw
 * @date 2022/4/28
 * @email 762188827@qq.com
 * @apiNote
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private SysUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        // 执行认证
        if (StrUtil.isBlank(token)) {
            //throw new CustomException(Constants.CODE_401, "无token，请重新登录");
            throw new CustomException(ResultStatusEnum.NO_TOKEN_EXCEPTION);
        }
        // 获取 token 中的 user id
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            //throw new CustomException(Constants.CODE_401, "token验证失败，请重新登录");
            throw new CustomException(ResultStatusEnum.TOKEN_VERIFICATION_FAILED);
        }
        // 根据token中的userid查询数据库
        SysUser user = userService.getById(userId);
        if (user == null) {
            throw new CustomException(ResultStatusEnum.NOT_USER);
        }
        // 用户密码加签验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token); // 验证token
        } catch (JWTVerificationException e) {
            throw new CustomException(ResultStatusEnum.TOKEN_VERIFICATION_FAILED);
        }
        return true;
    }
}
