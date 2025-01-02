package in.VijayIt.service;

import in.VijayIt.bindings.CounsellorDTO;

public interface CounsellorService {

	
    public CounsellorDTO login(CounsellorDTO counsellorDTO);
	
	//if unique return true else return false
	public boolean uniqueEmailCheck(String email);
	
	public boolean register(CounsellorDTO counsellorDTO);
	
	
}
