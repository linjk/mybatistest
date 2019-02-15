package cn.linjk.mybatistest.mapper;

import cn.linjk.mybatistest.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

public class UserMapperTest extends BaseMapperTest {

    @Test
    public void testSelectUserById() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectById(1L);
            Assert.assertNotNull(user);
            Assert.assertEquals("admin", user.getName());
        }
        finally {
            sqlSession.close();
        }
    }
}
