package com.tatacommunications.vtt.ibmApiCaller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.ibm.cloud.sdk.core.http.HttpMediaType;
import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.speech_to_text.v1.SpeechToText;
import com.ibm.watson.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.speech_to_text.v1.model.SpeechRecognitionResults;
import com.ibm.watson.speech_to_text.v1.websocket.BaseRecognizeCallback;

@Component
public class apiCaller {
	String speechRecognitionResultsInString=null;
	boolean flag=false;
	int cnt=1;

	public String getSpeechRecognitionResults(String filepath, String model, String contentType) {
			System.out.println("getSpeechRecognitionResults");
		//APICalle
			
				  IamOptions options2 = new IamOptions.Builder()
		    		    .apiKey("NW2RvdRP8ItssRduLU6QwpauKtmbHEPouP97iYF2iMC7")
		    		    .build();

		    		SpeechToText speechToText = new SpeechToText(options2);

		    		speechToText.setEndPoint("https://gateway-lon.watsonplatform.net/speech-to-text/api");

		    	
		    		try {
		    		  RecognizeOptions recognizeOptions = new RecognizeOptions.Builder()
		    		    .audio(new FileInputStream(filepath))
		    		    .contentType(contentType)
		    		    .model(model)
		    		    .keywords(Arrays.asList("welcome", "Vaishali", "please"))
		    		    .keywordsThreshold((float) 0.5)
		    		    .maxAlternatives(3)
		    		    .build();
		    		  
		    		  /*SpeechRecognitionResults transcript1 = speechToText.recognize(recognizeOptions).execute().getResult();
		    		  System.out.println(transcript1); */
		    		  
		    		  BaseRecognizeCallback baseRecognizeCallback =
		    		    new BaseRecognizeCallback() {
		    			  
		    		      @Override
		    		      public void onTranscription
		    		        (SpeechRecognitionResults speechRecognitionResults) {
		    		    	  speechRecognitionResultsInString=speechRecognitionResults.toString();
		    		      }

		    		      @Override
		    		      public void onDisconnected() {
		    		    	  flag=true;
		    		    	  cnt++;
		    		      //  System.exit(0);
		    		      }
		    		    };
		    	
		    		 speechToText.recognizeUsingWebSocket(recognizeOptions,
		    		    baseRecognizeCallback);
		    	 if(cnt > 3) {
		 				return null;
		 			}
			while (flag == false && cnt < 3) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}  
		    		
			
    		
		return speechRecognitionResultsInString;

	}
	
	

}
