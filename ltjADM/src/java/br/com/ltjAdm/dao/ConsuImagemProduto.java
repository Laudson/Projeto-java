
package br.com.ltjAdm.dao;

import br.com.ltjAdm.factory.ConnectionFactory;
import br.com.ltjAdm.model.BeanImagemProduto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Junior
 */
public class ConsuImagemProduto {
    
    //------------------------------------Metodo que executa a opreção no banco de dados--------------------------------------------
    
    private static ResultSet resultSet(String comandoSql) throws SQLException {
        
        PreparedStatement pstmt;
        ResultSet rs;
        pstmt = ConnectionFactory.getConexaoMySQL().prepareStatement(comandoSql);
        rs = pstmt.executeQuery();
        ConnectionFactory.FecharConexao();
        return rs;
    }

//-------------------------Metodo que faz a listagem de dados da tabela imagem de produtos--------------------------------------------
    
    public static List<BeanImagemProduto> ListaImagemProduto(String comandoSql) throws SQLException {

        List<BeanImagemProduto> listaImagemProduto = new ArrayList<BeanImagemProduto>();
        ResultSet lista = resultSet(comandoSql);

        while (lista.next()) {

            BeanImagemProduto bean = new BeanImagemProduto();
            bean.setId_imagem(lista.getString("Id_imagem"));
            bean.setImagem_principal(lista.getString("Imagem_principal"));
            bean.setNome_album(lista.getString("Nome_album"));
            bean.setNome_imagem(lista.getString("Nome_imagem"));
            bean.setNome_produto(lista.getString("Nome_produto"));
            bean.setCod_prod(lista.getString("Cod_prod"));
            listaImagemProduto.add(bean);
        }

        return listaImagemProduto;
    }
    
}
