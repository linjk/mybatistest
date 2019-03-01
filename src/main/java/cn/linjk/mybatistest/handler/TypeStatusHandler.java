package cn.linjk.mybatistest.handler;

import cn.linjk.mybatistest.type.TypeStatus;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class TypeStatusHandler implements TypeHandler<TypeStatus> {
    private final Map<Integer, TypeStatus> typeStatusMap = new HashMap<>();

    public TypeStatusHandler() {
        for (TypeStatus typeStatus : TypeStatus.values()) {
            typeStatusMap.put(typeStatus.getValue(), typeStatus);
        }
    }

    @Override
    public void setParameter(PreparedStatement ps, int i, TypeStatus parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setInt(i, parameter.getValue());
    }

    @Override
    public TypeStatus getResult(ResultSet rs, String columnName) throws SQLException {
        Integer value = rs.getInt(columnName);
        return typeStatusMap.get(value);
    }

    @Override
    public TypeStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        Integer value = rs.getInt(columnIndex);
        return typeStatusMap.get(value);
    }

    @Override
    public TypeStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        Integer value = cs.getInt(columnIndex);
        return typeStatusMap.get(value);
    }
}
