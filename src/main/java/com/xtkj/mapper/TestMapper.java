package com.xtkj.mapper;

import com.xtkj.bean.Test;
import org.springframework.stereotype.Repository;

@Repository
public interface TestMapper {
//    @Select(" select id, test1, test2 from test where id=#{id}")
    Test selectByPrimaryKey(Integer id);

}
