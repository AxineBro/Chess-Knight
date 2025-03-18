package Module.AxineQueue;

import Module.JavaQueue.AnswerChessKnightJava;
import Utils.*;
import basicFiles.*;

import java.util.*;

public class SolutionChessKnightAxine extends Solution {
    private Knight knight;
    private List<String> solutionPath;
    private Position target;
    private Position bestPosition;

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
            for (int k = 0; k < 2; k++) {
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        possibleMove = new Position((char) ((int)position.getLetter() + (int) (Math.pow(-1, (j + 1))* (k+1))), position.getNumber()+(int) (Math.pow(-1, (i + 1)) * (2 - k)));
                        if(isRealMives(possibleMove))
                            possibleMoves.add(possibleMove);
                    }
                }
            }
            return possibleMoves;
        }

        private boolean isRealMives(Position positionMove){
            return 'A' <= positionMove.getLetter() &&
                    positionMove.getLetter() <= 'H' &&
                    positionMove.getNumber() >= 1 &&
                    positionMove.getNumber() <= 8;
        }
    }

    public SolutionChessKnightAxine(char let, int num){
        knight = new Knight(let, num);
    }

    @Override
    public void solve() {
        if (solutionPath != null)
            solutionPath.clear();

        class Node {
            Position pos;
            List<String> path;

            Node(Position pos, List<String> path) {
                this.pos = pos;
                this.path = new ArrayList<>(path);
                this.path.add(pos.toString());
            }
        }

        AxineLinkedList<Node> queue = new AxineLinkedList<>();
        Set<String> visited = new HashSet<>();

        Position start = knight.getPosition();
        queue.add(new Node(start, new ArrayList<>()));
        visited.add(start.toString());

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.pos.isIntersect(target)) {
                solutionPath = current.path;
                return;
            }

            Knight currentKnight = new Knight(current.pos);
            List<Position> possibleMoves = currentKnight.getListPossibleMoves();

            for (Position next : possibleMoves) {
                if(!visited.contains(next.toString())) {
                    visited.add(next.toString());
                    queue.add(new Node(next, current.path));
                }
            }
        }

        solutionPath = null;
    }

    @Override
    public void solveParams(Object... objects) {
        char targetLet = (char) objects[0];
        int targetNum = (int) objects[1];
        target = new Position(targetLet, targetNum);
        solve();
    }

    @Override
    public Answer getAnswer(){
        Answer answer = new AnswerChessKnightJava(solutionPath);
        return answer;
    }
    public List<String> getSolutionPath() {
        return solutionPath;
    }
}
