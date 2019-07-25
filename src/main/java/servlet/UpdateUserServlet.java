package servlet;

import model.User;
import service.UserServiceImpl;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/update", "/edit"}, description = "New User Form")
public class UpdateUserServlet extends HttpServlet {
    private UserService userService;

    public void init() {
        userService = UserServiceImpl.getUserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String country = request.getParameter("country");
        User user = new User(id, name, email, country);
        userService.editExistingUser(user);
        response.sendRedirect("list");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userService.getUserByID(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("create-user.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);
    }
}

