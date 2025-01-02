package in.VijayIt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.VijayIt.entity.EnquiryEntity;

public interface EnquiryRepo 
      extends JpaRepository<EnquiryEntity, Integer>{

}
