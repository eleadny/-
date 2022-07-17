package com.eleandy.customer.info.Mapper.admin;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.eleandy.customer.info.Pojo.AdminUserInfoPojo;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@DS("admin")
public interface AdminUserInfoMapper extends BaseMapper<AdminUserInfoPojo> {
    @Update("UPDATE `AdminDatas`.`adminUserInfo` SET `token` = #{token},`login_time`=#{loginTime},`login_static`=#{loginStatic} WHERE `user_number` = #{userName};")
    void insertUserToken(String userName, String token,long loginTime, Integer loginStatic);
}
