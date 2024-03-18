package com.App.questionservice.service;


import com.App.questionservice.dao.QuestionDao;
import com.App.questionservice.model.Question;
import com.App.questionservice.model.QuestionWrapper;
import com.App.questionservice.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
        public ResponseEntity<List<Question>> getallQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            //System.out.println("Hello world");
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            //System.out.println("Hello world");
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Success",HttpStatus.CREATED);
        }
        catch(Exception e) {
            return new ResponseEntity<>("Failure",HttpStatus.NOT_ACCEPTABLE);
        }
    }
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String level,Integer numQ){
        List<Integer> questions=questionDao.findRandomQuestionsByCategory(level,numQ);
        return new ResponseEntity<>(questions,HttpStatus.OK);


    }


    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> ids) {
            List<QuestionWrapper> questionsForQuizService=new ArrayList<>();
            for(Integer id: ids)
            {
                Question currQuestion=questionDao.findById(id).get();
                questionsForQuizService.add(new QuestionWrapper(currQuestion.getId(), currQuestion.getOption1(), currQuestion.getOption2(), currQuestion.getOption3(), currQuestion.getOption4(), currQuestion.getQuestion_title()));
            }
            return new ResponseEntity<>(questionsForQuizService,HttpStatus.OK);
        }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int score=0;
        for(Response r:responses)
        {
            Question question=questionDao.findById(r.getId()).get();
            if(r.getResponse().equals(question.getRightAnswer())) {
                    score++;
            }
        }
        return new ResponseEntity<>(score,HttpStatus.OK);
    }
}
