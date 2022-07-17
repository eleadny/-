package com.eleandy.customer.info.Pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("subject_works")
public class SubjectWorksPojo {
    private Integer workId;
    private String workName;
    private String workAddress;
    private String workTime;
    private String education;
    private String workDollorDown;
    private String workDollorTop;
    private String muchDollor;
    private String companyName;
    private String publisher;
    private String welfare;
    private String jobDescription;
    private String updateTime;
    private Integer shelves;
    private String passState;
}