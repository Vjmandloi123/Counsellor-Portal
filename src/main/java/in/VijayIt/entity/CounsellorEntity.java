package in.VijayIt.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="counselloer_tbl")
@Setter
@Getter
public class CounsellorEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer counsellorId;
	private String name;
	private String email;
	private String pwd;
	private String phno;
	
	@OneToMany
	private List<EnquiryEntity> enquiries;

}
