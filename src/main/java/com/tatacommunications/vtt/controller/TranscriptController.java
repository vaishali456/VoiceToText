package com.tatacommunications.vtt.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
   	@RequestMapping(value = "/get", method = RequestMethod.POST)
   	public String get(@RequestParam("url") String url,@RequestParam("name") String name) {
    	try {
			downloadUsingStream(url,name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   		return new Gson().toJson("Helooo");
   	}
    
    
	  private static void downloadUsingStream(String urlStr, String file) throws IOException{
	        URL url = new URL(urlStr);
	        BufferedInputStream bis = new BufferedInputStream(url.openStream());
	        FileOutputStream fis = new FileOutputStream(file);
	        byte[] buffer = new byte[1024];
	        int count=0;
	        while((count = bis.read(buffer,0,1024)) != -1)
	        {
	        	
	            fis.write(buffer, 0, count);
	        }
	        fis.close();
	        bis.close();
	        try {
	        	 FileInputStream new1=new FileInputStream(file);
	        }catch(Exception e) {
	        	System.out.println("File Not Found");
	        	e.printStackTrace();
	        }
	       
	    }

	    private static void downloadUsingNIO(String urlStr, String file) throws IOException {
	        URL url = new URL(urlStr);
	        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
	        FileOutputStream fos = new FileOutputStream(file);
	        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
	        fos.close();
	        rbc.close();
	    }



}
