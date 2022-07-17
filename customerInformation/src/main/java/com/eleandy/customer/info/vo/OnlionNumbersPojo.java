package com.eleandy.customer.info.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
/*
* 在线用户人数、比例、总人数*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class OnlionNumbersPojo {
    private Long totalPeople;
    private Long onlions;
    private Double proportion;
}
