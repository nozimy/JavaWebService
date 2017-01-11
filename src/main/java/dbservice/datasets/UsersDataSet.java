package dbservice.datasets;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mk-orzu on 09.01.2017.
 */
@Entity
@Table(name="users")
public class UsersDataSet implements Serializable{// Serializable Important to Hibernate!
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="login", unique =true, updatable=false)
    private String login;



    @Column(name="password", updatable=false)
    private String password;

    //@Column(name="email", unique =true, updatable=false)
    //private String email;

    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet(long id, String login,String password) {
        this.setId(id);
        this.setLogin(login);
        this.setPassword(password);

    }

    public UsersDataSet(String login, String password) {
        this.setId(-1);
        this.setLogin(login);
        this.setPassword(password);
    }

    //@SuppressWarnings("UnusedDeclaration")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", login='" + login +'\''+ " password: "+password  +
                '}';
    }

}
