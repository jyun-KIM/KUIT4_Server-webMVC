package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class HomeController implements Controller {
    private QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Question> questionList = questionDao.getAllQuestion();
        req.setAttribute("questionList", questionList);

        return "/home.jsp";
    }
}
