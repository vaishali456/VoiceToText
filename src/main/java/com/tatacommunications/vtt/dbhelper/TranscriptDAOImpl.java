package com.tatacommunications.vtt.dbhelper;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.tatacommunications.vtt.entity.TranscriptDetails;
import com.tatacommunications.vtt.ibmApiCaller.apiCaller;
import com.tatacommunications.vtt.parser.ResponseParser;

@Repository
public class TranscriptDAOImpl implements TranscriptDAO {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private apiCaller apiCaller;
	
	@Autowired
	private ResponseParser parser;
	
	

	public TranscriptDetails addTranscriptDetails(String filepath,String model,String contentType) {
		System.out.println("DAOImpl");
		String speechRecognitionResultsInString=apiCaller.getSpeechRecognitionResults(filepath, model, contentType);
		TranscriptDetails transcriptDtl=parser.parseResponse(speechRecognitionResultsInString);
		if(transcriptDtl!=null) {
			mongoTemplate.save(transcriptDtl, "Transcript_Details");
		}
    	return transcriptDtl;
		
		
		/*
		 * String transcript = ""; String a = "{\r\n" + "  \"results\": [\r\n" +
		 * "    {\r\n" + "      \"final\": true,\r\n" + "      \"alternatives\": [\r\n"
		 * + "        {\r\n" +
		 * "          \"transcript\": \"welcome to the world of premium services \",\r\n"
		 * + "          \"confidence\": 0.92\r\n" + "        }\r\n" + "      ]\r\n" +
		 * "    },\r\n" + "    {\r\n" + "      \"final\": true,\r\n" +
		 * "      \"alternatives\": [\r\n" + "        {\r\n" +
		 * "          \"transcript\": \"please press one from your dial tennis I do enjoy system services \",\r\n"
		 * + "          \"confidence\": 0.76\r\n" + "        }\r\n" + "      ]\r\n" +
		 * "    },\r\n" + "    {\r\n" + "      \"final\": true,\r\n" +
		 * "      \"alternatives\": [\r\n" + "        {\r\n" +
		 * "          \"transcript\": \"%HESITATION \",\r\n" +
		 * "          \"confidence\": 0.64\r\n" + "        }\r\n" + "      ]\r\n" +
		 * "    }\r\n" + "  ],\r\n" + "  \"result_index\": 0\r\n" + "}";
		 * 
		 * String b = "{\r\n" + "  \"results\": [\r\n" + "    {\r\n" +
		 * "      \"final\": true,\r\n" + "      \"alternatives\": [\r\n" +
		 * "        {\r\n" +
		 * "          \"transcript\": \"welcome to the world of premium services \",\r\n"
		 * + "          \"confidence\": 0.92\r\n" + "        },\r\n" + "        {\r\n" +
		 * "          \"transcript\": \"welcome to the world of premiums services \"\r\n"
		 * + "        },\r\n" + "        {\r\n" +
		 * "          \"transcript\": \"welcome to the world of premium services I \"\r\n"
		 * + "        }\r\n" + "      ],\r\n" + "      \"keywords_result\": {\r\n" +
		 * "        \"welcome\": [\r\n" + "          {\r\n" +
		 * "            \"normalized_text\": \"welcome\",\r\n" +
		 * "            \"start_time\": 2.37,\r\n" +
		 * "            \"end_time\": 2.87,\r\n" + "            \"confidence\": 1.0\r\n"
		 * + "          }\r\n" + "        ]\r\n" + "      }\r\n" + "    },\r\n" +
		 * "    {\r\n" + "      \"final\": true,\r\n" + "      \"alternatives\": [\r\n"
		 * + "        {\r\n" +
		 * "          \"transcript\": \"please press one from your dial tennis I do enjoy system services \",\r\n"
		 * + "          \"confidence\": 0.76\r\n" + "        },\r\n" + "        {\r\n" +
		 * "          \"transcript\": \"please press one from your dial tense I do enjoy system services \"\r\n"
		 * + "        },\r\n" + "        {\r\n" +
		 * "          \"transcript\": \"please press one from your dial tents I do enjoy system services \"\r\n"
		 * + "        }\r\n" + "      ],\r\n" + "      \"keywords_result\": {\r\n" +
		 * "        \"please\": [\r\n" + "          {\r\n" +
		 * "            \"normalized_text\": \"please\",\r\n" +
		 * "            \"start_time\": 5.98,\r\n" +
		 * "            \"end_time\": 6.42,\r\n" + "            \"confidence\": 1.0\r\n"
		 * + "          }\r\n" + "        ],\r\n" + "        \"one\": [\r\n" +
		 * "          {\r\n" + "            \"normalized_text\": \"one\",\r\n" +
		 * "            \"start_time\": 6.77,\r\n" +
		 * "            \"end_time\": 7.08,\r\n" +
		 * "            \"confidence\": 0.99\r\n" + "          }\r\n" + "        ]\r\n"
		 * + "      }\r\n" + "    },\r\n" + "    {\r\n" + "      \"final\": true,\r\n" +
		 * "      \"alternatives\": [\r\n" + "        {\r\n" +
		 * "          \"transcript\": \"%HESITATION \",\r\n" +
		 * "          \"confidence\": 0.64\r\n" + "        },\r\n" + "        {\r\n" +
		 * "          \"transcript\": \"%HESITATION %HESITATION \"\r\n" +
		 * "        },\r\n" + "        {\r\n" +
		 * "          \"transcript\": \"%HESITATION the \"\r\n" + "        }\r\n" +
		 * "      ],\r\n" + "      \"keywords_result\": {}\r\n" + "    }\r\n" +
		 * "  ],\r\n" + "  \"result_index\": 0\r\n" + "}";
		 * 
		 * 
		 * TranscriptDetails transcriptDtl = new TranscriptDetails(); Response
		 * responseObj = new Gson().fromJson(b, Response.class); ArrayList<String>
		 * matchedKeywordsList=new ArrayList<String>(); if (responseObj.getResults() !=
		 * null && responseObj.getResults().size() > 0) { for (Results result :
		 * responseObj.getResults()) { Alternatives alternatives =
		 * result.getAlternatives().get(0); if (alternatives.getConfidence() > new
		 * Double(0.10)) { transcript = transcript + alternatives.getTranscript(); }
		 * JsonElement keywordResultJson = new
		 * Gson().toJsonTree(result.getKeywords_result()); Set<Map.Entry<String,
		 * JsonElement>> entries = keywordResultJson.getAsJsonObject().entrySet(); //
		 * object for (Map.Entry<String, JsonElement> entry : entries) {
		 * matchedKeywordsList.add(entry.getKey()); } } }
		 * transcriptDtl.setMatchedKeywords(matchedKeywordsList);
		 * transcriptDtl.setTranscript(transcript); if
		 * (transcriptDtl.getMatchedKeywords().size()>0) {
		 * transcriptDtl.setFraudPossibility("yes"); }
		 * 
		 * transcriptDtl.setIvrNumber(new Random().nextInt()); transcriptDtl.setDate(new
		 * Date(System.currentTimeMillis())); transcriptDtl =
		 * mongoTemplate.save(transcriptDtl, "Transcript_Details");
		 * 
		 * return transcriptDtl;
		 */
	}

	public List<TranscriptDetails> getAllTranscriptDetails() {
		return mongoTemplate.findAll(TranscriptDetails.class,"Transcript_Details");
	
		
	}

}
