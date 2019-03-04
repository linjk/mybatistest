package cn.linjk.mybatistest.mapper;

import cn.linjk.mybatistest.domain.Role;
import cn.linjk.mybatistest.domain.User;
import cn.linjk.mybatistest.plugin.SimpleInteceptor;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.decorators.LoggingCache;
import org.apache.ibatis.cache.decorators.LruCache;
import org.apache.ibatis.cache.decorators.SerializedCache;
import org.apache.ibatis.cache.decorators.SynchronizedCache;
import org.apache.ibatis.cache.impl.PerpetualCache;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LearningTest extends BaseMapperTest {
    @Test
    public void testSourceCode() throws SQLException {
        // ----- 1. 指定日志和创建配置对象
        LogFactory.useLog4JLogging();
        final Configuration config = new Configuration(); // MyBatis的配置类，对应于mybatis-config.xml的settings标签
        config.setCacheEnabled(true);
        config.setLazyLoadingEnabled(false);
        config.setAggressiveLazyLoading(true);
        // ----- 2. 添加拦截器
        SimpleInteceptor simpleInteceptor = new SimpleInteceptor();
        config.addInterceptor(simpleInteceptor);
        // ----- 3. 创建数据源和JDBC事务
        UnpooledDataSource dataSource = new UnpooledDataSource();
        dataSource.setDriver("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/mytest");
        dataSource.setUsername("root");
        dataSource.setPassword("ljk121121");

        Transaction transaction = new JdbcTransaction(dataSource, null, false);
        // ----- 4. 创建Executor
        final Executor executor = config.newExecutor(transaction);
        // ----- 5. 获取类型处理注册器
        final TypeHandlerRegistry registry = config.getTypeHandlerRegistry();
        // ----- 6. 创建SqlSource对象
        StaticSqlSource sqlSource = new StaticSqlSource(config, "SELECT * FROM t_role where role_id = ?");
        // ----- 7. 创建参数映射配置
        List<ParameterMapping> parameterMappingList = new ArrayList<>();
        parameterMappingList.add(new ParameterMapping.Builder(
                config, "role_id",
                registry.getTypeHandler(Long.class)
        ).build());
        ParameterMap.Builder parameterBuilder = new ParameterMap.Builder(
                config, "defaultParameterMap",
                User.class,
                parameterMappingList
        );
        // ----- 8. 创建结果映射
        ResultMap resultMap = new ResultMap.Builder(
                config, "defaultResultMap",
                Role.class,
                new ArrayList<ResultMapping>(){{
                    add(new ResultMapping.Builder(config, "roleId", "role_id", Long.class).build());
                    add(new ResultMapping.Builder(config, "name", "name", String.class).build());
                }}
        ).build();
        // ----- 9. 创建缓存对象
        final Cache userCache = new SynchronizedCache(
                new SerializedCache(new LoggingCache(new LruCache(new PerpetualCache("user_cache"))))
        );
        // ----- 10. 创建MappedSatement对象
        MappedStatement.Builder mBuilder = new MappedStatement.Builder(
                config,
                "cn.linjk.mybatistest.learning.selectRoleById",
                sqlSource, SqlCommandType.SELECT
        );
        mBuilder.parameterMap(parameterBuilder.build());
        List<ResultMap> resultMaps = new ArrayList<>();
        resultMaps.add(resultMap);
        mBuilder.resultMaps(resultMaps);
        mBuilder.cache(userCache);
        MappedStatement ms = mBuilder.build();
        List<Role> roles = executor.query(ms, 1L, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
        for (Role role : roles) {
            System.out.println("--> " + role.getName());
        }
    }
}
