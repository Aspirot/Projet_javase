package bll.model;

public class Elector {
    private static int AUTO_INCREMENT_ID = 0;

    private int id;
    private String login;
    private String password;
    private int weight;
    private String email;
    private int voterId;

    public Elector(String login, String password, int weight, String email, int voterId) {
        this.id = AUTO_INCREMENT_ID++;
        this.login = login;
        this.password = password;
        this.weight = weight;
        this.email = email;
        this.voterId = voterId;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getVoterId() {
        return voterId;
    }

    public void setVoterId(int voterId) {
        this.voterId = voterId;
    }
}
