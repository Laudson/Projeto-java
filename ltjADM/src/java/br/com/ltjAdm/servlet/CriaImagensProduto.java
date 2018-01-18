package br.com.ltjAdm.servlet;

import br.com.ltjAdm.dao.ExecComandoSql;
import br.com.ltjAdm.upload.MoveImagem;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 *
 * @author Junior
 */
public class CriaImagensProduto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        boolean isMultPart = ServletFileUpload.isMultipartContent(request);
        RequestContext context = new ServletRequestContext(request);

        HttpSession sessao = request.getSession();

        if (sessao.getAttribute("nomeCompleto") == null) {

            response.sendRedirect("index.jsp");

        } else {

            if (isMultPart) {

                FileItemFactory factory = new DiskFileItemFactory();

                ServletFileUpload upload = new ServletFileUpload(factory);

                try {

                    ArrayList<String> mensagensUpload = new ArrayList();
                    List items = upload.parseRequest(context);
                    Iterator iter = items.iterator();
                    String resultadoUpload;
                    String formulario = "";

                    while (iter.hasNext()) {

                        FileItem item = (FileItem) iter.next();

                        if (item.isFormField()) {

                            formulario = item.getString();

                        } else {

                            String path = getServletContext().getRealPath("/");
                            resultadoUpload = MoveImagem.processarArquivo(path, formulario, item);
                            if (resultadoUpload.contains("concluido com sucesso")) {
                                String comandoSQL = "insert into imagem_produto(cod_prod, nome_album, nome_imagem,imagem_principal)"
                                        + " values ('"
                                        + formulario.trim() + "','"
                                        + formulario.trim() + "','"
                                        + item.getName().trim() + "','N')";
                                ExecComandoSql.execComando(comandoSQL);
                            }
                            mensagensUpload.add(resultadoUpload);
                        }
                    }

//                    int contadorMenssagem = 0;
//                    while (!mensagensUpload.isEmpty()) {
//                        out.println(mensagensUpload.get(contadorMenssagem) + "<br>");
//                        contadorMenssagem += 1;
//                    }
                    
                    request.setAttribute("resultadoUpload", mensagensUpload);
                    request.setAttribute("msnImagensImpostadas", "sim");
                    request.getRequestDispatcher("ConsultaImagemProduto?id=" + formulario).forward(request, response);

                } catch (SQLException erroSQL) {
                    out.println(erroSQL);
                } catch (FileUploadException erroFUE) {
                    out.println(erroFUE.getMessage());
                } catch (Exception ex) {
                    ex.getMessage();
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
