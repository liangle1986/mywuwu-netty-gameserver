/*
 * @author   作者: lianglele
 * @E-mail   邮箱: liangle1986@126.com
 * @date     创建时间：2018/11/12
 * 类说明     基于WebFlex 登陆模块
 */
package com.mywuwu.gameserver.webserver;

import com.mywuwu.gameserver.core.security.JwtTokenUtil;
import com.mywuwu.gameserver.core.websocket.GameWebSocketSession;
import com.mywuwu.gameserver.data.monoModel.UserModel;
import com.mywuwu.gameserver.data.monoRepository.UserRepository;
import com.mywuwu.gameserver.webserver.config.GameConfig;
import com.mywuwu.gameserver.webserver.protocol.GuestResponse;
import com.mywuwu.gameserver.webserver.protocol.LoginRequest;
import com.mywuwu.gameserver.webserver.protocol.LoginResponse;
import com.mywuwu.gameserver.webserver.protocol.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RequestMapping("/api")
@RestController
public class LoginController {

    private final UserRepository userRepository;

    private final GameConfig config;

    protected final ValueOperations<String, GameWebSocketSession> valueOperationsByGameWebSocketSession;

    private final RedisTemplate redisTemplate;

    private final JwtTokenUtil jwtTokenUtil;


    @Autowired
    public LoginController(
            GameConfig config,
            RedisTemplate redisTemplate,
            JwtTokenUtil jwtTokenUtil,
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
        this.config = config;
        this.redisTemplate = redisTemplate;
        this.jwtTokenUtil = jwtTokenUtil;
        this.valueOperationsByGameWebSocketSession = this.redisTemplate.opsForValue();
    }


    @PostMapping("login")
    public Optional<LoginResponse> login(@RequestBody LoginRequest loginForm) {

        UserModel userModel = userRepository.findByNameAndPassword(loginForm.getName(), loginForm.getPassword());
        //查询sdk等到用户信息

//        验证本地是否有当前用户
        if (userModel != null) {
//            用微信sdk更新本地
        } else {
            //创建用户信息到本地
            userModel = new UserModel();
            userModel.setName(loginForm.getName());
            userModel.setNickName(loginForm.getName());
            userModel.setPassword(loginForm.getPassword());
            userModel.setMobileNumber("123214324");
            userModel.setUserType(0);
            userModel.setSex(0);
            userModel.setCardNumber(0);
            userRepository.save(userModel);
        }

        // 加密生产token
        String token = jwtTokenUtil.generateToken(userModel.getNickName());

        //返回用户信息
        LoginResponse response = new LoginResponse(userModel.getId(), userModel.getName(), userModel.getNickName(), userModel.getMobileNumber(),
                userModel.getSex(), userModel.getCardNumber()
                , userModel.getUserType(), userModel.getHeadImgUrl(), config.getServers(), token);

        return Optional.of(response);

    }

    @GetMapping("login1")
    public Optional<LoginResponse> login(String name, String password) {
        UserModel userModel = userRepository.findByNameAndPassword(name, password);
        //查询sdk等到用户信息

//        验证本地是否有当前用户
        if (userModel != null) {
//            用微信sdk更新本地
        } else {
            //创建用户信息到本地
            userModel = new UserModel();
            userModel.setName(name);
            userModel.setNickName(name);
            userModel.setPassword(password);
            userModel.setMobileNumber("123214324");
            userModel.setUserType(0);
            userModel.setSex(0);
            userModel.setCardNumber(0);
            userRepository.save(userModel);
        }

        // 加密生产token
        String token = jwtTokenUtil.generateToken(userModel.getNickName());

        //返回用户信息
        LoginResponse response = new LoginResponse(userModel.getId(), userModel.getName(), userModel.getNickName(), userModel.getMobileNumber(),
                userModel.getSex(), userModel.getCardNumber()
                , userModel.getUserType(), userModel.getHeadImgUrl(), config.getServers(), token);


        GameWebSocketSession gameWebSocketSession = new GameWebSocketSession(userModel.getId() + "", null, token, new Date().toString(),
                "", null, "", "", "");

        valueOperationsByGameWebSocketSession.set(userModel.getId() + "", gameWebSocketSession);

        return Optional.of(response);
    }


    @GetMapping("api/guest")
    public GuestResponse guest(String deviceId) {
        UserModel userModel = userRepository.findByNameAndUserType(deviceId, 1);
        if (userModel == null) {
            userModel = new UserModel();
            userModel.setName(deviceId);
            userModel.setNickName("访客");
            userModel.setBalance(0);
            userModel.setCardNumber(3);
            userModel.setMobileNumber("");
            userModel.setPassword("");
            userModel.setSex(0);
            userModel.setSponsor("");
            userModel.setUserType(1);
            userRepository.save(userModel);
        }


        String token = jwtTokenUtil.generateToken(String.valueOf(userModel.getId()));
        return new GuestResponse(userModel.getId()
                , userModel.getName(),
                userModel.getNickName(),
                token,
                0,
                3,
                config.getServers()
        );
    }

    @PostMapping("api/register")
    public UserModel register(@RequestBody RegisterRequest registerForm) {
        //todo 验证码
        UserModel userModel = new UserModel();

        userModel.setName(registerForm.getName());
        userModel.setNickName(registerForm.getNickName());
        userModel.setBalance(0);
        userModel.setCardNumber(3);
        userModel.setMobileNumber(registerForm.getMobileNumber());
        userModel.setPassword(registerForm.getPassword());
        userModel.setSex(0);
        userModel.setSponsor("");
        userModel.setUserType(0);
        userRepository.save(userModel);
        return userModel;
    }
}
