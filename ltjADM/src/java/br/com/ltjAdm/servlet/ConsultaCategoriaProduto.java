package br.com.ltjAdm.servlet;

import br.com.ltjAdm.dao.ConsuCategoriaProduto;
import br.com.ltjAdm.dao.FiltroPesquisa;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Junior
 */
public class ConsultaCategoriaProduto extends HttpServlet {

    FiltroPesquisa filtra = new FiltroPesquisa();
    ArrayList valores = new ArrayList();
    ArrayList coluna = new ArrayList();
    String comSqlListCateProduto;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession sessao = request.getSession();

        if (sessao.getAttribute("nomeCompleto") == null) {

            response.sendRedirect("index.jsp");

        } else {

            try {

                String innerJoin = "categoria_produto";

                if (request.getParameter("filtroCodigoCatProduto") == null) {
                    valores.add("N");
                    coluna.add("bloq_categoria");
                    comSqlListCateProduto = filtra.Filtrar(innerJoin, "1","desc", valores, coluna) + " limit 50";

                } else {

                    if (!request.getParameter("filtroCodigoCatProduto").isEmpty()) {
                        coluna.add("id_categoria");
                        valores.add(request.getParameter("filtroCodigoCatProduto"));
                    }

                    if (!request.getParameter("filtroNomeCatProduto").isEmpty()) {
                        coluna.add("nome_categoria");
                        valores.add(request.getParameter("filtroNomeCatProduto"));
                    }
                    
                    if (!request.getParameter("filtroBloqueadoCatProduto").isEmpty()) {
                        coluna.add("bloq_categoria");
                        valores.add(request.getParameter("filtroBloqueadoCatProduto"));
                    }

                    comSqlListCateProduto = filtra.Filtrar(innerJoin,request.getParameter("filtroOrdenarCatProduto"),
                            request.getParameter("filtroOrdemCatProduto"), valores, coluna) + " limit 50";
                }

                request.setAttribute("listaCategoriaProduto", ConsuCategoriaProduto.ListaCategoriaProduto(comSqlListCateProduto));

//--------------------------------------------------------------------Dados topo-------------------------------------------------------------------------------------------------------------------
                request.setAttribute("modulo", "Categoria de produto");
                request.setAttribute("usuarioLogado", sessao.getAttribute("nomeCompleto"));

//-----------------------------------------------------------Envia dados a pagina categoriaProdut.jsp----------------------------------------------------------------------------------------------
                request.getRequestDispatcher("categoriaProduto.jsp").forward(request, response);

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
