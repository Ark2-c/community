package cn.ark.community.controller;

import cn.ark.community.entity.User;
import cn.ark.community.service.UserService;
import cn.ark.community.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @program: community
 * @description:注册请求
 * @author: Ark
 * @create: 2022-02-19 19:48
 **/
@Controller
public class LoginController implements CommunityConstant{

    @Autowired
    private UserService userService;

    /**
     * 跳转注册页面
     * @return
     */
    @RequestMapping(path = "/register",method = RequestMethod.GET)
    public String getRegisterPage(){
        return "site/register";
    }

    @RequestMapping(path = "/login",method = RequestMethod.GET)
    public String getLoginPage(){
        return "site/login";
    }

    /**
     * 输出注册信息后对信息进行处理
     * @param model
     * @param user
     * @return
     */
    @RequestMapping(path = "/register",method = RequestMethod.POST)
    public String register(Model model, User user){
        Map<String,Object> map = userService.register(user);
        if (map==null||map.isEmpty()){
            model.addAttribute("mgs","注册成功,请前往邮箱激活账号");
            model.addAttribute("target","/index");
            return "/site/operate-result";
        }else {
            model.addAttribute("usernameMsg",map.get("usernameMsg"));
            model.addAttribute("passwordMsg",map.get("passwordMsg"));
            model.addAttribute("emailMsg",map.get("emailMsg"));

            return "site/register";
        }
    }

    /**
     * 邮件激活
     * @param model
     * @param userId
     * @param code
     * @return
     */
    @RequestMapping(value = "/activation/{userId}/{code}",method = RequestMethod.GET)
    public String activation(Model model, @PathVariable("userId")int userId,@PathVariable("code")String code){
        int result = userService.activation(userId,code);
        if (result==ACTIVATION_SUCCESS){
            model.addAttribute("msg", "激活成功");
            model.addAttribute("target","/login");
        }else if (result == ACTIVATION_REPEAT){
            model.addAttribute("msg","无效操作，该账号已激活");
            model.addAttribute("target","/index");
        }else {
            model.addAttribute("msg","激活失败，您携带的激活码不正确");
            model.addAttribute("target","/index");

        }
        return "/site/operate-result";
    }
}
