package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/user/update")
public class UpdateUserController extends HomeController{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        User updateUser = new User(user.getUserId(), password, name, email);

        user.update(updateUser);
        MemoryUserRepository.getInstance().changeUserInfo(updateUser);

        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        req.setAttribute("users", users);
        RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
        rd.forward(req,resp);

        System.out.println("update유저: " + updateUser.toString());
    }
}
