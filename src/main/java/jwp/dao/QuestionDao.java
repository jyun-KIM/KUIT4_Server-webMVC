package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import jwp.model.Question;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class QuestionDao {
    private final JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate();

    public List<Question> getAllQuestion() throws SQLException {
        String sql = "select * from QUESTIONS";

        RowMapper<Question> rowMapper = rs -> new Question(rs.getInt("questionId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getDate("createdDate"),
                rs.getInt("countOfAnswer"));
        return jdbcTemplate.query(sql, rowMapper);
    }
}
