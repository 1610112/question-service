package com.App.questionservice.controller;

import com.App.questionservice.model.Question;
import com.App.questionservice.model.QuestionWrapper;
import com.App.questionservice.model.Response;
import com.App.questionservice.service.QuestionService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {


    @Autowired
    QuestionService questionService;



    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> allQuestions(){

        return questionService.getallQuestions();
    }

    @RequestMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category)
    {
        return questionService.getQuestionByCategory(category);
    }
    @PostMapping("add")
    public ResponseEntity<String> getQuestionByCategory(@RequestBody Question question)
    {
        return questionService.addQuestion(question);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String level,@RequestParam Integer numQ)
    {
        return questionService.getQuestionsForQuiz(level,numQ);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> ids){

        return questionService.getQuestionsFromId(ids);

    }

    @PostMapping("generateScore")
    public ResponseEntity<Integer> generateScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);

    }
}
