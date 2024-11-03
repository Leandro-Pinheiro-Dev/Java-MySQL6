package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

    // VARIÁVEL ESTÁTICA PARA MANTER UMA ÚNICA CONEXÃO ATIVA
    private static Connection conn = null;

    // MÉTODO PARA OBTER A CONEXÃO COM O BANCO DE DADOS
    public static Connection getConnection() {
        if (conn == null) {
            try {
                Properties props = loadProperties(); // CARREGAR PROPRIEDADES DO BANCO
                String url = props.getProperty("dburl"); // OBTÉM A URL DO BANCO
                conn = DriverManager.getConnection(url, props); // ESTABELECE A CONEXÃO
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage()); // LANÇA EXCEÇÃO PERSONALIZADA EM CASO DE ERRO
            }
        }
        return conn; // RETORNA A CONEXÃO
    }

    // MÉTODO PARA FECHAR A CONEXÃO COM O BANCO DE DADOS
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close(); // FECHA A CONEXÃO
            } catch (SQLException e) {
                throw new DbException(e.getMessage()); // LANÇA EXCEÇÃO PERSONALIZADA SE OCORRER ERRO
            }
        }
    }

    // MÉTODO PARA CARREGAR AS CONFIGURAÇÕES DO BANCO DE DADOS DO ARQUIVO db.properties
    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties(); // CRIA OBJETO PROPERTIES
            props.load(fs); // CARREGA AS PROPRIEDADES DO ARQUIVO
            return props; // RETORNA AS PROPRIEDADES CARREGADAS
        }
        catch (IOException e) {
            throw new DbException(e.getMessage()); // LANÇA EXCEÇÃO PERSONALIZADA EM CASO DE ERRO DE I/O
        }
    }

    // MÉTODO PARA FECHAR O OBJETO STATEMENT
    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close(); // FECHA O STATEMENT
            } catch (SQLException e) {
                throw new DbException(e.getMessage()); // LANÇA EXCEÇÃO PERSONALIZADA SE OCORRER ERRO
            }
        }
    }

    // MÉTODO PARA FECHAR O OBJETO RESULTSET
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close(); // FECHA O RESULTSET
            } catch (SQLException e) {
                throw new DbException(e.getMessage()); // LANÇA EXCEÇÃO PERSONALIZADA SE OCORRER ERRO
            }
        }
    }
}
