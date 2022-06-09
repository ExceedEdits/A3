import java.sql.Connection;
import javax.swing.*;
import bd.WiringSystem;
import dao.AccountDAO;
import model.Account;

public class App {
    public static void main(String[] args) throws Exception {
        JOptionPane.showMessageDialog(null, "Bem-Vindo, estamos te conectando...");
        Connection connection = WiringSystem.getConnection();

        if (connection == null){
            JOptionPane.showMessageDialog(null, "Erro de conexão.");
            return;
        }
        JOptionPane.showMessageDialog(null, "Conectado!");

        connection.close();

        AccountDAO dao = new AccountDAO();
        int check = JOptionPane.showConfirmDialog(null, 
        "Já possui uma conta?", "Login", 
        JOptionPane.YES_NO_OPTION);
        if (check == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Cadastre sua conta.");
            String nickname = JOptionPane.showInputDialog(null, "Insira seu login:");
            String locker = JOptionPane.showInputDialog(null, "Insira sua senha:");
            Account conta = new Account(nickname, locker);
            
            if(dao.insertAccount(conta)){
                JOptionPane.showMessageDialog(null, "Conta Cadastrada!");
            } else{
                JOptionPane.showMessageDialog(null,"Erro ao Cadastrar!");
            }
        }
        
        JOptionPane.showMessageDialog(null, dao.listAccount());
        
        int ii = dao.listLogin();
        
        Object[] options = { "Entrar", "Deletar" };
        int answer = JOptionPane.showOptionDialog(null, "O que você deseja fazer?", "Conta Selecionada",
        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        if (answer == JOptionPane.NO_OPTION) {
            if(dao.deleteAccount(ii)){
                JOptionPane.showMessageDialog(null, "Conta Apagada!");
            }else{
                JOptionPane.showMessageDialog(null, "Erro ao Deletar!");
            }
            dao.close();
        } else{
            dao.close();
            JOptionPane.showMessageDialog(null, "Carregando seus dados...");
        }
    }
}
