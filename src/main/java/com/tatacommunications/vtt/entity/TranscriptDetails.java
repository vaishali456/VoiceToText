package com.tatacommunications.vtt.entity;
import java.util.Date;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TranscriptDetails {
	@Id
	private String transcriptId;
	
	private String transcript;
	private String fraudPossibility="no";
	public ArrayList<String> getMatchedKeywords() {
		return matchedKeywords;
	}
	public void setMatchedKeywords(ArrayList<String> matchedKeywords) {
		this.matchedKeywords = matchedKeywords;
	}
	private Double fraudRate;
	private ArrayList<String> matchedKeywords;

	private long responseTime;
	private long ivrNumber;
	
	public Double getFraudRate() {
		return fraudRate;
	}
	public void setFraudRate(Double fraudRate) {
		this.fraudRate = fraudRate;
	}
	
	public String getFraudPossibility() {
		return fraudPossibility;
	}
	public void setFraudPossibility(String fraudPossibility) {
		this.fraudPossibility = fraudPossibility;
	}
	private Date date;
	
	public String getTranscriptId() {
		return transcriptId;
	}
	public void setTranscriptId(String transcriptId) {
		this.transcriptId = transcriptId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTranscript() {
		return transcript;
	}
	public void setTranscript(String transcript) {
		this.transcript = transcript;
	}
	
	public long getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(long responseTime) {
		this.responseTime = responseTime;
	}
	public long getIvrNumber() {
		return ivrNumber;
	}
	public void setIvrNumber(long ivrNumber) {
		this.ivrNumber = ivrNumber;
	}
	
}
