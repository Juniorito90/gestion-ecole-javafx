package application.ndiaya;

import application.ndiaya.User;

public interface IUser {

    public boolean add(User user);
    public boolean add(String nom, String prenom, String telephone, String email, String login, String password);

    public boolean login(User user);
    public boolean login(String login, String password);
}
