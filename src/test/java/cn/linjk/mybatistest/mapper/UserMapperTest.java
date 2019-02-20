package cn.linjk.mybatistest.mapper;

import cn.linjk.mybatistest.domain.Role;
import cn.linjk.mybatistest.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

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

    @Test
    public void testSelectRolesByUserId() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<Role> roleList = userMapper.selectRolesbyUserId(1L);
            Assert.assertNotNull(roleList);
            Assert.assertTrue(roleList.size() > 0);
        }
        finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsert() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User();
            user.setUserId(3L);
            user.setName("test");
            user.setPassword("test");
            user.setLastLogin(new Date());
            int result = userMapper.insert(user);
            Assert.assertEquals(1, result);
        }
        finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testUpdate() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectById(1L);
            Assert.assertEquals("admin", user.getName());
            user.setName("administrator");
            int result = userMapper.updateByUserId(user);
            Assert.assertEquals(1, result);
            user = userMapper.selectById(1L);
            Assert.assertEquals("administrator", user.getName());
        }
        finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }

    @Test
    public void testDelete() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper_in = sqlSession.getMapper(UserMapper.class);
            User user_in = new User();
            user_in.setUserId(3L);
            user_in.setName("test");
            user_in.setPassword("test");
            user_in.setLastLogin(new Date());
            int result = userMapper_in.insert(user_in);
            Assert.assertEquals(1, result);
            //
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectById(3L);
            Assert.assertNotNull(user);
            Assert.assertEquals(1, userMapper.deleteByUserId(3L));
            user = userMapper.selectById(3L);
            Assert.assertNull(user);
        }
        finally {
            sqlSession.rollback();
            sqlSession.close();
        }
    }
}
