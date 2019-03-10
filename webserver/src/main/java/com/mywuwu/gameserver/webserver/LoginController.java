/*
 * @author   作者: qugang
 * @E-mail   邮箱: qgass@163.com
 * @date     创建时间：2018/11/12
 * 类说明     基于WebFlex 登陆模块
 */
package com.mywuwu.gameserver.webserver;

import com.mywuwu.gameserver.core.security.JwtTokenUtil;
import com.mywuwu.gameserver.core.websocket.GameWebSocketSession;
import com.mywuwu.gameserver.mapper.entity.MywuwuUser;
import com.mywuwu.gameserver.mapper.service.UserService;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/api")
@RestController
public class LoginController {

//    private final UserRepository userRepository;

    private final GameConfig config;

    protected final ValueOperations<String, GameWebSocketSession> valueOperationsByGameWebSocketSession;

    private final RedisTemplate redisTemplate;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserService userService;


    @Autowired
    public LoginController(
            GameConfig config,
            RedisTemplate redisTemplate,
            JwtTokenUtil jwtTokenUtil, UserService userService) {
//        this.userRepository = userRepository;
        this.config = config;
        this.redisTemplate = redisTemplate;
        this.jwtTokenUtil = jwtTokenUtil;
        this.valueOperationsByGameWebSocketSession = this.redisTemplate.opsForValue();
        this.userService = userService;
    }


    @PostMapping("login")
    public Optional<LoginResponse> login(@RequestBody LoginRequest loginForm) {

        MywuwuUser mywuwuUser = userService.selectWeixinCode(loginForm.getName());
        //查询sdk等到用户信息

//        验证本地是否有当前用户
        if (mywuwuUser != null) {
//            用微信sdk更新本地
        } else {
            //创建用户信息到本地
            mywuwuUser = new MywuwuUser();
            mywuwuUser.setId(UUID.randomUUID().toString());
            mywuwuUser.setUserLevel(true);
            mywuwuUser.setNickName(new Date().toString());
            mywuwuUser.setHeadImgUrl("http://img/url");
            mywuwuUser.setRoomCardNum(10000);
            mywuwuUser.setCreateTime(new Date());
            mywuwuUser.setUpdateTime(new Date());
            mywuwuUser.setWinProbability(1);
            mywuwuUser.setWxOpenId(mywuwuUser.getId());
            userService.saveGameUser(mywuwuUser);
        }

        // 加密生产token
        String token = jwtTokenUtil.generateToken(mywuwuUser.getNickName());

        //返回用户信息
        LoginResponse response = new LoginResponse(mywuwuUser.getNickName(), mywuwuUser.getHeadImgUrl(), mywuwuUser.getWxOpenId(), mywuwuUser.getRoomCardNum(), mywuwuUser.getUserLevel()
                , mywuwuUser.getWinProbability(), config.getServers(), token);

        return Optional.of(response);

    }

    @GetMapping("login1")
    public Optional<LoginResponse> login(String name, String password) {
        MywuwuUser mywuwuUser = userService.selectWeixinCode(name);
        //查询sdk等到用户信息

//        验证本地是否有当前用户
        if (mywuwuUser != null) {
//            用微信sdk更新本地
        } else {
            //创建用户信息到本地
            mywuwuUser = new MywuwuUser();
            mywuwuUser.setId(UUID.randomUUID().toString());
            mywuwuUser.setUserLevel(true);
            mywuwuUser.setNickName(new Date().toString());
            mywuwuUser.setHeadImgUrl("http://img/url");
            mywuwuUser.setRoomCardNum(10000);
            mywuwuUser.setCreateTime(new Date());
            mywuwuUser.setUpdateTime(new Date());
            mywuwuUser.setWinProbability(1);
            mywuwuUser.setWxOpenId(mywuwuUser.getId());
            userService.saveGameUser(mywuwuUser);
        }

        // 加密生产token
        String token = jwtTokenUtil.generateToken(mywuwuUser.getNickName());

        //返回用户信息
        LoginResponse response = new LoginResponse(mywuwuUser.getNickName(), mywuwuUser.getHeadImgUrl(), mywuwuUser.getWxOpenId(), mywuwuUser.getRoomCardNum(), mywuwuUser.getUserLevel()
                , mywuwuUser.getWinProbability(), config.getServers(), token);

        return Optional.of(response);
//
//        MywuwuUser mywuwuUser = userService.selectWeixinCode(wxOpenId);
//        // 加密生产token
//        String token = jwtTokenUtil.generateToken(mywuwuUser.getNickName());
//
//        //返回用户信息
//        LoginResponse response = new LoginResponse(mywuwuUser.getNickName(), mywuwuUser.getHeadImgUrl(), mywuwuUser.getWxOpenId(), mywuwuUser.getRoomCardNum(), mywuwuUser.getUserLevel()
//                , mywuwuUser.getWinProbability(), config.getServers(), token);
//
//        return Optional.of(response);
//        return Optional.empty();
    }

   /* @GetMapping("api/guest")
    public GuestResponse guest(String deviceId) {
        List<User> list = userService.getTest();
        System.out.println(list.size());
        UserModel userModel = userRepository.findByNameAndUserType(deviceId, 1);
        if (userModel == null) {
            userModel = new UserModel();
            userModel.setName(deviceId);
            userModel.setNickName("访客");
            userModel.setBalance(0);
            userModel.setCardNumber(3);
            userModel.setMobileNumber("");
            userModel.setPassword("");
            userModel.setSex("0");
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
        userModel.setSex("0");
        userModel.setSponsor("");
        userModel.setUserType(0);
        userRepository.save(userModel);
        return userModel;
    }*/
}
