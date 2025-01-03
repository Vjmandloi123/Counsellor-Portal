package in.VijayIt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.VijayIt.entity.EnquiryEntity;

public interface EnquiryRepo 
      extends JpaRepository<EnquiryEntity, Integer>{

	public List<EnquiryEntity>findByCounsellorCounsellorId(Integer counsellorId);
	
}
