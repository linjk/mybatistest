package cn.linjk.mybatistest.mapper;

import cn.linjk.mybatistest.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    User selectById(Long id);
    User selectByUser(User user);
    List<User> selectByIdList(List<Long> idList);
    List<User> selectAll();
    User selectUserAndRoleById(Long id);
    int insert(User user);
    int insertList(List<User> userList);
    int updateByUserId(User user);
    int deleteByUserId(Long userId);

    // 注解用法
    @Select({"SELECT name, password FROM t_user WHERE user_id = #{id}"})
    User selectByIdAnnotation(Long id);
}