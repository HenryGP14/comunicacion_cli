/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author henry
 */
@WebServlet(name = "cmdComand", urlPatterns = {"/cmdComand"})
public class cmdComand extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=iso-8859-1");
        PrintWriter out = response.getWriter();
        String comando = request.getParameter("nombre");
        try {
            Process process = Runtime.getRuntime().exec("cmd /c " + comando);//cmd /c dir C:\\Users\\jhonp\\OneDrive\\Escritorio
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String resultado = null;
            while ((resultado = br.readLine()) != null) {
                out.write("<p>" + resultado + "</p>");
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        
        Runtime cmd = Runtime.getRuntime();
        InputStream in = null;
        try {
            String come = "cmd /c php --version";
            Process exec = cmd.exec(come);
            in = exec.getInputStream();
            int length = -1;
            byte[] buffer = new byte[1024];
            StringBuilder sb = new StringBuilder();
            while ((length = in.read(buffer)) != -1){
               sb.append(new String(buffer, 0, length, "GBK"));
            }
            System.out.println(sb.toString());
           
        } catch (Exception ex) {
            System.out.println("ex:" + ex.getMessage());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
