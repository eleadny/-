package com.eleandy.admin.login.Pojo;

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
@TableName("recordSheet")
public class RecordSheetPojo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String userName;
    private String exitTime;
    private String addressId;
}
