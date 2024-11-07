package com.briup.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author YuYan
 * @date 2024-10-28 14:54:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String username;
    private String password;
    private String gender;
    private Integer age;
    private Date dob;
    private String email;
    private String introduction;
    private Integer education;

}
