package com.eleandy.customer.info.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WorksProportionPojo {
    private Long totalPeople;
    private Long workers;
    private Double proportion;
}
