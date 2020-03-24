package jpa.test.db;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

@Entity
@Table(name = "citizen")
public class Citizen implements Serializable {

	private static final long serialVersionUID = 2612085042435964624L;
	
	@Column(name = "id", updatable = false, unique = true, nullable = false)
	@Id
//	@org.hibernate.annotations.Type(type = "pg-uuid")
	private UUID id;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
	protected Citizen() {
	}
	
	protected Citizen(UUID id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public static Citizen valueOf(UUID id, String firstName, String lastName) {
		Objects.requireNonNull(id);
		final String newFirstName = Objects.requireNonNull(firstName).trim();
		final String newLastName = Objects.requireNonNull(lastName).trim();
		return new Citizen(id,newFirstName,newLastName);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = Objects.requireNonNull(firstName).trim();
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = Objects.requireNonNull(lastName).trim();
	}

	public UUID getId() {
		return id;
	}
}
