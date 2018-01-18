package br.com.ltjAdm.servlet;

import br.com.ltjAdm.dao.ConsuGrupoProduto;
import br.com.ltjAdm.dao.FiltroPesquisa;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;

import java.util.ArrayList;
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
public class ConsultaGrupoProduto extends HttpServlet {

    FiltroPesquisa filtra = new FiltroPesquisa();
    ArrayList valores = new ArrayList();
    ArrayList coluna = new ArrayList();
    String comSqlListCateProduto;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession sessao = request.getSession();

        if (sessao.getAttribute("nomeCompleto") == null) {

            response.sendRedirect("index.jsp");

        } else {

            try {

                String innerJoin = "grupo_produto";

                if (request.getParameter("filtroCodigoGrpProduto") == null) {
                    valores.add("N");
                    coluna.add("bloq_grupo");
                    comSqlListCateProduto = filtra.Filtrar(innerJoin, "1","desc", valores, coluna) + " limit 50";

                } else {

                    if (!request.getParameter("filtroCodigoGrpProduto").isEmpty()) {
                        coluna.add("id_grupo");
                        valores.add(request.getParameter("filtroCodigoGrpProduto"));
                    }

                    if (!request.getParameter("filtroNomeGrpProduto").isEmpty()) {
                        coluna.add("nome_grupo");
                        valores.add(request.getParameter("filtroNomeGrpProduto"));
                    }
                    
                    if (!request.getParameter("filtroBloqueadoGrpProduto").isEmpty()) {
                        coluna.add("bloq_grupo");
                        valores.add(request.getParameter("filtroBloqueadoGrpProduto"));
                    }

                    comSqlListCateProduto = filtra.Filtrar(innerJoin,request.getParameter("filtroOrdenarGrpProduto"),
                            request.getParameter("filtroOrdemGrpProduto"), valores, coluna) + " limit 50";
                }

                request.setAttribute("listaGrpProduto", ConsuGrupoProduto.ListaGrpProduto(comSqlListCateProduto));

//--------------------------------------------------------------------Dados topo-------------------------------------------------------------------------------------------------------------------
                request.setAttribute("modulo", "Grupo de produto");
                request.setAttribute("usuarioLogado", sessao.getAttribute("nomeCompleto"));

//------------------------------------------------------Envia dados para pagina grupoProduto--------------------------------------------------------------------------------------------------------
                request.getRequestDispatcher("grupoProduto.jsp").forward(request, response);

                valores.clear();
                coluna.clear();

            } catch (SQLException erroSQL) {
                out.println(erroSQL);
            } catch (IOException erroIO) {
                out.println(erroIO);
            } finally {
                out.close();
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
            Logger.getLogger(ConsultaGrupoProduto.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ConsultaGrupoProduto.class.getName()).log(Level.SEVERE, null, ex);
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
