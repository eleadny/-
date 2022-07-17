package com.eleandy.admin.login.Mapper;

import com.eleandy.admin.login.Pojo.AdminUserInfoPojo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserInfoMapper extends BaseMapper<AdminUserInfoPojo> {
    @Update("UPDATE `AdminDatas`.`adminUserInfo` SET `token` = #{token},`login_time`=#{loginTime},`login_static`=#{loginStatic} WHERE `user_number` = #{userName};")
    void insertUserToken(String userName, String token,long loginTime, Integer loginStatic);
}
