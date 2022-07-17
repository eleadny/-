package com.eleandy.customer.info.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eleandy.customer.info.Pojo.UserInfoPojo;
import com.eleandy.customer.info.vo.OnlionNumbersPojo;
import com.eleandy.customer.info.vo.WorkerInfoProPojo;
import com.eleandy.customer.info.vo.WorksProportionPojo;

public interface UserInfoService {
    Page<UserInfoPojo> selectUserInfo(Page<UserInfoPojo> selectUser, String userName);
    void changeUserStatus(UserInfoPojo userInfoPojo);
    OnlionNumbersPojo selectOnlionUser();
    WorksProportionPojo selectWorksproportion();
    WorkerInfoProPojo selectWorksInfoProportion();
}
