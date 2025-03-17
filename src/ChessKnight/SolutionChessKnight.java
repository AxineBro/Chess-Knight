package ChessKnight;

import basicFiles.*;

import java.util.ArrayList;
import java.util.List;

public class SolutionChessKnight extends Solution {
    private Knight knight;

    class Knight extends ChessFigure{

        private Position position;

        public  Knight(Position position){
            setPosition(position);
        }

        public  Knight(char let, int num){
            setPosition(let, num);
        }

        @Override
        public void setPosition(Position position){
            this.position = position;
        }

        @Override
        public void setPosition(char let, int num){
            this.position = new Position(let, num);
        }

        @Override
        public Position getPosition(){
            return position;
        }

        public List<Position> getListPossibleMoves(){
            List<Position> possibleMoves = new ArrayList<>();
            Position possibleMove;

            possibleMove = new Position((char) ((int)position.getLetter()-1), position.getNumber()+2);
            if(isRealMives(possibleMove))
                possibleMoves.add(possibleMove);
            possibleMove = new Position((char) ((int)position.getLetter()+1), position.getNumber()+2);
            if(isRealMives(possibleMove))
                possibleMoves.add(possibleMove);
            possibleMove = new Position((char) ((int)position.getLetter()-1), position.getNumber()-2);
            if(isRealMives(possibleMove))
                possibleMoves.add(possibleMove);
            possibleMove = new Position((char) ((int)position.getLetter()+1), position.getNumber()-2);
            if(isRealMives(possibleMove))
                possibleMoves.add(possibleMove);
            possibleMove = new Position((char) ((int)position.getLetter()-2), position.getNumber()+1);
            if(isRealMives(possibleMove))
                possibleMoves.add(possibleMove);
            possibleMove = new Position((char) ((int)position.getLetter()-2), position.getNumber()-1);
            if(isRealMives(possibleMove))
                possibleMoves.add(possibleMove);
            possibleMove = new Position((char) ((int)position.getLetter()+2), position.getNumber()+1);
            if(isRealMives(possibleMove))
                possibleMoves.add(possibleMove);
            possibleMove = new Position((char) ((int)position.getLetter()+2), position.getNumber()-1);
            if(isRealMives(possibleMove))
                possibleMoves.add(possibleMove);
            return possibleMoves;
        }

        private boolean isRealMives(Position positionMove){
            return 'A' <= positionMove.getLetter() &&
                    positionMove.getLetter() <= 'H' &&
                    positionMove.getNumber() >= 1 &&
                    positionMove.getNumber() <= 8;
        }
    }

    public SolutionChessKnight(char let, int num){
        knight = new Knight(let, num);
    }

    @Override
    public void solve(){

    }

}
