package com.eleandy.customer.info.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eleandy.customer.info.Mapper.wangge.SubjectWorksMapper;
import com.eleandy.customer.info.Mapper.wangge.UserInfoMapper;
import com.eleandy.customer.info.Pojo.SubjectWorksPojo;
import com.eleandy.customer.info.Pojo.UserInfoPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class WorkStateServiceImpl implements WorkStateService {
    @Autowired
    private SubjectWorksMapper subjectWorksMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public Page<SubjectWorksPojo> selectUndeterminedOrDetermineWorks(Page<SubjectWorksPojo> page, Integer workState, String state) {
        QueryWrapper<SubjectWorksPojo> works = new QueryWrapper<>();
        boolean noPassBool = false;
        boolean passBool = true;
        List<String> list = null;
        if (state.split(",").length >= 2){
            list = Arrays.asList(state.split(","));
            noPassBool = true;
            passBool = false;
        }
        if(state.equals("未审核"))
            state = "";
        works.select("update_time","work_id","work_name","work_address","job_description","work_dollor_top","work_dollor_down","education","welfare","publisher","shelves").
                eq("shelves",workState).eq(passBool,"pass_state",state).in(noPassBool,"pass_state",list);
        return subjectWorksMapper.selectPage(page, works);
    }

    @Override
    public UserInfoPojo viewWorkInfo(Integer publisher) {
        QueryWrapper<UserInfoPojo> publisherInfo = new QueryWrapper<>();
        publisherInfo.select("user_id","user_name","user_tel","user_email").eq("user_id",publisher);
        return userInfoMapper.selectOne(publisherInfo);
    }

    @Override
    public int workUpdatesOrDown(Integer workId, Integer workShelves, String passState) {
        QueryWrapper<SubjectWorksPojo> changeWorkShelves = new QueryWrapper<>();
        changeWorkShelves.select("work_id","shelves","pass_state").eq("work_id",workId);
        SubjectWorksPojo changeShelves = new SubjectWorksPojo();
        changeShelves.setWorkId(workId).setShelves(workShelves).setPassState(passState);
        return subjectWorksMapper.update(changeShelves, changeWorkShelves);
    }
}
