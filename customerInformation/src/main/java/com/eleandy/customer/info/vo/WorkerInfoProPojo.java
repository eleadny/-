package com.eleandy.customer.info.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

//应聘人员状态（已完成个人信息）
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WorkerInfoProPojo {
    private Long totalPeople;
    private Long workersOfInfo;
    private Double proportion;
}
