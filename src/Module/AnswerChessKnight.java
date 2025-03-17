package Module;

import basicFiles.Answer;

import java.util.List;

public class AnswerChessKnight extends Answer {

    List<String> answerList;

    public AnswerChessKnight(List<String> answerList){
        this.answerList = answerList;
    }

    @Override
    public List<String> getList(){
        return answerList;
    }
}
