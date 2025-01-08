public class User {
    private String name;
    private String surname;
    private String login;
    private String password;
    private Role role;

    public User(String name, String surname, String login, String password, Role role){
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public Role getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
