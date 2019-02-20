package cn.linjk.mybatistest.mapper;

import cn.linjk.mybatistest.domain.Role;
import cn.linjk.mybatistest.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    User selectById(Long id);
    List<User> selectAll();
    List<Role> selectRolesbyUserId(Long id);
    int insert(User user);
    int updateByUserId(User user);
    int deleteByUserId(Long userId);

    // 注解用法
    @Select({"SELECT name, password FROM t_user WHERE user_id = #{id}"})
    User selectByIdAnnotation(Long id);
}