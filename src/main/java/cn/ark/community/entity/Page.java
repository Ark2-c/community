package cn.ark.community.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * @program: community
 * @description:封装分页相关信息
 * @author: Ark
 * @create: 2022-02-18 14:14
 **/
@Getter
@Setter
public class Page {

    //当前页码
    private int current = 1;
    //显示上限
    private int limit = 10;
    //数据总数
    private int rows;
    //查询路径
    private String path;

    public void setLimit(int limit) {
        if (limit >= 1 && limit <= 100) {
            this.limit = limit;

        }
    }

    public void setRows(int rows) {
        if (rows >= 0)
            this.rows = rows;
    }

    /**
     * 获得当前页的起始值
     *
     * @return
     */
    public int getOffset() {
        return (current - 1) * limit;
    }

    /**
     * 获得总页数
     *
     * @return
     */
    public int getTotal() {
        if (rows % limit == 0) return rows / limit;
        else return rows / limit + 1;
    }

    /**
     * 获得起始页号
     * @return
     */
    public int getFrom() {
        int from = current - 2;
        return from <1 ? 1 : from;
    }

    /**
     * 获得结束页号
     * total 总页数
     * @return
     */
    public int getTo() {
        int to = current + 2;
        int total = getTotal();
        return to >total ? total : to;
    }
}
