package com.eleandy.customer.info.log;

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
@TableName("operLog")
public class OperLogPojo {
    @TableId(type = IdType.AUTO)
    private Integer id;//
    private String userName;//
    private String operModule;//
    private String operMethod;//
    private String operInfo;//操作信息报告
    private String operDesp;//
    private String operUrl;//
    private String addressIp;//
    private String operTime;//
}
