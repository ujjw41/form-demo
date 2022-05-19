package form.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "Name is mandatory")
	String name;

	@Size(min = 4, max = 15, message = "digits must be more than 3 and less than 15")
	String mobile;

	@Size(min = 6)
	String password;

	@Email(message = "Invalid Email")
	@Column(unique = true, length = 50)
	String email;

	String img_link;


}
