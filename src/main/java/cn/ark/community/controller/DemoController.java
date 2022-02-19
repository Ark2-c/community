package cn.ark.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: community
 * @description:
 * @author: Ark
 * @create: 2022-02-11 22:39
 **/
@Controller
@RequestMapping("/alpha")
public class DemoController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "Hello!";
    }
}
