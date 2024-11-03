package db;

// CLASSE DbException QUE HERDA RuntimeException PARA GERAR EXCEÇÕES PERSONALIZADAS
public class DbException extends RuntimeException {
    
    private static final long serialVersionUID = 1L; // SERIALIZAÇÃO PARA MANIPULAÇÃO DE OBJETOS EM ARQUIVOS

    // CONSTRUTOR QUE ACEITA UMA MENSAGEM PERSONALIZADA DE ERRO
    public DbException(String msg) {
        super(msg); // CHAMA O CONSTRUTOR DA SUPERCLASSE RuntimeException COM A MENSAGEM
    }
}
