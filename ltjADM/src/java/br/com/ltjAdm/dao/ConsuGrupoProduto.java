package br.com.ltjAdm.dao;

import br.com.ltjAdm.factory.ConnectionFactory;
import br.com.ltjAdm.model.BeanGrupoProduto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Junior
 */
public class ConsuGrupoProduto {

//------------------------------------Metodo que executa a opreção no banco de dados--------------------------------------------
    
    private static ResultSet resultSet(String comandoSql) throws SQLException {
        
        PreparedStatement pstmt;
        ResultSet rs;
        pstmt = ConnectionFactory.getConexaoMySQL().prepareStatement(comandoSql);
        rs = pstmt.executeQuery();
        ConnectionFactory.FecharConexao();
        return rs;
    }

//-------------------------Metodo que faz a listagem de dados da tabela grupo de produtos--------------------------------------------
    
    public static List<BeanGrupoProduto> ListaGrpProduto(String comandoSql) throws SQLException {

        List<BeanGrupoProduto> listaGrpProduto = new ArrayList<BeanGrupoProduto>();
        ResultSet lista = resultSet(comandoSql);

        while (lista.next()) {

            BeanGrupoProduto bean = new BeanGrupoProduto();
            bean.setId_grupo(lista.getString("Id_grupo"));
            bean.setNome_grupo(lista.getString("Nome_grupo"));            
            listaGrpProduto.add(bean);
        }

        return listaGrpProduto;
    }
}
