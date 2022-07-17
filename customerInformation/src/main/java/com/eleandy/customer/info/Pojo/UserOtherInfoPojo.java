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
@TableName("User_info")
public class UserOtherInfoPojo {
    private Integer userId;
    private String userName;
    private String workComeTime;
    private String schoolOverTime;
    private String identity;
    private String birthday;
    private String telephone;
    private String weixin;
    private String email;
}
