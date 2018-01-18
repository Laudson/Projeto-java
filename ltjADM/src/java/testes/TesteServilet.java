package testes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import javax.servlet.ServletContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Junior
 */
public class TesteServilet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            Modelo modelo = new Modelo();

            String endereco = request.getParameter("arquivo");
            ServletContext context = getServletContext();
            String enderecoCompleto = context.getRealPath(endereco);
            out.println(enderecoCompleto);
//            BufferedReader buff = new BufferedReader(new FileReader(enderecoCompleto));
//            String str;
//            while ((str = buff.readLine()) != null) {
//                System.out.println(str); //apenas para você saber o que tem no seu arquivo
//                modelo.trataArquivo(str);
//            }
//            buff.close();
        } finally {
            out.close();
        }
    }
}
