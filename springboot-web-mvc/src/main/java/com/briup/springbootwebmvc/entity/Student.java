package com.briup.springbootwebmvc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 14409
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {


        private String name;
        private String gender;
        private Integer age;

}
