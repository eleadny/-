package com.eleandy.customer.info.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eleandy.customer.info.Pojo.NoticeInfosPojo;

import java.text.ParseException;
import java.util.List;

public interface NoticeInfoService {
    Page<NoticeInfosPojo> selectNoticeInfos(Page<NoticeInfosPojo> page, String userName) throws ParseException;

    boolean insertNewNotice(NoticeInfosPojo noticeInfosPojo);

    boolean deleteNotice(NoticeInfosPojo noticeInfosPojo);

    List<NoticeInfosPojo> getNoticeInfos();
}
