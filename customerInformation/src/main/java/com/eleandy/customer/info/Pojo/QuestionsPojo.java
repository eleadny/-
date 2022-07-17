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
@TableName("Questions")
public class QuestionsPojo {
    private Integer id;
    private String questionCon;
    private String phoneNumber;
    private Integer userId;
    private String userName;
}
