package br.com.ltjAdm.dao;

import br.com.ltjAdm.factory.ConnectionFactory;
import br.com.ltjAdm.model.BeanCategoriaProduto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Junior
 */
public class ConsuCategoriaProduto {

//------------------------------------Metodo que executa a opreção no banco de dados--------------------------------------------
    
    private static ResultSet resultSet(String comandoSql) throws SQLException {
        
        PreparedStatement pstmt;
        ResultSet rs;
        pstmt = ConnectionFactory.getConexaoMySQL().prepareStatement(comandoSql);
        rs = pstmt.executeQuery();
        ConnectionFactory.FecharConexao();
        return rs;
    }

//-------------------------Metodo que faz a listagem de dados da tabela categoria de produtos--------------------------------------------
    
    public static List<BeanCategoriaProduto> ListaCategoriaProduto(String comandoSql) throws SQLException {

        List<BeanCategoriaProduto> listaGrpProduto = new ArrayList<BeanCategoriaProduto>();
        ResultSet lista = resultSet(comandoSql);

        while (lista.next()) {

            BeanCategoriaProduto bean = new BeanCategoriaProduto();
            bean.setId_categoria(lista.getString("Id_categoria"));
            bean.setNome_categoria(lista.getString("Nome_categoria"));            
            listaGrpProduto.add(bean);
        }

        return listaGrpProduto;
    }
}
