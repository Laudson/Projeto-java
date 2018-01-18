
package br.com.ltjAdm.upload;

import java.io.File;

/**
 *
 * @author Junior
 */
public class DeletaArquivo {

    public static boolean deleta(File dir) {
        if (dir.isDirectory()) {
            String[] conteudo = dir.list();
            for (String conteudo1 : conteudo) {
                boolean success = deleta(new File(dir, conteudo1));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
