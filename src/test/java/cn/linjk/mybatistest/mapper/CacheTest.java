package cn.linjk.mybatistest.mapper;

import cn.linjk.mybatistest.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

public class CacheTest extends BaseMapperTest {
    @Test
    public void testLevelOneCache() {
        SqlSession sqlSession = getSqlSession();
        User user = null;
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            user = userMapper.selectById(1L);
            user.setName("New Name");
            // 注意：这里虽然创建了新的userTmp对象，但是，查询出的用户名仍然是`New Name`！，而并没有更新数据库操作！
            User userTmp = userMapper.selectById(1L);
            Assert.assertEquals("New Name", userTmp.getName());
            Assert.assertEquals(user, userTmp);
        }
        finally {
            sqlSession.close();
        }
        // 开启新session......
        sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User userNew = userMapper.selectById(1L);
            Assert.assertEquals("admin", userNew.getName());
        }
        finally {
            sqlSession.close();
        }
    }

    @Test
    public void testLevelTwoCache() {
        SqlSession sqlSession = getSqlSession();
        User user = null;
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            user = userMapper.selectById(1L);
            user.setName("New Name");
            User userTmp = userMapper.selectById(1L);
            Assert.assertEquals("New Name", userTmp.getName());
            Assert.assertEquals(user, userTmp);
        }
        finally {
            sqlSession.close();
        }
        // 开启新session......
        sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User userNew = userMapper.selectById(1L);
            Assert.assertEquals("New Name", userNew.getName());
        }
        finally {
            sqlSession.close();
        }
    }
}
