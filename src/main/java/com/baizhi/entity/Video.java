package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Table(name ="yx_sp")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @Id
    private String id;

    private String title;

    private String description;
    @Column(name = "video_path")
    private String videoPath;
    @Column(name = "cover_path")
    private String coverPath;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(name = "upload_time")
    private Date uploadTime;
    @Column(name = "paly_count")
    private Integer palyCount;
    @Column(name = "like_count")
    private Integer likeCount;
    @Column(name = "category_id")
    private String categoryId;
    @Column(name = "group_id")
    private String groupId;

}