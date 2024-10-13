package models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Table(name="users")
@NamedQuery(name="User.findAll", query ="SELECT u from User u")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Username")
	private String username;
	
	@Column(name="Active")
	private boolean active;
	
	@Column(name="Admin")
	private boolean admin;
	
	@Column(name="Email")
	@Email(message="Nhập đúng định dạng email")
	@NotEmpty(message="Hãy nhập email")
	private String email;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	@Column(name="Phone")
	@Pattern(regexp = "^\\{8,10}$", message = " Số điện thoại từ 8-10 số")
	@NotEmpty(message="Hãy nhập số điện thoại")
	private String phone;
	
	public User(String username, boolean active, boolean admin,
			@Email(message = "Nhập đúng định dạng email") @NotEmpty(message = "Hãy nhập email") String email,
			@Pattern(regexp = "^\\{8,10}$", message = " Số điện thoại từ 8-10 số") @NotEmpty(message = "Hãy nhập số điện thoại") String phone,
			String fullname, String password, String images) {
		super();
		this.username = username;
		this.active = active;
		this.admin = admin;
		this.email = email;
		this.phone = phone;
		this.fullname = fullname;
		this.password = password;
		this.images = images;
	}

	@Column(name="Fullname", columnDefinition = "nvarchar(255)")
	private String fullname;
	
	@Column(name="Password", columnDefinition = "nvarchar(255)")
	private String password;
	
	@Column(name="Images", columnDefinition = "nvarchar(500)")
	private String images;
	

}
