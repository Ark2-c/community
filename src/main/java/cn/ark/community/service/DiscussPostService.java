package cn.ark.community.service;


import cn.ark.community.dao.DiscussPostMapper;
import cn.ark.community.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussPostService {

    @Autowired
    private DiscussPostMapper discussPostMapper;

    public List<DiscussPost> findDiscussPost(int userId,int offset,int limit){
        //对集合中每一个DiscussPost记录通过userId查用户名,便于使用redis
        return discussPostMapper.selectDiscussPosts(userId,offset,limit,0);
    }
    public int findDiscussPostRows(int userId){
        return discussPostMapper.selectDiscussPostRows(userId);
    }

}
