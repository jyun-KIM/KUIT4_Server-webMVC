package core.jdbc;

import jwp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate<T> {
    public void update(String sql, PreparedStatementSetter pstmtSetter) throws SQLException {

        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmtSetter.setParameters(pstmt);

            pstmt.executeUpdate(); //db에 쿼리문 실행 > db에 쿼리문 저장
        }
    }

    public <I> List<T> query(String sql, RowMapper<T> rowMapper) throws SQLException{

        List<T> objects = new ArrayList<>();

        try (Connection conn = ConnectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()){
            while(rs.next()){
                T object = rowMapper.mapRow(rs);
                objects.add(object);
            }
        }
        return objects;
    }

    public T queryForObject(String sql, PreparedStatementSetter pstmtSetter, RowMapper<T> rowMapper) throws SQLException {
        ResultSet rs = null;
        T object= null;

        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmtSetter.setParameters(pstmt);

            rs = pstmt.executeQuery();
            while(rs.next()){
                object = rowMapper.mapRow(rs);
            }
        }finally {
            if(rs != null) rs.close();
        }
        return object;
    }
}
