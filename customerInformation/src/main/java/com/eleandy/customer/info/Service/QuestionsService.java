package com.eleandy.customer.info.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.eleandy.customer.info.Pojo.QuestionsPojo;

public interface QuestionsService {
    Page<QuestionsPojo> selectUserAdvance(Page<QuestionsPojo> page,String userName);
}
