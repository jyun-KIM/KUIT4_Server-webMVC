package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import core.jdbc.KeyHolder;
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

    public Question insert(Question question) throws SQLException {
        String sql = "INSERT INTO QUESTIONS VALUES(?,?,?,?,?)";

        KeyHolder keyHolder = new KeyHolder();
        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setDate(4, question.getCreatedDate());
            pstmt.setInt(5, question.getCountOfAnswer());
        };
        jdbcTemplate.update(sql, pstmtSetter, keyHolder);

        int questionId = keyHolder.getId();
        question.setQuestionId(questionId);

        return findByQuestionId(questionId);
    }

    public Question findByQuestionId(int questionId) throws SQLException {
        String sql = "SELECT * FROM QUESTIONS WHERE questionId = ?";

        PreparedStatementSetter pstmtSetter = pstmt -> {
            pstmt.setInt(1, questionId);
        };

        RowMapper<Question> rowMapper = rs -> new Question(rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents"),
                rs.getDate("createdDate"),
                rs.getInt("countOfAnswer"));

        return jdbcTemplate.queryForObject(sql, pstmtSetter, rowMapper);
    }
}
