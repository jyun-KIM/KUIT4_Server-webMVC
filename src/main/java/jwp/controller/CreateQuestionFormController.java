package jwp.controller;

import core.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateQuestionFormController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");

        if (value != null) {          // 회원만 질문 등록 가능
            return "/qna/form.jsp";
        }
        return "redirect:/user/loginForm";
    }
}
