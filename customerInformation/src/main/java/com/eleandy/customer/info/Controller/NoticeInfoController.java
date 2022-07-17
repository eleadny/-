package com.eleandy.customer.info.Controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eleandy.customer.info.Pojo.NoticeInfosPojo;
import com.eleandy.customer.info.Service.NoticeInfoService;
import com.eleandy.customer.info.inspect.InspectAnnotation;
import com.eleandy.customer.info.log.OperLog;
import com.eleandy.customer.info.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RefreshScope
@CrossOrigin
@RequestMapping("/notice")
public class NoticeInfoController {

    @Autowired
    private NoticeInfoService noticeInfoService;
//公告查询
    @GetMapping("/select/{pageNumber}/{userName}")
    public SysResult selectNoticeInfo(@PathVariable Integer pageNumber,@PathVariable String userName){
        Page<NoticeInfosPojo> page = new Page<>(pageNumber,6);
        Page<NoticeInfosPojo> noticeInfosPage = null;
        try {
            noticeInfosPage = noticeInfoService.selectNoticeInfos(page,userName);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return SysResult.success("200",noticeInfosPage);
    }
//公告写入
    @PostMapping("/insert")
    @OperLog(operDesc = "系统信息",operModul = "公告发送")
    @InspectAnnotation
    public SysResult insertNoticeInfo(@RequestBody NoticeInfosPojo noticeInfosPojo){
        boolean insertBool = noticeInfoService.insertNewNotice(noticeInfosPojo);
        if (insertBool && noticeInfosPojo.getNoticeType().equals(1))
            return SysResult.success("201");
        if (insertBool && noticeInfosPojo.getNoticeType().equals(0))
            return SysResult.success("200");
            return SysResult.falied("202");
    }
//    公告删除
    @PostMapping("/delete")
    @OperLog(operDesc = "信息删除",operModul = "公告删除")
    @InspectAnnotation
    public SysResult deleteNotice(@RequestBody NoticeInfosPojo noticeInfosPojo){
        boolean delete = noticeInfoService.deleteNotice(noticeInfosPojo);
        if (delete){
            return SysResult.success("删除成功");
        }
        return SysResult.falied("删除失败");
    }

//    前台获取
    @GetMapping("/getnotice")
    public SysResult getNotice(){
        List<NoticeInfosPojo> noticeLists = noticeInfoService.getNoticeInfos();
        return SysResult.success("200",noticeLists);
    }
}
