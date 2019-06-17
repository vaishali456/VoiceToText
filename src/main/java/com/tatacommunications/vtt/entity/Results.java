package com.tatacommunications.vtt.entity;


import java.util.ArrayList;
import java.util.LinkedList;

public class Results {
	Integer result_index;
	LinkedList<Alternatives> alternatives;
	Object keywords_result;
	
	
	public Object getKeywords_result() {
		return keywords_result;
	}
	public void setKeywords_result(Object keywords_result) {
		this.keywords_result = keywords_result;
	}
	public Integer getResult_index() {
		return result_index;
	}
	public void setResult_index(Integer result_index) {
		this.result_index = result_index;
	}
	
	/*
	 * public KeywordsResult getKeywords_result() { return keywords_result; } public
	 * void setKeywords_result(KeywordsResult keywords_result) {
	 * this.keywords_result = keywords_result; }
	 */
	public LinkedList<Alternatives> getAlternatives() {
		return alternatives;
	}
	public void setAlternatives(LinkedList<Alternatives> alternatives) {
		this.alternatives = alternatives;
	}
}
