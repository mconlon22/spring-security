package com.bezkoder.springjwt.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User {
	
	public long LOCK_DURATION = 1000*60*20;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;



	@OneToMany(mappedBy="user")
     private Set<Card> cards;
	
	 @ManyToMany(cascade = CascadeType.ALL)

	 @JoinTable(
		name = "user_reservations", 
		joinColumns = @JoinColumn(name = "user_id"), 
		inverseJoinColumns = @JoinColumn(name = "flight_id"))
		Set<Flight> reservations;

    @Column(name = "phone")
    private String phone;
	
	
	@Column(name = "failed_attempts")
	private int failedAttempts;

	@Column(name = "locked")
	private boolean locked;

	@Column(name = "lock_time")
	private Date lockTime;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public User() {
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.failedAttempts =0;
		this.locked = false;
		this.lockTime = null;
	}
	public Set<Flight> getReservations() {
        return reservations;
    }
 public void addReservation(Flight f) {
       reservations.add(f);
    }
	public void removeReservation(Flight f) {
        reservations.remove(f);
     }
    public void setReservations(Set<Flight> reservations) {
        this.reservations = reservations;
    }
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public int getFailedAttempts() {
		return failedAttempts;
	}

	public void setFailedAttempts(int attempts) {
		this.failedAttempts = attempts;
	}

	public boolean getLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date time) {
		this.lockTime = time;
	}

}
