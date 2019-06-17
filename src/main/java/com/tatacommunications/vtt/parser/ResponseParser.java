package com.tatacommunications.vtt.parser;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.tatacommunications.vtt.entity.Alternatives;
import com.tatacommunications.vtt.entity.Response;
import com.tatacommunications.vtt.entity.Results;
import com.tatacommunications.vtt.entity.TranscriptDetails;

@Component
public class ResponseParser {
	public TranscriptDetails parseResponse(String apiResponse) {
		String transcript = "";
		TranscriptDetails transcriptDtl =null;
		Response responseObj = new Gson().fromJson(apiResponse, Response.class);
		ArrayList<String> matchedKeywordsList = new ArrayList<String>();
		if (responseObj.getResults() != null && responseObj.getResults().size() > 0) {
			for (Results result : responseObj.getResults()) {
				Alternatives alternatives = result.getAlternatives().get(0);
				if (alternatives.getConfidence() > new Double(0.10)) {
					transcript = transcript + alternatives.getTranscript();
				}
				JsonElement keywordResultJson = new Gson().toJsonTree(result.getKeywords_result());
				Set<Map.Entry<String, JsonElement>> entries = keywordResultJson.getAsJsonObject().entrySet(); // object
				for (Map.Entry<String, JsonElement> entry : entries) {
					matchedKeywordsList.add(entry.getKey());
				}
			}
		}
		if(transcript.length()>0) {
			 transcriptDtl = new TranscriptDetails();
			 transcriptDtl.setMatchedKeywords(matchedKeywordsList);
				transcriptDtl.setTranscript(transcript);
				if (transcriptDtl.getMatchedKeywords().size() > 0) {
					transcriptDtl.setFraudPossibility("yes");
				}

				transcriptDtl.setIvrNumber(new Random().nextInt());
				transcriptDtl.setDate(new Date(System.currentTimeMillis()));
		}
	
		return transcriptDtl;

	}

}
