package cn.linjk.mybatistest.mapper;

import cn.linjk.mybatistest.domain.User;

import java.util.List;

public interface UserMapper {
    User selectById(Long id);
    List<User> selectAll();
}