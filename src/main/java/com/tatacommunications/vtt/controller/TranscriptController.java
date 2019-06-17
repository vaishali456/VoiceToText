package com.tatacommunications.vtt.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tatacommunications.vtt.dbhelper.TranscriptDAO;
import com.tatacommunications.vtt.entity.RestResponse;
import com.tatacommunications.vtt.entity.TranscriptDetails;

@RestController
@RequestMapping(value = "/")
public class TranscriptController {
	
	@Autowired
	TranscriptDAO transcriptDao;
	
	 @CrossOrigin(origins = "*")
	@RequestMapping(value = "/createTranscript", method = RequestMethod.POST)
	public String createTranscript(@RequestParam("filepath")  String filepath,@RequestParam("model")  String model,@RequestParam("contentType")  String contentType) {
		RestResponse response=null;
		TranscriptDetails transcriptDtl=null;
		try {
			transcriptDtl=transcriptDao.addTranscriptDetails(filepath, model, contentType);
			}catch(Exception exception) {
				response=new RestResponse(null,500,"Internal Server Error");
			}
		
		  if(transcriptDtl==null) {
			  response=new RestResponse(null,500,"Internal Server Error");
		  }
		  else {
			  response=new RestResponse(transcriptDtl,201,new Date(System.currentTimeMillis()).toString());
		  }
		return new Gson().toJson(response);
	}
	
    @CrossOrigin(origins = "http://localhost:9002")
	@RequestMapping(value = "/getAllTranscripts", method = RequestMethod.GET)
	public String getAllTranscripts() {
		RestResponse response=new RestResponse(transcriptDao.getAllTranscriptDetails(),200,"Success");
		return new Gson().toJson(response);
	}
    
    
    @CrossOrigin(origins = "*")
   	@RequestMapping(value = "/get", method = RequestMethod.GET)
   	public String get() {
   		return new Gson().toJson("Helooo");
   	}


}
