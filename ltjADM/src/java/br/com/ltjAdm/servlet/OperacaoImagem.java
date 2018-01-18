package br.com.ltjAdm.servlet;

import br.com.ltjAdm.dao.ExecComandoSql;
import br.com.ltjAdm.upload.DeletaArquivo;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Junior
 */
public class OperacaoImagem extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession sessao = request.getSession();
        String path = getServletContext().getRealPath("/");
        File dir = new File(path + File.separator + "IMAGENS-PRODUTOS" + File.separator + request.getParameter("album"));

        if (sessao.getAttribute("nomeCompleto") == null) {

            response.sendRedirect("index.jsp");

        } else {
            if (request.getParameter("bntExcluir") != null) {
                DeletaArquivo.deleta(dir);
                ExecComandoSql.execComando("delete from imagem_produto where nome_album = '" + request.getParameter("album") + "'");
                response.sendRedirect("ConsultaImagemProduto?id=" + request.getParameter("album"));

            } else {

                try {
                    if (request.getParameter("operacao").equals("Torna-la Principal")) {

                        String padroniza = "update imagem_produto set imagem_principal = 'N' where cod_prod = '"
                                + request.getParameter("idImagPrinc") + "'";
                        ExecComandoSql.execComando(padroniza);
                        String setPrincipal = "update imagem_produto set imagem_principal = 'S' where id_imagem = '"
                                + request.getParameter("codImagem") + "'";
                        ExecComandoSql.execComando(setPrincipal);

                    } else {

                        File img = new File(dir + File.separator + request.getParameter("imagem"));
                        if (dir.listFiles().length > 1) {
                            DeletaArquivo.deleta(img);
                            ExecComandoSql.execComando("delete from imagem_produto where id_imagem ='"
                                    + request.getParameter("codImagem") + "'");
                        } else {
                            DeletaArquivo.deleta(dir);
                            ExecComandoSql.execComando("delete from imagem_produto where cod_prod ='"
                                    + request.getParameter("idImagPrinc") + "'");
                        }
                    }

                    response.sendRedirect("ConsultaImagemProduto?id=" + request.getParameter("idImagPrinc"));

                } catch (SQLException erroSQL) {
                    out.println(erroSQL);
                } catch (IOException erroIO) {
                    out.println(erroIO);
                } finally {
                    out.close();
                }
            }
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
            Logger.getLogger(OperacaoImagem.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(OperacaoImagem.class.getName()).log(Level.SEVERE, null, ex);
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
