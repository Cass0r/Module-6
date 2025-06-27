package com.example.module_6;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/todo")
public class ToDoServlet extends HttpServlet {
    private to_do_item manager;

    @Override
    public void init() throws ServletException {
        manager = new to_do_item();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null || action.equals("list")) {
            List<Items> items = manager.getAllItems();
            request.setAttribute("items", items);
            request.getRequestDispatcher("list.jsp").forward(request, response);
        } else if (action.equals("add")) {
            request.getRequestDispatcher("add.jsp").forward(request, response);
        } else if (action.equals("delete")) {
            request.getRequestDispatcher("delete.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            int status = Integer.parseInt(request.getParameter("status"));

            Items item = new Items(id, name, description, status);
            manager.Add_Item(item);
            response.sendRedirect("todo?action=list");

        } else if ("delete".equals(action)) {
            String id = request.getParameter("id");
            manager.Delete_item(id);
            response.sendRedirect("todo?action=list");
        }
    }
}
