package org.users.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "users")
@Data
public class User {
	@Id
	@Column(name = "id", length = 100)
	private String id;
	
	@Column(name = "name", length = 100)
	private String name;
}
