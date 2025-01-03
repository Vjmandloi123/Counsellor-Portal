package in.VijayIt.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.VijayIt.bindings.CounsellorDTO;
import in.VijayIt.entity.CounsellorEntity;
import in.VijayIt.repo.CounsellorRepo;

@Service 
public class CounsellorServiceImpl implements CounsellorService {

	@Autowired
	private CounsellorRepo counsellorRepo;
	
	@Override
	public CounsellorDTO login(CounsellorDTO counsellorDTO) {
		
		CounsellorEntity entity=counsellorRepo.findByEmailAndPwd(counsellorDTO.getEmail(),counsellorDTO.getPwd()) ; 
		
		if(entity!=null) {
			
		//copy entity obj data into dto obj and return dto obj
			
			CounsellorDTO dto = new CounsellorDTO();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}
		return null;
	}

	@Override
	public boolean uniqueEmailCheck(String email) {
		                  
		CounsellorEntity entity=counsellorRepo.findByEmail(email);
//		if(enity == null) {
//			return true;
//		}
//		
//		return false;
		
		return entity == null;
	}

	@Override
	public boolean register(CounsellorDTO counsellorDTO) {
		
		CounsellorEntity entity = new CounsellorEntity();
		
		BeanUtils.copyProperties(counsellorDTO,entity);
		
	    CounsellorEntity savedEntity=counsellorRepo.save(entity);
	    
		return null!= savedEntity.getCounsellorId();
	}
	
   
}