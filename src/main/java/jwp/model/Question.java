package jwp.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Question {
    private int questionId;
    private String writer;
    private String title;
    private String contents;
    private Date createdDate;
    private int countOfAnswer;

    public Question(int questionId, String writer, String title, String contents, Date createdDate, int countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    public Question(String writer, String title, String contents, Date createdDate, int countOfAnswer) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    public int getQuestionId() {
        return questionId;
    }
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
    public String getWriter() {
        return writer;
    }
    public String getTitle(){
        return title;
    }
    public String getContents() {
        return contents;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public int getCountOfAnswer() {
        return countOfAnswer;
    }

}
