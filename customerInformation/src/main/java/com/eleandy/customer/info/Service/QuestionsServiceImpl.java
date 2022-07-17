package com.eleandy.customer.info.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eleandy.customer.info.Mapper.wangge.QuestionsMapper;
import com.eleandy.customer.info.Pojo.QuestionsPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//问题提交处理业务
@Service
public class QuestionsServiceImpl implements QuestionsService {
    @Autowired
    private QuestionsMapper questionsMapper;
    @Override
    public Page<QuestionsPojo> selectUserAdvance(Page<QuestionsPojo> page, String userName) {
        boolean searchUser = true;
        QueryWrapper<QuestionsPojo> advances = new QueryWrapper<>();
        if(userName.equals("ALL")){
            searchUser = false;
        }
        advances.select("id","question_con","phone_number","user_id","user_name").eq(searchUser,"user_name",userName);
        return questionsMapper.selectPage(page,advances);
    }
}
