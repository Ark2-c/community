package cn.ark.community.service;

import cn.ark.community.dao.UserMapper;
import cn.ark.community.entity.User;
import cn.ark.community.util.CommunityConstant;
import cn.ark.community.util.CommunityUtil;
import cn.ark.community.util.MailClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @program: community
 * @description:
 * @author: Ark
 * @create: 2022-02-16 15:55
 **/
@Service
public class UserService implements CommunityConstant {
    @Autowired
    UserMapper userMapper;
    @Autowired
    MailClient mailClient;
    @Autowired
    TemplateEngine templateEngine;

    @Value("${community.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    public User findUserById(int id) {
        return userMapper.selectById(id);
    }

    public int activation(int userId,String code){
        User user = userMapper.selectById(userId);

        if (user.getStatus()==1){
            return ACTIVATION_REPEAT;
        }else if (user.getActivationCode().equals(code)){
            userMapper.updateStatus(userId,1);
            return ACTIVATION_SUCCESS;
        }else {
            return ACTIVATION_FAILURE;
        }
    }

    public Map<String, Object> register(User user) {
        Map<String, Object> map = new HashMap<>();
        if (user == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
        if (StringUtils.isBlank(user.getUsername())) {
            map.put("usernameMsg", "账号不能为空");
            return map;
        }
        if (StringUtils.isBlank(user.getPassword())) {
            map.put("pasawordMsg", "密码不能为空");
            return map;
        }
        if (StringUtils.isBlank(user.getEmail())) {
            map.put("email", "邮箱不能为空");
            return map;
        }
        //验证账号
        User u = userMapper.selectByName(user.getUsername());
        if (u != null) {
            map.put("usernameMsg", "账号已存在");
            return map;
        }
        u = userMapper.selectByEmail(user.getEmail());
        if (u != null) {
            map.put("emailMsg", "邮箱已存在");
            return map;
        }
        //
        user.setSalt(CommunityUtil.generateUUID().substring(0, 5));
        user.setPassword(CommunityUtil.md5(user.getPassword() + user.getSalt()));
        user.setStatus(0);
        user.setType(0);
        user.setActivationCode(CommunityUtil.generateUUID());
        user.setCreateTime(new Date());
        user.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
        userMapper.insertUser(user);

        //发送激活邮件
        Context context = new Context();
        context.setVariable("email", user.getEmail());
        context.setVariable("username", user.getUsername());
        String url = domain + contextPath + "/activation/" + user.getId() + "/" + user.getActivationCode();
        //http:localhost:8080/community/activation/101/code
        context.setVariable("url", url);
        String contend = templateEngine.process("mail/activation", context);
        mailClient.sendMail(user.getEmail(),"激活账号",contend);
        //map为空则没问题
        return map;
    }

    public User findUerByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    public User findUserByName(String name) {
        return userMapper.selectByName(name);
    }

}
