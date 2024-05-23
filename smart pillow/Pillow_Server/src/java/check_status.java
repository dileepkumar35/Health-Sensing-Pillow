/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Logic.info;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sumit
 */
public class check_status extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            
            String health_data="";
            File fileh = new File(info.py_path+"health_test.txt"); 

            BufferedReader brh = new BufferedReader(new FileReader(fileh)); 

            String sth; 
            while ((sth = brh.readLine()) != null) {
            System.out.println(sth); 
            health_data=sth;
            }
//            String health_prediction="";
//            File filep = new File(info.py_path+"health_output.txt"); 
//
//            BufferedReader brp = new BufferedReader(new FileReader(filep)); 
//
//            String stp; 
//            while ((stp = brp.readLine()) != null) {
//            System.out.println(stp); 
//            health_prediction=stp;
//            }
            
            
            String pillow_data="";
            File file1 = new File(info.path+"pillow_test.txt"); 
            BufferedReader br1 = new BufferedReader(new FileReader(file1)); 
            String st1; 
            while ((st1 = br1.readLine()) != null) {
            System.out.println(st1); 
            pillow_data=st1;
            }
            
           
           
            System.out.println(health_data+"#"+pillow_data);
            out.print(health_data+"#"+pillow_data);
        }
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
