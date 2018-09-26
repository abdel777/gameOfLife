// Cell of the Matrix

public class Cell{
    private int x,y; // the position of the cell in the grid, from this point we can sketch up a rectangle
    private boolean alive; // the state of the cell; alive or dead


    public Cell(int x,int y, boolean alive){ // the first constructor
        this.x=x;
        this.y=y;
        this.alive=alive;
    }

    public Cell(int x,int y){ // the second constructor
        this.x=x;
        this.y=y;
    }


    public boolean isAlive(){
        return alive;
    } // to check if the cell is alive or dead

    public void setAlive(boolean alive){
        this.alive=alive;
    }

    public int getXX(){
        return x;
    }


    public int getYY(){
        return y;
    }

    @Override
    public boolean equals( Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cell otherCell = (Cell) obj;

        if (!(obj instanceof Cell))
            return false;
        return otherCell.getXX() == this.getXX() && otherCell.getYY() == this.getYY();
    }

}