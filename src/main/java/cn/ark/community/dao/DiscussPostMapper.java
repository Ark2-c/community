package cn.ark.community.dao;

import cn.ark.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {


    /**
     * @param userId    用于实现在个人主页中查找我发布的贴子，通过id进行判断，
     *                  实现是使用动态sql对userid的值进行判断，
     *                  为0则查所有帖子，否则需要判断userid
     * @param offset    起始行的行号
     * @param limit     每页最多显示的数据数
     * @param orderMode 排序模式，默认为0。1是按帖子热度排序
     * @return 符合条件的帖子集合
     */
    List<DiscussPost> selectDiscussPosts(
            @Param("userId") int userId, @Param("offset") int offset, @Param("limit") int limit, @Param("orderMode") int orderMode);

    // @Param注解用于给参数取别名，只有一个变量可以不加，要是有两个一定要加，所以一般都加上比较保险
    //

    /**
     * 查询用户发布的帖子的行数
     *
     * @param userId 当userId为0时，查询所有帖子数，否则查询userId发布的帖子数
     *               用于动态sql查询，sql在<if>里使用,则所有参数必须加别名.
     * @return
     */
    int selectDiscussPostRows(@Param("userId") int userId);

    int insertDiscussPost(DiscussPost discussPost);

    DiscussPost selectDiscussPostById(@Param("id") int id);

    int updateCommentCount(@Param("id") int id, @Param("commentCount") int commentCount);

    int updateType(@Param("id") int id, @Param("type") int type);

    int updateStatus(@Param("id") int id, @Param("status") int status);

    int updateScore(@Param("id") int id, @Param("score") double score);
}
