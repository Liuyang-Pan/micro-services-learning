package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author 16798
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2022-11-18 11:57:39
 * @Entity org.example.entity.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




