package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/updateForm")
public class UpdateUserFormController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");         // 수정되는 user
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        HttpSession session = req.getSession();                    // 수정하는 user
        Object value = session.getAttribute("user");

        if (user != null && value != null) {
            if (user.equals(value)){            // 수정되는 user와 수정하는 user가 동일한 경우
                RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
                rd.forward(req, resp);
                return;
            }
        }
        resp.sendRedirect("/");
    }
}