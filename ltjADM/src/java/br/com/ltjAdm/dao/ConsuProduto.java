package br.com.ltjAdm.dao;

import br.com.ltjAdm.factory.ConnectionFactory;
import br.com.ltjAdm.model.BeanImagemProduto;
import br.com.ltjAdm.model.BeanProduto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Junior
 */
public class ConsuProduto {

//------------------------------------Metodo que executa a opreção no banco de dados--------------------------------------------
    private static ResultSet resultSet(String comandoSql) throws SQLException {

        PreparedStatement pstmt;
        ResultSet rs;
        pstmt = ConnectionFactory.getConexaoMySQL().prepareStatement(comandoSql);
        rs = pstmt.executeQuery();
        ConnectionFactory.FecharConexao();
        return rs;
    }

//-------------------------Metodo que faz a listagem de dados da tabela produtos--------------------------------------------
    public static List<BeanProduto> ListaProduto(String comandoSql) throws SQLException {

        ResultSet lista = resultSet(comandoSql);
        List<BeanProduto> listaProduto = new ArrayList<BeanProduto>();

        while (lista.next()) {

            BeanProduto bean = new BeanProduto();
            bean.setNome_produto(lista.getString("Nome_produto"));
            bean.setId_produto(lista.getString("Id_produto"));
            bean.setNome_categoria(lista.getString("Nome_categoria"));
            bean.setId_categoria(lista.getString("Id_categoria"));
            bean.setNome_grupo(lista.getString("Nome_grupo"));
            bean.setId_grupo(lista.getString("Id_grupo"));

//------------------------------Seta valor caminho da imagem------------------------------------------------------
            List<BeanImagemProduto> imagemPrincipal = new ArrayList();
            String caminhoImagem = "IMAGENS-SISTEMA/sem-imagem.jpg";

            String consultaSQL = "select * from imagem_produto a inner join produto_principal b \n"
                    + "on a.cod_prod = b.id_produto\n"
                    + "where imagem_principal = 'S' and cod_prod = " + lista.getString("Id_produto");
            imagemPrincipal = ConsuImagemProduto.ListaImagemProduto(consultaSQL);
            if (!imagemPrincipal.isEmpty()) {
                caminhoImagem = "IMAGENS-PRODUTOS/" + imagemPrincipal.get(0).getNome_album() + "/" + imagemPrincipal.get(0).getNome_imagem() + "";
            }

            bean.setCaminho_imagem(caminhoImagem);

//----------------------------Adiciona valores a listaProduto------------------------------------------------
            listaProduto.add(bean);
            
            imagemPrincipal.clear();
        }
        return listaProduto;
    }
}
