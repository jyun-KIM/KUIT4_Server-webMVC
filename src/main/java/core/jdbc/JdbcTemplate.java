package core.jdbc;

import jwp.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// JdbeTemplate은 JDBC를 사용한 데이텋베이스 작업을 쉽게 처리할 수 있도록 도와준다.
public class JdbcTemplate<T> {
    public void update(String sql, PreparedStatementSetter pstmtSetter) throws SQLException {

        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmtSetter.setParameters(pstmt);

            pstmt.executeUpdate(); //db에 쿼리문 실행 > db에 쿼리문 저장
        }
    }

    public void update(String sql, PreparedStatementSetter pstmtSetter, KeyHolder holder) {
        try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmtSetter.setParameters(pstmt);
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                holder.setId((int) rs.getLong(1));
            }
            rs.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
