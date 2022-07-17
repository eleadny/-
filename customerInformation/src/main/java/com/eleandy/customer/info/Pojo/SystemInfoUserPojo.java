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
@TableName("System_info_user")
public class SystemInfoUserPojo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String systemInfo;
    private Integer userId;
    private String userName;
    private String infoTime;
}
