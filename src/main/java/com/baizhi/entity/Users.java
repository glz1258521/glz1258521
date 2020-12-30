package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Table(name ="yx_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    private String id;

    private String phone;

    private String username;
    @Column(name = "head_img")
    private String headImg;

    private String brief;

    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(name = "create_date")
    private Date createDate;
    @Transient
    private String score;

}