package com.besofty.firstproject.common.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageDTO {
    /**
     * 当前页
     */
    private Integer pageNum;
    /**
     * 每页数据条数
     */
    private Integer pageSize;
    /**
     * 总记录数
     */
    private Long total;
    /**
     * 返回的数据结果集
     */
    private List list;

    public PageDTO(int pageNum, int pageSize, List list, long total) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.list = list;
        this.total = total;
    }

}
