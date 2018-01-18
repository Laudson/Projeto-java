package br.com.ltjAdm.dao;

import br.com.ltjAdm.factory.ConnectionFactory;
import br.com.ltjAdm.model.BeanUsuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author laudson
 */
public class ConsuUsuario {

    //------------------------------------Metodo que executa a opreção no banco de dados--------------------------------------------
    private static ResultSet resultSet(String comandoSql) throws SQLException {

        PreparedStatement pstmt;
        ResultSet rs;
        pstmt = ConnectionFactory.getConexaoMySQL().prepareStatement(comandoSql);
        rs = pstmt.executeQuery();
        ConnectionFactory.FecharConexao();
        return rs;
    }

//-------------------------Metodo que faz a listagem de dados da tabela usuario_usuario--------------------------------------------
    public static List<BeanUsuario> ListaUsuario(String comandoSql) throws SQLException {

        List<BeanUsuario> listaUsuario = new ArrayList<BeanUsuario>();
        ResultSet lista = resultSet(comandoSql);

        while (lista.next()) {

            BeanUsuario bean = new BeanUsuario();
            bean.setId_usuario(lista.getString("id_usuario"));
            bean.setNome_completo(lista.getString("nome_completo"));
            bean.setNome_usuario(lista.getString("nome_usuario"));
            bean.setPermissao(lista.getString("permissao"));
            bean.setSenha(lista.getString("senha"));
            listaUsuario.add(bean);
        }
        return listaUsuario;
    }

}
