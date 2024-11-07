package jwp.controller.qna;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ShowController implements Controller {
    private QuestionDao questionDao= new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        String questionIdParams = req.getParameter("questionId");
        int questionId = Integer.parseInt(questionIdParams);

        Question question = questionDao.findByQuestionId(questionId);
        req.setAttribute("question", question);
        return "qna/show.jsp";
    }
}
