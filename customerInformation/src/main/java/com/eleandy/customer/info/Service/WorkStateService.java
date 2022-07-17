package com.eleandy.customer.info.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eleandy.customer.info.Pojo.SubjectWorksPojo;
import com.eleandy.customer.info.Pojo.UserInfoPojo;

public interface WorkStateService {
    Page<SubjectWorksPojo> selectUndeterminedOrDetermineWorks(Page<SubjectWorksPojo> page, Integer workState, String state);
    UserInfoPojo viewWorkInfo(Integer publisher);
    int workUpdatesOrDown(Integer workId, Integer workShelves, String passState);
}
