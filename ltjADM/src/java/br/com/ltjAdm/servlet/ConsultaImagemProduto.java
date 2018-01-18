package br.com.ltjAdm.servlet;

import br.com.ltjAdm.dao.ConsuImagemProduto;
import br.com.ltjAdm.dao.ConsuProduto;
import br.com.ltjAdm.model.BeanImagemProduto;

import java.io.IOException;
import java.io.PrintWriter;

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
 * @author Junior
 */
public class ConsultaImagemProduto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession sessao = request.getSession();

        if (sessao.getAttribute("nomeCompleto") == null) {

            response.sendRedirect("index.jsp");

        } else {

            try {

                if (request.getParameter("id") == null) {
                    response.sendRedirect("index.jsp");
                } else {
//-------------------------------------------------------------Gera dados do produto----------------------------------------------------------------------
                    String dadosProduto = "select * from produto_principal a inner join categoria_produto b \n"
                            + "on a.cod_cate_prod = b.id_categoria inner join grupo_produto c\n"
                            + "on a.cod_grp_prod = c.id_grupo where id_produto = " + request.getParameter("id");
                    request.setAttribute("listaDadosProduto", ConsuProduto.ListaProduto(dadosProduto));

//--------------------------------------------------------------Gera imagens do produto-------------------------------------------------------------------                
                    String comSqlListImagemProduto = "select * from produto_principal a inner join imagem_produto b "
                            + "on a.id_produto = b.cod_prod where a.id_produto = " + request.getParameter("id");
                    request.setAttribute("listaImagemProduto", ConsuImagemProduto.ListaImagemProduto(comSqlListImagemProduto));

//------------------------------------------------------Captura imagem principal/Nome album----------------------------------------------------------------        
                    List<BeanImagemProduto> dadosImagensProduto = ConsuImagemProduto.ListaImagemProduto(comSqlListImagemProduto);

                    String ImagemPrincipal = "IMAGENS-SISTEMA/sem-imagem.jpg";
                    String nomeAlbum = "";

                    if (!dadosImagensProduto.isEmpty()) {

                        int contador = 0;

                        while (dadosImagensProduto.size() != contador) {
                            if (dadosImagensProduto.get(contador).getImagem_principal().equals("S")) {
                                ImagemPrincipal = "IMAGENS-PRODUTOS/" + dadosImagensProduto.get(contador).getNome_album() + "/"
                                        + dadosImagensProduto.get(contador).getNome_imagem() + "";
                            }
                            contador += 1;
                        }
                        nomeAlbum = dadosImagensProduto.get(0).getNome_album();
                    }

                    request.setAttribute("ImagemPrincipal", ImagemPrincipal);
                    request.setAttribute("nomeAlbum", nomeAlbum);
                    request.setAttribute("modulo", "Imagens produto");
                    request.setAttribute("usuarioLogado", sessao.getAttribute("nomeCompleto"));

//--------------------------------------------------------------Envia dados para pagina imagensPricipal---------------------------------------------------
                    request.setAttribute("codProd", request.getParameter("id"));
                    request.getRequestDispatcher("imagensProduto.jsp").forward(request, response);

                    dadosImagensProduto.clear();
                }

            } catch (SQLException erroSQL) {
                out.println(erroSQL);
                response.sendRedirect("index.jsp");
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
            Logger.getLogger(ConsultaImagemProduto.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ConsultaImagemProduto.class.getName()).log(Level.SEVERE, null, ex);
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
