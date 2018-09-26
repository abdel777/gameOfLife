import javax.swing.*;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Iterator;

import static java.util.Collections.copy;

public class Frame extends JFrame implements ActionListener {
    private Grid grid = new Grid();

    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    JMenuItem randomBtn = new JMenuItem("Random");
    JMenuItem playBtn = new JMenuItem("Start");
    JMenuItem endBtn = new JMenuItem("End");
    JMenuItem resetBtn = new JMenuItem("Reset");


    private boolean play =false;
    private List<Cell> copyOfCells = new ArrayList<>();
    private int generation = 0;
    private int aliveCells = 0;
    JLabel generationLabel = new JLabel(); // this is just to show the number of the generation and the alive cells

    public Frame(){
        grid.setPreferredSize(new Dimension(Grid.width, Grid.height));

        this.setSize(new Dimension(800,500));
        Image im = Toolkit.getDefaultToolkit().getImage("D:\\Master\\Softwaretechnik 1\\gameOflife2\\iconLife.png");
        this.setIconImage(im);
        this.setTitle("Game of life");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                if(play) {
                    Iterator<Cell> it =  (grid.getListOfCells()).iterator();
                    while (it.hasNext()){
                        Cell celll = it.next();
                        Cell cellll = new Cell(celll.getXX(),celll.getYY(), grid.entscheiden(celll));
                        if(celll.isAlive()) aliveCells++;
                        copyOfCells.add(cellll);
                    }

                    copy(grid.getListOfCells(), copyOfCells);
                    grid.repaint();
                    copyOfCells.clear();
                    generationLabel.setText("Generation: "+generation+" | Lives cells :"+aliveCells);
                    generation++;
                    aliveCells=0;
                }
            }
        };
        timer.scheduleAtFixedRate(task,0,1);
        generationLabel.setText("Generation: "+generation+" | Live cells : "+aliveCells);

        menuBar.add(menu);


        playBtn.setAccelerator(KeyStroke.getKeyStroke(49, 8));
        menu.add(playBtn);


        resetBtn.setAccelerator(KeyStroke.getKeyStroke(50, 8));
        menu.add(resetBtn);


        randomBtn.setAccelerator(KeyStroke.getKeyStroke(51, 8));
        menu.add(randomBtn);

        endBtn.setAccelerator(KeyStroke.getKeyStroke(52, 8));
        menu.add(endBtn);

        randomBtn.addActionListener(new RandomListener());
        resetBtn.addActionListener(new ResetListener());
        playBtn.addActionListener(new PlayListener());
        endBtn.addActionListener(new EndListener());


        this.setLayout(new BorderLayout());
        this.setJMenuBar(menuBar);
        this.getContentPane().add(generationLabel,BorderLayout.SOUTH);
        this.getContentPane().add(grid,BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);

    }

    // make a random cells in the grid with random Button
    public class RandomListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            endBtn.setEnabled(false);
            grid.setRandomTest(true);
            grid.setMouseClick(true);

            grid.repaint();
        }
    }

    // make the grid empty again
    public class ResetListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            play=false;
            playBtn.setEnabled(true);
            randomBtn.setEnabled(true);
            endBtn.setEnabled(false);
            grid.setMouseClick(true);

            generation = 0;
            aliveCells = 0;
            //generationLabel.setText("Generation: "+generation+" | Lives cells :"+aliveCells);
            for(Cell cell: grid.getListOfCells()){

                cell.setAlive(false);
            }
            generation = 0;
            aliveCells = 0;
            generationLabel.setText("Generation: "+generation+" | Live cells : "+aliveCells);
            grid.repaint();
        }
    }

    // start the play
    public class PlayListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            play=true;
            randomBtn.setEnabled(false);
            playBtn.setEnabled(false);
            resetBtn.setEnabled(false);
            endBtn.setEnabled(true);
            grid.setMouseClick(false);
        }
    }

    public class EndListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            play=false;
            randomBtn.setEnabled(true);
            playBtn.setEnabled(true);
            resetBtn.setEnabled(true);
            endBtn.setEnabled(false);
            grid.setMouseClick(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
