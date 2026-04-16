package model;

import java.util.ArrayList;

public class Utente {
    private String login;
    private String password;
    private ArrayList<Bacheca> bacheca;

    public Utente(String login, String password) {
        this.login = login;
        this.password = password;
        bacheca = new ArrayList<>();
        bacheca.add(new Bacheca("Universita",""));
        bacheca.add(new Bacheca("Tempo LIbero",""));
        bacheca.add(new Bacheca("Lavoro",""));
    }

    public boolean login(String login, String password) {
        return ( login.equals(this.login) && password.equals(this.password));
    }
}
