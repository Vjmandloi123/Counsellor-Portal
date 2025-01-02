package in.VijayIt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name="enquiries_tbl")
@Setter
@Getter
public class EnquiryEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer counsellorId;
	private String stuName;
	private String stuPhone;;
	private String classMode;
	private String course;
	private String enqStatus;
	
    @ManyToOne
    @JoinColumn(name="counselloer_id")
	private CounsellorEntity  counselloer;
	
}
