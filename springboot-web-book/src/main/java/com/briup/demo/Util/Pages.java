package com.briup.demo.Util;

import lombok.Data;

import java.util.List;

/**
 * @author 14409
 */

@Data
public class Pages<T> {

    private List<T> data;//数据列表

    private Integer total;//数据总量
}
