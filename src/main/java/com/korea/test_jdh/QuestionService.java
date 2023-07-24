package com.korea.test_jdh;


import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import java.util.Optional;
import com.korea.test_jdh.DataNotFoundException;
import java.time.LocalDateTime;


@RequiredArgsConstructor
@Service
public class QuestionService {


    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    public Question getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    public void create(String subject, String content) {
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setUserName("홍길동");
        q.setRecommend(0);
        q.setView(0);
        q.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q);
    }

    public void modify(Question question, String subject, String content) {
        question.setSubject(subject);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());
        this.questionRepository.save(question);
    }

    public void delete(Question question) {
        this.questionRepository.delete(question);
    }
}