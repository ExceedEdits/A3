package dao;

import bd.WiringSystem;
import model.Account;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {
    Connection connection;

    public AccountDAO() throws SQLException{
        connection = WiringSystem.getConnection();
    }

    public boolean insertAccount(Account user) throws SQLException {
        String sql = "INSERT INTO account (login, password) VALUES (?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());

        int rowsInserted = statement.executeUpdate();

        return (rowsInserted > 0);
    }

    public boolean deleteAccount(int id) throws SQLException {
        String sql = "DELETE FROM account WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        int rowsDeleted = statement.executeUpdate();

        return (rowsDeleted > 0);
    }

    public Account findAccount(int id) throws SQLException{
        String sql = "SELECT * FROM account WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        
        ResultSet result = statement.executeQuery();
        
        if(result.next()){
            Account account = new Account(
                result.getInt(1),
                result.getString(2),
                result.getString(3)
            );
            return account;
        }
        return null;
    }
        
    public List<String> listAccount() throws SQLException{
        String sql = "SELECT * FROM account";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        List<String> idInterface = new ArrayList<>();
        

        while (result.next()) {
            int i = result.getInt("id");
            String str = result.getString("login");
            idInterface.add("\n Conta " + i + ": login: " + str);
        }

        return idInterface;
    }

    public int listLogin() throws SQLException{
        String sql = "SELECT * FROM account";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        List<Integer> idInterface = new ArrayList<>();
        
        while (result.next()) {
            int i = result.getInt("id");
            idInterface.add(i);
        }
        
        Integer[] array = idInterface.stream().toArray(Integer[]::new);

        Integer ii = (Integer) JOptionPane.showInputDialog(null,
            "Selecione a conta", "Troca de Conta", JOptionPane.QUESTION_MESSAGE,
            null, array, array[1]);

        return ii;
    }


    /// Tentativa falha de um método de verificação

    /* public boolean checkLogin(int id) throws SQLException{
        String sql = "SELECT * FROM account WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        String nickname = JOptionPane.showInputDialog(null, "Insira sua senha:");
        ResultSet result = statement.executeQuery();
        if(result.next()){
            Account account = new Account(
                result.getInt(1),
                result.getString(2),
                result.getString(3)
            );
            System.out.println(nickname);
            System.out.println(account.getPassword());
            String password = account.getPassword();

            if(nickname == password){
                JOptionPane.showMessageDialog(null, "Senha Correta!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Senha Incorreta!");
            }
        }
        return false;
    } */
    

    public void close() throws SQLException {
        connection.close();
    }
}
