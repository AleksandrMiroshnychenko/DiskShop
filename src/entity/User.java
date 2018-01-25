package entity;

import java.sql.Date;

import dao.UserDAO;


public class User {
  
    private int userId;
    private String nickname;
    private String password;
    private String email;
    private Byte role = 0;
    Date date = new Date(System.currentTimeMillis());  
    
    public User(String nickname, String password, String eMail) {
    	
        this.nickname = nickname;
        this.password = password;
        this.email = eMail;
    }
 
    public User() {
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getNickname() {
        return nickname;
    }
 
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
 
    public Integer getUserId() {
        return userId;
    }
 
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegDate() {
		return date;
	}
	
	public void setRegDate(Date date) {
		this.date = date;
	}


	public Byte getRole() {
		return role;
	}

	public void setRole(Byte role) {
		this.role = role;
	}

	public Boolean appendToDB(){
		UserDAO hi = new UserDAO();
		hi.addUser(this);
		return !hi.check;
	}
	
	@Override
	public String toString(){
		return "User Nickname: " + this.getNickname() + " password: " + this.getPassword() + " E-Mail: " + this.getEmail() + " Date Of Registration: " + this.getRegDate();
	}
	
}
