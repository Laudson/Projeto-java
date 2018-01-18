package br.com.ltjAdm.servlet;

import br.com.ltjAdm.dao.ConsuCategoriaProduto;
import br.com.ltjAdm.dao.ConsuGrupoProduto;
import br.com.ltjAdm.dao.ConsuProduto;
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
public class ConsultaProduto extends HttpServlet {

    FiltroPesquisa filtra = new FiltroPesquisa();
    ArrayList valores = new ArrayList();
    ArrayList coluna = new ArrayList();

    String comSqlListProduto;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession sessao = request.getSession();

        if (sessao.getAttribute("nomeCompleto") == null) {

            response.sendRedirect("index.jsp");

        } else {

            try {

                String innerJoin = "produto_principal a inner join grupo_produto b \n"
                        + "on a.cod_grp_prod = b.id_grupo\n"
                        + "inner join categoria_produto c \n"
                        + "on a.cod_cate_prod = c.id_categoria";

                if (request.getParameter("filtroCodigoProduto") == null) {
                    valores.add("N");
                    coluna.add("bloq_produto");
                    comSqlListProduto = filtra.Filtrar(innerJoin, "1", "desc", valores, coluna) + " limit 50";

                } else {

                    if (!request.getParameter("filtroCodigoProduto").isEmpty()) {
                        coluna.add("id_produto");
                        valores.add(request.getParameter("filtroCodigoProduto"));
                    }

                    if (!request.getParameter("filtroNomeProduto").isEmpty()) {
                        coluna.add("nome_produto");
                        valores.add(request.getParameter("filtroNomeProduto"));
                    }

                    if (!request.getParameter("filtroGrupoProduto").isEmpty()) {
                        coluna.add("cod_grp_prod");
                        valores.add(request.getParameter("filtroGrupoProduto"));
                    }

                    if (!request.getParameter("filtroCategoriaProduto").isEmpty()) {
                        coluna.add("cod_cate_prod");
                        valores.add(request.getParameter("filtroCategoriaProduto"));
                    }
                    
                    if (!request.getParameter("filtroBloqueadoProduto").isEmpty()) {
                        coluna.add("bloq_produto");
                        valores.add(request.getParameter("filtroBloqueadoProduto"));
                    }
                    
                    comSqlListProduto = filtra.Filtrar(innerJoin, request.getParameter("filtroOrdenarProduto"),
                            request.getParameter("filtroOrdemProduto"), valores, coluna) + " limit 50";
                }

                request.setAttribute("listaProduto", ConsuProduto.ListaProduto(comSqlListProduto));

//-------------------------------------------------------------Dados do Drop down grupo de produto----------------------------------------------------------
                String comSqlListGrpProduto = "select * from grupo_produto where 1 order by 2 asc";
                request.setAttribute("listaGrpProduto", ConsuGrupoProduto.ListaGrpProduto(comSqlListGrpProduto));

//-------------------------------------------------------------Dados do Drop down categoria de produto------------------------------------------------------
                String comSqlListCateProduto = "select * from categoria_produto where 1 order by 2 asc";
                request.setAttribute("listaCateProduto", ConsuCategoriaProduto.ListaCategoriaProduto(comSqlListCateProduto));

//-------------------------------------------------------------------Dados cabaçalho------------------------------------------------------------------------
                request.setAttribute("modulo", "Produto");
                request.setAttribute("usuarioLogado", sessao.getAttribute("nomeCompleto"));

//---------------------------------------------Envia os dados para a pagina consultaProduto e limpa as listas-----------------------------------------------
                request.getRequestDispatcher("produto.jsp").forward(request, response);

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
            Logger.getLogger(ConsultaProduto.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ConsultaProduto.class.getName()).log(Level.SEVERE, null, ex);
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
