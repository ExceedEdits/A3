package model;

public class Account {
    private int id;
    private String login, password;

    public Account(int id, String login, String password){
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public Account(String login, String password){
        this.login = login;
        this.password = password;
    }

    public Account(int id, String login){
        this.id = id;
        this.login = login;
    }

    public Account(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return "Conta " + id + ": login = " + login;
    }
}
