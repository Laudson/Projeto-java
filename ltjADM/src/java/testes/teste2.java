package testes;

import br.com.ltjAdm.dao.ConsuImagemProduto;
import br.com.ltjAdm.model.BeanImagemProduto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Junior
 */
@WebServlet(name = "teste2", urlPatterns = {"/teste2"})
public class teste2 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String caminhoImagem = "IMAGENS-SISTEMA/sem-imagem.jpg";
        try {
            List<BeanImagemProduto> imagemPrincipal = new ArrayList();
            String consultaSQL = "select * from imagem_produto a inner join produto_principal b \n"
                    + "on a.cod_prod = b.id_produto\n"
                    + "where imagem_principal = 'S' and cod_prod = 1";
            imagemPrincipal = ConsuImagemProduto.ListaImagemProduto(consultaSQL);
            if (!imagemPrincipal.isEmpty()) {
                caminhoImagem = "IMAGENS-SISTEMA/" + imagemPrincipal.get(0).getNome_album() + "/" + imagemPrincipal.get(0).getNome_imagem() + "";
            }
            out.println(caminhoImagem);
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
            Logger.getLogger(teste2.class.getName()).log(Level.SEVERE, null, ex);
        }
        PrintWriter out = response.getWriter();

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(teste2.class.getName()).log(Level.SEVERE, null, ex);
        }
        PrintWriter out = response.getWriter();

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
