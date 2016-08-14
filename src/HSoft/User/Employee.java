/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HSoft.User;

/**
 *
 * @author Gina
 */
public class Employee {
    
    public Employee () {
        username = "";
        password = "";
        email = "";
        firstname = "";
        lastname = "";
        dateRegistered = "";
        dateLogin = "";
        age = 0;
        gender = "";
        hired = false;
        timeRegistered = "";
        timeLogin = "";
    }
    
    public String username;
    public String password;
    public String email;
    public String firstname;
    public String lastname;
    public String address;
    public String dateRegistered;
    public String dateLogin;
    public String timeRegistered;
    public String timeLogin;
    public int age;
    public String gender;
    public boolean hired;
}
