package br.com.ltjAdm.servlet;

import br.com.ltjAdm.dao.ConsuUsuario;
import br.com.ltjAdm.dao.Criptografia;
import br.com.ltjAdm.model.BeanUsuario;

import java.io.IOException;
import java.io.PrintWriter;

import java.security.NoSuchAlgorithmException;

import java.sql.SQLException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author laudson
 */
public class ConsultaUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        List<BeanUsuario> listaUsuario;

        try {

            String comandoSQL = "select * from usuario_usuario where 1 and"
                    + " nome_usuario = '" + request.getParameter("nomeUsuario") + "' and senha = md5('" + request.getParameter("senha") + "')";
            listaUsuario = ConsuUsuario.ListaUsuario(comandoSQL);

            if (listaUsuario.isEmpty()) {
                response.sendRedirect("http://localhost:8084/ltjADM/");
            } else {
                if (Criptografia.verificaCriptografia(request.getParameter("senha")).equals(listaUsuario.get(0).getSenha())) {

                    HttpSession sessao = request.getSession();
                    sessao.setAttribute("nomeCompleto", listaUsuario.get(0).getNome_completo());
                    sessao.setAttribute("nivelAcesso", listaUsuario.get(0).getPermissao());

                    response.sendRedirect("ConsultaProduto");
                } else {
                    response.sendRedirect("http://localhost:8084/ltjADM/");
                }
            }
        } catch (SQLException erroSQL) {
            out.println(erroSQL);
        } finally {
            out.close();
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ConsultaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(ConsultaUsuario.class.getName()).log(Level.SEVERE, null, ex);
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
