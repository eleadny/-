package com.eleandy.admin.login.Service;

import com.eleandy.admin.login.Pojo.AdminUserInfoPojo;

public interface AdminUserLoginService {
    String verifyAdministratorIdentity(AdminUserInfoPojo adminUserInfoPojo);
    void adminUserLoginOut(AdminUserInfoPojo adminUserInfoPojo, String ipAddr);
    AdminUserInfoPojo selectUserInfo(String session);

}
