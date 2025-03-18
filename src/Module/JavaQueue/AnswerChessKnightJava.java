package Module.JavaQueue;

import basicFiles.Answer;

import java.util.List;

public class AnswerChessKnightJava extends Answer {

    List<String> answerList;

    public AnswerChessKnightJava(List<String> answerList){
        this.answerList = answerList;
    }

    @Override
    public List<String> getList(){
        return answerList;
    }
}
