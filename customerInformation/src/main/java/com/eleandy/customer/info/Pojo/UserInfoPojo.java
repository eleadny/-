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
@TableName("XianL_Wangge_User")
public class UserInfoPojo {
    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String userName;
    private String userPassword;
    private Long userTel;
    private String userEmail;
    private String status;
    private String token;
    private Long dateTime;
    private Integer identityStatus;
    private Date registrationTime;
}
