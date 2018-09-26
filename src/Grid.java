import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid extends JPanel implements MouseListener{
    private List<Cell> listOfCells = new ArrayList<>(); // this list will contain all the cells later, with their positions and state of life
    public static int height=400,width=800; // height and the width of the grid
    private int rectLenght =10;
    private Cell cell;
    private Random random = new Random(); // to use the nextInt() function;
    private boolean randomTest = false;
    private boolean reset = true;
    private int posX,posY;
    private boolean mouseClick = true; // to test allowing drawing a rectangle by clicking on mouse or not.



    public Grid(){
        this.addMouseListener(this);
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);
        // initialize all the cells as dead cells
        if(reset){
            for(int i=0;i<width;i=i+rectLenght) {
                for(int j=0;j<height;j=j+rectLenght){
                    cell=new Cell(i,j,false);
                    listOfCells.add(cell);
                }
            }
            reset = !reset;
        }

        // to make random alive cells in the grid
        if (randomTest) {
            for(int i=1;i<=350;i++){
                int randomNumber = random.nextInt(listOfCells.size()); // nextInt() to generate a random number between 0 and the size of list
                listOfCells.get(randomNumber).setAlive(true); // make the specifc cell alive
            }
            randomTest = !randomTest;
        }

        // the background of the jPanel
        this.setBackground(Color.WHITE);

        // draw the grid

        for(Cell currentCell :listOfCells){
            posX = currentCell.getXX();
            posY = currentCell.getYY();

            if(currentCell.isAlive()){ // draw the cell with red color

                g.setColor(Color.RED);
                g.fillRect(posX,posY , rectLenght, rectLenght); // fill it with red
                g.setColor(Color.GRAY);
                g.drawRect(posX, posY, rectLenght, rectLenght); // the frame will be in the gray color
            }else{
                g.setColor(Color.GRAY); //
                g.drawRect(posX, posY, rectLenght, rectLenght);
            }




        }

    }


    @Override
    public void mouseClicked(MouseEvent e) { // make a cell alive or dead with th mouse
        if(mouseClick) {
            posX = e.getX();
            posY = e.getY();
            Cell makeCell = new Cell(posX - posX % rectLenght, posY - posY % rectLenght); // it must be x%rectLenght = 0 and y%rectLenght =0


            if (listOfCells.contains(makeCell)) {
                int i = listOfCells.indexOf(makeCell); // extract the index of the cell
                //System.out.println(i);
                if (listOfCells.get(i).isAlive()) {
                    listOfCells.get(i).setAlive(false);
                } else {
                    listOfCells.get(i).setAlive(true);
                }

                repaint();

            }
        }

    }


    /*
     == here we make our test for the next grid how should be
     == so the next grid will be based on the actual grid
     == we will check every cells neighbours to decide if it will be dead or alive in the next generation
     */
    public boolean entscheiden(Cell cell){
        int nachbaren=0;
        int x=cell.getXX();
        int y=cell.getYY();

        // define the neighbours of the cell
         Cell nachbarUp = new Cell(x,y-rectLenght) ;
         Cell nachbarUpLeft = new Cell(x-rectLenght,y-rectLenght);
         Cell nachbarUpRight= new Cell(x+rectLenght,y-rectLenght);
         Cell nachbarRight= new Cell(x+rectLenght,y);
         Cell nachbarLeft= new Cell(x-rectLenght,y);
         Cell nachbarDown= new Cell(x,y+rectLenght);
         Cell nachbarDownLeft= new Cell(x-rectLenght,y+rectLenght);
         Cell nachbarDownRight= new Cell(x+rectLenght,y+rectLenght);

        // we will increase the nachbaren by 1 if the tested neighbour is alive
        if(x>0){
            if(listOfCells.get(listOfCells.indexOf(nachbarLeft)).isAlive()) nachbaren++;
            if(y>0) if(listOfCells.get(listOfCells.indexOf(nachbarUpLeft)).isAlive()) nachbaren++;
            if(y<height-rectLenght) if(listOfCells.get(listOfCells.indexOf(nachbarDownLeft)).isAlive()) nachbaren++;
        }
        if(x<width-rectLenght){
            if(listOfCells.get(listOfCells.indexOf(nachbarRight)).isAlive()) nachbaren++;
            if(y>0) if(listOfCells.get(listOfCells.indexOf(nachbarUpRight)).isAlive()) nachbaren++;
            if(y<height-rectLenght) if(listOfCells.get(listOfCells.indexOf(nachbarDownRight)).isAlive()) nachbaren++;
        }

        if(y>0) if(listOfCells.get(listOfCells.indexOf(nachbarUp)).isAlive()) nachbaren++;
        if(y<height-rectLenght) if(listOfCells.get(listOfCells.indexOf(nachbarDown)).isAlive()) nachbaren++;


        // jetzt zu entscheiden ob den Cell wird leben oder nein
        if(nachbaren == 3) return true; // 3 neighbours are alive so it will live in the next generation

        if(cell.isAlive() && nachbaren==2) return true; // 2 neighbours are alive, meanwhile it is alive. so it will live in the next generation

        return false; // otherwise it will be dead in the next generation

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    // this is for make a random alive cells
    public void setRandomTest(boolean randomTest){
        this.randomTest= randomTest;
    }

    // get the list of the cells
    public List<Cell> getListOfCells(){
        return listOfCells;
    }

    public void setMouseClick(boolean click){
        mouseClick = click;
    }



}

