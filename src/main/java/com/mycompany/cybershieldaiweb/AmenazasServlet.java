/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cybershieldaiweb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import com.mycompany.cybershieldaiweb.dao.AmenazaDAO;
import com.mycompany.cybershieldaiweb.dao.AnalisisDAO;  // Importar el DAO de análisis
import com.mycompany.cybershieldaiweb.model.Amenaza;
import com.mycompany.cybershieldaiweb.model.Analisis;  // Importar el modelo de análisis

@WebServlet("/amenazas")
public class AmenazasServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AmenazaDAO amenazaDAO = new AmenazaDAO();
        AnalisisDAO analisisDAO = new AnalisisDAO();  // Instancia del AnalisisDAO

        // Obtener las amenazas críticas
        List<Amenaza> amenazas = amenazaDAO.getAmenazasCriticas();

        // Para cada amenaza, obtener el análisis relacionado y asignarlo
        for (Amenaza amenaza : amenazas) {
            Analisis analisis = analisisDAO.getAnalisisById(amenaza.getAnalisis_idAnalisis());
            amenaza.setAnalisis(analisis);  // Asignar el análisis a la amenaza
        }

        // Pasar la lista de amenazas con análisis a la JSP
        request.setAttribute("amenazas", amenazas);
        request.getRequestDispatcher("detallesAnalisis.jsp").forward(request, response);
    }
}

