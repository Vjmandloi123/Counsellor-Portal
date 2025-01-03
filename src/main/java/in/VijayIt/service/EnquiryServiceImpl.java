package in.VijayIt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.VijayIt.bindings.DashboardResponseDTO;
import in.VijayIt.bindings.EnqFilterDTO;
import in.VijayIt.bindings.EnquiryDTO;
import in.VijayIt.entity.CounsellorEntity;
import in.VijayIt.entity.EnquiryEntity;
import in.VijayIt.repo.CounsellorRepo;
import in.VijayIt.repo.EnquiryRepo;

     @Service
     public class EnquiryServiceImpl implements EnquiryService {
    
	@Autowired
	 private EnquiryRepo enquiryRepo;
	 
	@Autowired
	 private CounsellorRepo counsellorRepo;

     @Override
     public DashboardResponseDTO getDashboardInfo(Integer counsellorId) {
	
	       List<EnquiryEntity>enqsList=enquiryRepo.findByCounsellorCounsellorId(counsellorId);
	        
	       DashboardResponseDTO dto = new DashboardResponseDTO();
	      
	       //Open enquiry count
	       int openCnt =enqsList.stream()
	    		                .filter(enq -> enq.getEnqStatus().equals("OPEN"))
	    		                .collect(Collectors.toList())
	    		                .size();
	       
	       
	       int enrolledCnt = enqsList.stream()
	    		                     .filter(enq -> enq.getEnqStatus().equals("ENROLLED"))
	    		                     .collect(Collectors.toList())
	    		                     .size();
	       
	       int lostCnt = enqsList.stream()
	    		                 .filter(enq -> enq.getEnqStatus().equals("LOST"))
	    		                 .collect(Collectors.toList())
	    		                 .size();
	       
	       
	       
	       dto.setTotalEnqCnt(enqsList.size());
	       dto.setOpenEnqCnt(openCnt);
	       dto.setEnrolledEnqCnt(enrolledCnt);
	       dto.setLostEnqCnt(lostCnt);
	       
	      return dto;
	       
}

     @Override
     public boolean addEnquiry(EnquiryDTO enqDTO, Integer counsellorId) {
	
	EnquiryEntity entity = new EnquiryEntity();
	BeanUtils.copyProperties(enqDTO, entity);
	
    Optional<CounsellorEntity>byId= counsellorRepo.findById(counsellorId);
	if(byId.isPresent()) {
		CounsellorEntity counsellor=byId.get();
		entity.setCounselloer(counsellor);;
	}
	
	EnquiryEntity save=enquiryRepo.save(entity);
	
	return save.getEnqId()!=null;
}

    @Override
    public List<EnquiryDTO> getEnquiries(Integer counsellorId) {
	
    	
    	List<EnquiryDTO> enqsDtoList = new ArrayList<>();    	
    	 List<EnquiryEntity>enqList = 
    			 enquiryRepo.findByCounsellorCounsellorId(counsellorId);
    	
    	 for (EnquiryEntity entity:enqList) {
    		 EnquiryDTO dto =new EnquiryDTO();
    		 BeanUtils.copyProperties(entity, dto);
    		 enqsDtoList.add(dto);
    	 }
	return enqsDtoList;
}

    @Override
    public List<EnquiryDTO> getEnquiries(EnqFilterDTO filterDTO, Integer counsellorId) {
     
    	EnquiryEntity entity = new EnquiryEntity();
    	
    	if(filterDTO.getClassMode()!=null && 
    			!filterDTO.getClassMode().equals("")) {
    		entity.setClassMode(filterDTO.getClassMode());
    	}
    	if(filterDTO.getCourse()!=null && 
    			!filterDTO.getClassMode().equals("")) {
    		entity.setCourse(filterDTO.getCourse());
    	}
    	if(filterDTO.getEnqStatus()!=null && 
    			!filterDTO.getEnqStatus().equals("")) {
    		entity.setCourse(filterDTO.getEnqStatus());
    	}
    	
    	CounsellorEntity counsellor = new CounsellorEntity();
    	counsellor.setCounsellorId(counsellorId);
    	entity.setCounselloer(counsellor);
    	
    	Example<EnquiryEntity>of =Example.of(entity);
    	
    	List<EnquiryEntity> enqList =enquiryRepo.findAll(of);
    	
    	List<EnquiryDTO>  enqsDtoList =new ArrayList<>();
    	
   	 for (EnquiryEntity enq: enqList) {
		 EnquiryDTO dto =new EnquiryDTO();
		 BeanUtils.copyProperties(entity, dto);
		 enqsDtoList.add(dto);
   	 }   
	    return enqsDtoList;
   }

    @Override
    public EnquiryDTO getEnquiryById(Integer enqId) {
	
    	Optional<EnquiryEntity>byId=enquiryRepo.findById(enqId);
    	
    	if(byId.isPresent()) {
    		EnquiryEntity enquiryEntity = byId.get();
    		EnquiryDTO dto = new EnquiryDTO();
    		BeanUtils.copyProperties(enquiryEntity, dto);
    		return dto;
    	}
    	
	return null;
}
}
