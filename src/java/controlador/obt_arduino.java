/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.BufferedReader;
import java.io.IOException;
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
@WebServlet(name = "obt_arduino", urlPatterns = {"/obt_arduino"})
public class obt_arduino extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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
        PrintWriter out = response.getWriter();
        String comando = "arduino-cli board list";
        try {
            Process process = Runtime.getRuntime().exec("cmd /c D:\\\\Arduino\\" + comando);//cmd /c dir C:\\Users\\jhonp\\OneDrive\\Escritorio
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String resultado = null;
            String inicio = "<div class=\"form-check\">";
            String intermedio = " <label class=\"form-check-label nom_placa\" for=\"flexRadioDefault1\"> ";
            String fin = "</label>" + "</div>";
            if (br.readLine() == null) {
                out.write("La placa no esta conectada o no la reconoce");
            }
            boolean hay_placa = false;
            int num_placa = 1;
            while ((resultado = br.readLine()) != null) {
                if (resultado.length() != 0) {
                    out.write(inicio
                            + " <input class=\"form-check-input\" type=\"radio\" name=\"placa\" id=\"placa-" + num_placa + "\"> "
                            + intermedio + resultado + fin);
                    hay_placa = true;
                    num_placa = num_placa + 1;
                }
            }
            if (!hay_placa) {
                out.write("La placa no esta conectada o no la reconoce");
            }

        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        processRequest(request, response);
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
