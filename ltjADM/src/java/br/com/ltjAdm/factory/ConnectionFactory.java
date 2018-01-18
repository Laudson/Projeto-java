package br.com.ltjAdm.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Junior
 */
public class ConnectionFactory {

    public static String status = "Não conectou...";

//Método Construtor da Classe//
    public ConnectionFactory() {

    }

//Método de Conexão//
    public static java.sql.Connection getConexaoMySQL() throws SQLException {

        Connection connection = null;          //Atributo do tipo Connection

        try {

// Carregando o JDBC Driver padrão
            String driverName = "com.mysql.jdbc.Driver";

            Class.forName(driverName);

// Configurando a conexão com um banco de dados//
            String serverName = "ltj.mysql.uhserver.com";    //Caminho do servidor do BD

            String mydatabase = "ltj";        //Nome do banco de dados

            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;

            String username = "laudson";        //Nome do usuário de seu BD      

            String password = "l@ujur10407915";      //Senha de acesso

            connection = DriverManager.getConnection(url, username, password);

            //Testa conexão//  
            if (connection != null) {

                status = ("STATUS--->Conectado com sucesso!");

            } else {

                status = ("STATUS--->Não foi possivel realizar conexão");

            }

            return connection;

        } catch (ClassNotFoundException e) {  //Driver não encontrado

            System.out.println("O driver expecificado nao foi encontrado.");

            return null;

        } catch (SQLException e) {

//Não conseguindo se conectar ao banco
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");

            return null;

        }

    }

    //Método que retorna o status da conexão//
    public static String statusConection() {

        return status;

    }

    //Método que fecha conexão//
    public static boolean FecharConexao() throws SQLException {

        ConnectionFactory.getConexaoMySQL().close();
        return true;

    }

    //Método que reinicia conexão//
    public static java.sql.Connection ReiniciarConexao() throws SQLException {

        FecharConexao();

        return ConnectionFactory.getConexaoMySQL();

    }

}
