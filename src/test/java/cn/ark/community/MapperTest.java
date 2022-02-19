package cn.ark.community;

import cn.ark.community.dao.DiscussPostMapper;
import cn.ark.community.dao.UserMapper;
import cn.ark.community.entity.DiscussPost;
import cn.ark.community.entity.User;
import cn.ark.community.service.DiscussPostService;
import cn.ark.community.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.*;

/**
 * @program: community
 * @description:测试mapper
 * @author: Ark
 * @create: 2022-02-16 15:02
 **/
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    DiscussPostService discussPostService;
    @Autowired
    UserService userService;

    @Test
    public void selectUserTest() {
        User user = userMapper.selectById(3);
        System.out.println(user);
        user = userMapper.selectByEmail("1216681804@qq.com");
        System.out.println(user);
        user = userMapper.selectByName("aaa");
        System.out.println(user);
    }

    @Test
    public void insertUserTest() {
        User user = new User();
        for (int i = 0;i<300;i++){
            user.setUsername("test");
            user.setPassword("123456");
            user.setSalt("abc");
            user.setEmail("test@qq.com");
            user.setHeaderUrl("http://www.nowcoder.com/101.png");
            user.setCreateTime(new Date());
            userMapper.insertUser(user);
        }



    }

    @Test
    public void updateUserTest() {
        userMapper.updatePassword(1, "123456");
        userMapper.updateStatus(5, 1);
        for (int i =6;i<305;i++){

            userMapper.updateHeader(i, "http://images.nowcoder.com/head/"+i+"t.png");
        }
    }

    @Test
    public void selectPostTest() {
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(0, 0, 10, 0);
        for (DiscussPost discussPost : list) {
            System.out.println(discussPost);
        }
        System.out.println(discussPostMapper.selectDiscussPostRows(0));
        list = discussPostService.findDiscussPost(0, 0, 10);
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (list != null) {
            for (DiscussPost post : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                User user = userService.findUserById(post.getUserId());
                map.put("user", user);
                discussPosts.add(map);
            }
        }
        System.out.println(discussPosts);
    }
}
