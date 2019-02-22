package cn.linjk.mybatistest.mapper;

import cn.linjk.mybatistest.domain.Autority;
import cn.linjk.mybatistest.domain.Role;
import cn.linjk.mybatistest.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
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
    public void testSelectUserByIdList() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<Long> idList = new ArrayList<>();
            idList.add(1L);
            idList.add(2L);
            List<User> result = userMapper.selectByIdList(idList);
            Assert.assertEquals(2, result.size());
        }
        finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectUserAndRoleById() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectUserAndRoleById(1L);
            Assert.assertNotNull(user);
            // <setting name="aggressiveLazyLoading" value="false"/>
            // 需要在mybatis-config.xml加上这个配置
            System.out.println("懒加载查询role表......");
            Assert.assertNotNull(user.getRole());
        }
        finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSelectAllUserAndRoles() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userMapper.selectAllUserAndRoles();
            System.out.println("用户数: " + userList.size());
            for (User user : userList) {
                System.out.println("用户名: " + user.getName());
                for (Role role : user.getRoleList()) {
                    System.out.println("-- 角色名: " + role.getName());
                    for (Autority autority : role.getAutorityList()) {
                        System.out.println("  -- 权限名： " + autority.getName());
                    }
                }
            }
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
    public void testInsertList() {
        SqlSession sqlSession = getSqlSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> users = new ArrayList<>();
            for (int i = 3; i < 5; i++) {
                User user = new User();
                user.setUserId(Long.valueOf(i));
                user.setName("test");
                user.setPassword("test");
                user.setLastLogin(new Date());
                users.add(user);
            }
            int result = userMapper.insertList(users);
            Assert.assertEquals(2, result);
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
