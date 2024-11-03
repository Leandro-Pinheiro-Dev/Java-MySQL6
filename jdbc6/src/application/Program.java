package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {

    public static void main(String[] args) {

        // INICIALIZAÇÃO DA CONEXÃO E DO STATEMENT
        Connection conn = null;
        Statement st = null;

        try {
            // OBTÉM A CONEXÃO COM O BANCO DE DADOS
            conn = DB.getConnection();
            
            // DESATIVA O AUTO COMMIT PARA INICIAR UMA TRANSAÇÃO MANUAL
            conn.setAutoCommit(false);

            // CRIA UM STATEMENT PARA EXECUTAR COMANDOS SQL
            st = conn.createStatement();
            
            // PRIMEIRA ATUALIZAÇÃO: AUMENTA O SALÁRIO BASE DOS VENDEDORES DO DEPARTAMENTO 1
            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

            // SIMULAÇÃO DE ERRO PARA TESTE (DESCOMENTE AS LINHAS ABAIXO PARA TESTAR)
            //int x = 1;
            //if (x < 2) { 
            //    throw new SQLException("Fake error");
            //}

            // SEGUNDA ATUALIZAÇÃO: AUMENTA O SALÁRIO BASE DOS VENDEDORES DO DEPARTAMENTO 2
            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

            // CONFIRMA A TRANSAÇÃO SE TODAS AS ATUALIZAÇÕES FOREM BEM-SUCEDIDAS
            conn.commit();

            // EXIBE O NÚMERO DE LINHAS AFETADAS POR CADA ATUALIZAÇÃO
            System.out.println("rows1 = " + rows1);
            System.out.println("rows2 = " + rows2);
        } 
        catch (SQLException e) {
            try {
                // EM CASO DE ERRO, REALIZA O ROLLBACK DA TRANSAÇÃO
                conn.rollback();
                throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
            } 
            catch (SQLException e1) {
                throw new DbException("Error trying to rollback! Caused by: " + e1.getMessage());
            }
        } 
        finally {
            // FINALIZA O STATEMENT E A CONEXÃO
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
