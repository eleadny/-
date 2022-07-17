package com.eleandy.customer.info.Pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("adminUserInfo")
public class AdminUserInfoPojo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String userNumber;
    private String userPassword;
    private Long loginTime;
    private Integer errorNumber;
    private Integer userStatic;
    private Integer loginStatic;
    private String token;
    private String accountType;
}
