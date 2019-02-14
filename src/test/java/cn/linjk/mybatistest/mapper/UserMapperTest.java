package cn.linjk.mybatistest.mapper;

import cn.linjk.mybatistest.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class UserMapperTest {

    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectUsers() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            List<User> userList = sqlSession.selectList("selectAllUser");
            for (User user : userList) {
                System.out.printf("%-4d%4s%4s\n",
                        user.getUserId(), user.getName(), user.getPasswd());
            }
        }
        finally {
            sqlSession.close();
        }
    }
}
