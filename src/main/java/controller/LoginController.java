package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/login")
public class LoginController extends HomeController{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        User user = MemoryUserRepository.getInstance().findUserById(userId);

        if(user.getPassword().equals(password)){
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            System.out.println("로그인");
            resp.sendRedirect("/");
//            resp.sendRedirect("/home.jsp");
        }else{
            System.out.println("로그인 실패");
            resp.sendRedirect("/user/login_failed.jsp");

        }
    }
}
