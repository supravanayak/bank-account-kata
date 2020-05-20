package fr.ing.interview.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customerId", nullable = false, updatable = false)
	private Long customerId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;

	@Column(name = "email", nullable = false, unique = true)
	private String email;
	private String phone;    
}
