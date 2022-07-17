package com.eleandy.customer.info.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eleandy.customer.info.Mapper.admin.NoticeInfosMapper;
import com.eleandy.customer.info.Mapper.wangge.UserInfoMapper;
import com.eleandy.customer.info.Pojo.NoticeInfosPojo;
import com.eleandy.customer.info.Pojo.UserInfoPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class NoticeInfoServiceImpl implements NoticeInfoService {

    @Autowired
    private NoticeInfosMapper noticeInfosMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    Date firstTime;
    Date secondTime;
    //  公告查询业务代码
    @Override
    public Page<NoticeInfosPojo> selectNoticeInfos(Page<NoticeInfosPojo> page, String time) throws ParseException {
        QueryWrapper<NoticeInfosPojo> selectNotices = new QueryWrapper<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        boolean timeTrue = (time.split(",").length >= 2);
        if (timeTrue){
            String[] split = time.split(",");
            firstTime = simpleDateFormat.parse(split[0]);
            secondTime = simpleDateFormat.parse(split[1]);
        }
        selectNotices.select("id","publisher","notice_type","receiver_id","receriver","notice_tittle","notice_info","notice_time").between(timeTrue,"notice_time",firstTime,secondTime);
        return noticeInfosMapper.selectPage(page,selectNotices);
    }

//    公告写入业务代码
    @Override
    public boolean insertNewNotice(NoticeInfosPojo noticeInfosPojo) {
        boolean idTrue;
        NoticeInfosPojo notices;
        QueryWrapper<NoticeInfosPojo> insertNotice = new QueryWrapper<>();
        QueryWrapper<UserInfoPojo> userInfo = new QueryWrapper<>();
        if (noticeInfosPojo.getNoticeType().equals(0) && !noticeInfosPojo.getReceriver().equals("")){
            userInfo.select("user_id","user_name").eq("user_name",noticeInfosPojo.getReceriver());
            UserInfoPojo userInfoPojo = userInfoMapper.selectOne(userInfo);
            if (userInfoPojo == null)
                return false;
            noticeInfosPojo.setReceiverId(userInfoPojo.getUserId());
        }
        if (noticeInfosPojo.getId() != null){
            idTrue = true;
        }else{
            idTrue = false;
        }
        insertNotice.select("id","publisher","notice_type","notice_tittle","receiver_id","receriver","notice_info","notice_time").
        eq(idTrue,"id",noticeInfosPojo.getId());
        if (idTrue){
            notices  = noticeInfosMapper.selectOne(insertNotice);
            if (notices != null){
                int update = noticeInfosMapper.update(noticeInfosPojo, insertNotice);
                return update > 0;
            }
        }
        int insertBool = noticeInfosMapper.insert(noticeInfosPojo);
        return insertBool > 0;
    }

    @Override
    public boolean deleteNotice(NoticeInfosPojo noticeInfosPojo) {
        QueryWrapper<NoticeInfosPojo> deleteNoticeWrapper = new QueryWrapper<>();
        deleteNoticeWrapper.eq("id",noticeInfosPojo.getId());
        int delete = noticeInfosMapper.delete(deleteNoticeWrapper);
        return delete>1;
    }

    @Override
    public List<NoticeInfosPojo> getNoticeInfos() {
        QueryWrapper<NoticeInfosPojo> selectNoticeList = new QueryWrapper<>();
        selectNoticeList.select("id","publisher","notice_type","notice_tittle","notice_info","notice_time").
                eq("notice_type",1).orderByDesc("id").last(" limit 5");
        List<NoticeInfosPojo> noticeInfosPojos = noticeInfosMapper.selectList(selectNoticeList);
        return noticeInfosPojos;
    }
}
