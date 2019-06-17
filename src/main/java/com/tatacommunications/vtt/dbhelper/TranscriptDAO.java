package com.tatacommunications.vtt.dbhelper;


import java.util.List;

import com.tatacommunications.vtt.entity.TranscriptDetails;



public interface TranscriptDAO {
	
	 TranscriptDetails addTranscriptDetails(String filepath,String model,String contentType) throws Exception;
	 
	 List<TranscriptDetails> getAllTranscriptDetails();
}
