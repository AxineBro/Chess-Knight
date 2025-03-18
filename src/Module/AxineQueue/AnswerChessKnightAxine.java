package Module.AxineQueue;

import basicFiles.Answer;

import java.util.List;

public class AnswerChessKnightAxine extends Answer{

    List<String> answerList;

    public AnswerChessKnightAxine(List<String> answerList){
        this.answerList = answerList;
    }

    @Override
    public List<String> getList(){
        return answerList;
    }
}
