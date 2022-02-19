package cn.ark.community.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

//indexName 索引名字  shards 分片   replicas 副本

@Getter
@Setter
@ToString
public class DiscussPost {
    private int id;

    private int userId;

    // 搜索帖子主要依据title、content
    //存储的时候用analyzer分词器，搜索的时候用searchAnalyzer分词器
    private String title;

    private String content;

    private int type;

    private int status;

    private Date createTime;

    private int commentCount;

    private double score;

}
