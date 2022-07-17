package com.eleandy.customer.info.Pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("notice_infos")
public class NoticeInfosPojo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String publisher;
    private Integer noticeType;
    private Date noticeTime;
    private String noticeTittle;
    private Integer receiverId;
    private String receriver;
    private String noticeInfo;
}
