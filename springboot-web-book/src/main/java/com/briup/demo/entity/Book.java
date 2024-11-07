package com.briup.demo.entity;

import lombok.Data;

/**
 * 实体类 - 图书
 *
 * @author YuYan
 * @date 2024-10-31 15:50:00
 */
@Data
public class Book {

    /**
     * 主键
     */
    private Integer id;
    /**
     * 图书名称
     */
    private String name;
    /**
     * 图书描述
     */
    private String description;
    /**
     * 作者
     */
    private String author;
    /**
     * 出版社
     */
    private String publisher;
    /**
     * 价格
     */
    private String price;
    /**
     * 库存数量
     */
    private Integer storeNum;
    /**
     * 状态（0-上架，1-下架）
     */
    private Integer status;
    /**
     * 分类ID
     */
    private Integer categoryId;

    /**
     * 所属分类
     */
    private Category category;

}
