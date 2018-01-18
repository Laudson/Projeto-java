
package br.com.ltjAdm.dao;

import br.com.ltjAdm.factory.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Junior
 */
public class ExecComandoSql {
    
    private static PreparedStatement pstmt = null;
    
    public static boolean execComando(String comandoSQL) throws SQLException {

        pstmt = ConnectionFactory.getConexaoMySQL().prepareStatement(comandoSQL);
        pstmt.execute();
        pstmt.close();

        ConnectionFactory.FecharConexao();
        return true;
    }
    
}
