package basicFiles;

public abstract class ChessFigure {

    protected class Position{
        char let;
        int num;

        public Position(char let, int num){
            this.let = String.valueOf(let).toUpperCase().charAt(0);
            this.num = num;
        }

        public Position getPosition(){
            return new Position(let, num);
        }

        public char getLetter(){
            return let;
        }

        public int getNumber(){
            return num;
        }

        public boolean isIntersect(Position position){
            return position.getNumber() == num && position.getLetter() == let;
        }

        @Override
        public String toString(){
            return let + "" + num;
        }
    }

    public Position getPosition(){
        return null;
    }

    public void setPosition(Position position){

    }

    public void setPosition(char let, int num){

    }
}
