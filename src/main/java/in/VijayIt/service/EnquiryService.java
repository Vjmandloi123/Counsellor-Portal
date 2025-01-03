package in.VijayIt.service;

import java.util.List;


import in.VijayIt.bindings.DashboardResponseDTO;
import in.VijayIt.bindings.EnqFilterDTO;
import in.VijayIt.bindings.EnquiryDTO;

public interface EnquiryService {

	
	public DashboardResponseDTO getDashboardInfo(Integer counsellorId);
	
	
	public boolean addEnquiry(EnquiryDTO enqDTO, Integer counsellorId);
	

	//based on counsellerId all enquires will come
	public List<EnquiryDTO> getEnquiries(Integer counselloerId);

  
	public List<EnquiryDTO> getEnquiries(EnqFilterDTO filterDTO,Integer counselloerId);


    public EnquiryDTO getEnquiryById(Integer enqId);

}