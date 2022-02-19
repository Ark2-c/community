package cn.ark.community.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @program: community
 * @description:
 * @author: Ark
 * @create: 2022-02-16 15:48
 **/

@Getter
@Setter
@ToString
public class Comment {

    private int id;
    private int userId;
    private int entityType;
    private int entityId;
    private int targetId;
    private String  content;
    private int status;
    private Date createTime;
}
