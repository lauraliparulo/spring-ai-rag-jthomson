package de.demo.spring.ai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import de.demo.spring.ai.model.Answer;
import de.demo.spring.ai.model.Question;
import de.demo.spring.ai.services.OpenAIService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class QuestionController {

	@Autowired
	@Qualifier("simpleOpenAIservice")
	private OpenAIService simpleOpenAIservice;
	
	@Autowired
	@Qualifier("vectorStoreOpenAIService")
	private OpenAIService openAIServiceVectoreStoreQueryImpl;
	
	@PostMapping("/ask")
	public Answer askQuestion(@RequestBody Question question) {
		return simpleOpenAIservice.getAnswer(question);
	}
	
	
	/**
	 * Example "   "question": "what is the movie Spider-Man: No Way home about?"" will work, but just using "Spider-Man" no
    // "What is the best XMen Marvel movie to see?"
    // "question": "What is the best movie with Hugh Jackman?"
    // "has Tom Cruise been successful since 2012?"
    // "question": "has Harrison Ford been more successful than Tom Cruise since 2012?"
	 * **/
	@PostMapping("/movie")
	public Answer askQuestionVectorStore(@RequestBody Question question) {
		return openAIServiceVectoreStoreQueryImpl.getAnswer(question);
	}
}
