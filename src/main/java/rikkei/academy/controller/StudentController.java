package rikkei.academy.controller;

import rikkei.academy.model.Students;
import rikkei.academy.service.IStudentService;
import rikkei.academy.service.StudentServiceIMPL;

import java.io.*;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(value = {"/", "/students"})
public class StudentController extends HttpServlet {
private IStudentService studentService = new StudentServiceIMPL();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if(action==null){
            action = "";
        }
        switch (action){
            case "create":
                showFormCreate(request, response);
                break;
            case "edit":
                showFormEdit(request,response);
                break;
            case "delete":
                fromDeleteStudent(request,response);
                break;
            case "detail":
                formDetailStudents(request,response);
                break;
            default:
                showListStudent(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if(action==null){
            action = "";
        }
        switch (action){
            case "create":
                actionCreate(request, response);
                break;
            case "edit":
                actionEdit(request, response);
                break;
            case "delete":
                actionDeleteStudent(request,response);
                break;
            default:
                actionSearch(request,response);
        }
    }

    public void destroy() {
    }

    public void showListStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Students> studentsList = studentService.findAll();
        request.setAttribute("listStudent", studentsList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/student/list.jsp");
        dispatcher.forward(request, response);
    }
    public void showFormCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/student/create.jsp");
        dispatcher.forward(request, response);
    }
    public void actionCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        Students students = new Students(name,age);
        studentService.save(students);
        request.setAttribute("message", "Create Student success!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/student/create.jsp");
        dispatcher.forward(request, response);
    }
    public void showFormEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Students students = studentService.findById(id);
        System.out.println("student show ---> "+students);
        request.setAttribute("student",students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/student/edit.jsp");
        dispatcher.forward(request, response);
    }
    public void actionEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Students students = studentService.findById(id);
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        students.setName(name);
        students.setAge(age);
        studentService.save(students);
        request.setAttribute("message", "Edit success!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/student/edit.jsp");
        dispatcher.forward(request, response);
    }
    public  void fromDeleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Students students = studentService.findById(id);
        System.out.println("student show ---> "+students);
        request.setAttribute("student",students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/student/delete.jsp");
        dispatcher.forward(request, response);
    }
    public void actionDeleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentService.deleteById(id);
        request.setAttribute("message", "Delete success!");
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/student/delete.jsp");
        dispatcher.forward(request, response);
    }
    public void formDetailStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Students students = studentService.findById(id);
        request.setAttribute("student",students);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/student/detail.jsp");
        dispatcher.forward(request, response);
    }
    public void actionSearch(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("search");
        List<Students> studentListSearch = studentService.findByName(name);
        request.setAttribute("listStudent", studentListSearch);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/student/list.jsp");
        dispatcher.forward(request, response);
        System.out.println("==================tìm ==== "+studentService.findByName(name));
    }
}