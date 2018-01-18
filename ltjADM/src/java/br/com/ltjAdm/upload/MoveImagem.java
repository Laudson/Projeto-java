package br.com.ltjAdm.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import java.util.Vector;

import org.apache.tomcat.util.http.fileupload.FileItem;

/**
 *
 * @author Junior
 */
public class MoveImagem {

    static String status;

    public static String processarArquivo(String path, String album, FileItem item) {
        try {
            if (!VerificaExtensao(item.getContentType())) {
                status = "A extensão do arquivo " + item.getName() + " é incompativel com o exigido ";
            } else {
                if (!VerificaTamanho(item.getSize())) {
                    status = "O tamanho do arquivo " + item.getName() + " é incompativel com o exigido";
                } else {
                    File f = new File(path + File.separator + "IMAGENS-PRODUTOS");
                    File fi = new File(f + File.separator + album);

                    if (!f.exists()) {
                        f.mkdir();
                        fi.mkdir();
                    } else {
                        fi.mkdir();
                    }

                    File savedFile = new File(fi + File.separator + item.getName());
                    if (savedFile.exists()) {
                        status = "A imagem " + item.getName() + " ja foi importada";
                    } else {
                        FileOutputStream fos = new FileOutputStream(savedFile);
                        InputStream is = item.getInputStream();
                        int x;
                        byte[] b = new byte[1024];
                        while ((x = is.read(b)) != -1) {
                            fos.write(b, 0, x);
                        }
                        fos.flush();
                        fos.close();
                        String caminho = fi + File.separator + item.getName();
                        Redimensiona.novoTamanho(caminho);
                        status = "A importação do arquivo " + item.getName() + " foi concluido com sucesso";
                    }
                }
            }

        } catch (Exception e) {
            status = e.getMessage();
        }
        return status;
    }

    private static boolean VerificaTamanho(long tamanho) {

        long tamanhoPadrao = 100000;
        boolean condicao = true;

        if (tamanho < tamanhoPadrao) {
            condicao = false;
        }
        return condicao;
    }

    private static boolean VerificaExtensao(String extensao) {

        Vector ext = new Vector();
        ext.add("image/jpg");
        ext.add("image/jpeg");
        ext.add("image/gif");
        ext.add("image/png");
        ext.add("image/bmp");
        ext.add("image/bitmap");
        ext.add("image/tiff");
        ext.add("image/raw");
        ext.add("image/svg");
        ext.add("image/webp");

        return ext.contains(extensao);
    }
}
