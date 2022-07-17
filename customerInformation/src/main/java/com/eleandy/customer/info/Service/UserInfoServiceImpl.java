package com.eleandy.customer.info.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eleandy.customer.info.Mapper.wangge.UserInfoMapper;
import com.eleandy.customer.info.Mapper.wangge.UserOtherInfoMapper;
import com.eleandy.customer.info.Pojo.AdminUserInfoPojo;
import com.eleandy.customer.info.Pojo.UserInfoPojo;
import com.eleandy.customer.info.Pojo.UserOtherInfoPojo;
import com.eleandy.customer.info.vo.OnlionNumbersPojo;
import com.eleandy.customer.info.vo.WorkerInfoProPojo;
import com.eleandy.customer.info.vo.WorksProportionPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserOtherInfoMapper userOtherInfoMapper;
    boolean userNameBool = false;
    @Override
    public Page<UserInfoPojo> selectUserInfo(Page<UserInfoPojo> selectUser, String userName) {
        if (!userName.equals("all")){
            userNameBool = true;
        }
        QueryWrapper<UserInfoPojo> selectUserQuery = new QueryWrapper<>();
        selectUserQuery.select("user_id","user_name","user_tel","user_email","date_time","registration_time","identity_status","status").
        like(userNameBool,"user_name",userName);
        return userInfoMapper.selectPage(selectUser,selectUserQuery);
    }

    @Override
    public void changeUserStatus(UserInfoPojo userInfoPojo) {
        QueryWrapper<UserInfoPojo> changeStatus = new QueryWrapper<>();
        changeStatus.select("status").eq("user_id",userInfoPojo.getUserId());
        userInfoMapper.update(userInfoPojo,changeStatus);
    }

    @Override
    public OnlionNumbersPojo selectOnlionUser() {
        OnlionNumbersPojo onlionNumbersPojo = new OnlionNumbersPojo();
        QueryWrapper<UserInfoPojo> onlionNumber = new QueryWrapper<>();
        onlionNumber.select("user_id");
        Long allUser = userInfoMapper.selectCount(onlionNumber);
        onlionNumber.eq("token","");
        Long onlionUser = userInfoMapper.selectCount(onlionNumber);
        Double proportion = Double.valueOf(String.format("%.1f", ((double) (allUser - onlionUser) / allUser) * 100));
        onlionNumbersPojo.setOnlions(onlionUser).setTotalPeople(allUser).setProportion(proportion);
        return onlionNumbersPojo;
    }

    @Override
    public WorksProportionPojo selectWorksproportion() {
        WorksProportionPojo worksProportionPojo = new WorksProportionPojo();
        QueryWrapper<UserInfoPojo> worker = new QueryWrapper<>();
        worker.select("user_id");
        Long allUser = userInfoMapper.selectCount(worker);
        worker.eq("identity_status",0);
        Long staticeNumber = userInfoMapper.selectCount(worker);
        Double proportion = Double.valueOf(String.format("%.1f",((double) (staticeNumber)/allUser)*100));
        worksProportionPojo.setWorkers(staticeNumber).setProportion(proportion).setTotalPeople(allUser);
        return worksProportionPojo;
    }

    @Override
    public WorkerInfoProPojo selectWorksInfoProportion() {
        WorkerInfoProPojo workerInfo = new WorkerInfoProPojo();
        QueryWrapper<UserOtherInfoPojo> worksInfoQuer = new QueryWrapper<>();
        worksInfoQuer.select("user_id");
        Long allUser = userOtherInfoMapper.selectCount(worksInfoQuer);
        worksInfoQuer.isNotNull("user_name");
        Long completeUser = userOtherInfoMapper.selectCount(worksInfoQuer);
        double proportion = Double.parseDouble(String.format("%.1f",((double) (completeUser)/allUser)*100));
        workerInfo.setProportion(proportion).setTotalPeople(allUser).setWorkersOfInfo(completeUser);
        return workerInfo;
    }


////    检索用户身份
//
//    public String inspectUser(String token){
//        QueryWrapper<AdminUserInfoPojo> userInspect = new QueryWrapper<>();
//        userInspect.select("account_type").eq("token",token);
//
//    }
}
