import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import java.awt.*;
import java.awt.event.*;

public class TicTac extends JFrame{
    private JButton[][] buttons;
    private boolean playerTurn =true;
    public TicTac(){
        super("Tic Tac Toe");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        initializeButtons();
        setUI();
        getContentPane().setBackground(Color.BLACK);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private void initializeButtons(){
        buttons = new JButton[3][3];
        for(int i=0;i<3;i++){
            for(int j = 0;j<3;j++){
                final int row=i;
                final int column=j;
                buttons[i][j]=new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 100));
                buttons[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        buttonClicked(row,column);
                    }
                });
            }
        }
    }
    private void setUI(){
        setLayout(new GridLayout(3,3));
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                add(buttons[i][j]);
            }
        }
    }
    private void buttonClicked(int row,int column){
        if(buttons[row][column].getText().equals("")){
            if(playerTurn){
                buttons[row][column].setText("X");
            }else{
                buttons[row][column].setText("O");
            }
            getContentPane().setForeground(Color.BLACK);
            playerTurn = !playerTurn;
            checkWinner();
            titleBar();
        }
    }
    private void checkWinner(){
        //check for row

        for(int i=0;i<3;i++){
            if(checkLine(buttons[i][0].getText(),buttons[i][1].getText(),buttons[i][2].getText())){
                announceWinner(buttons[i][0].getText());
                return;
            }
        }

        //check for column
        for(int i=0;i<3;i++){
            if(checkLine(buttons[0][i].getText(),buttons[1][i].getText(),buttons[2][i].getText())){
                announceWinner(buttons[0][i].getText());
                return;
            }
        }

        // Check diagonals
        if (checkLine(buttons[0][0].getText(), buttons[1][1].getText(), buttons[2][2].getText()) ||
                checkLine(buttons[0][2].getText(), buttons[1][1].getText(), buttons[2][0].getText())) {
            announceWinner(buttons[1][1].getText());
            return;
        }

        // Check for a draw
        boolean draw = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    draw = false;
                    break;
                }
            }
        }
        if (draw) {
            announceWinner("It's a draw!");
        }
    }
    private boolean checkLine(String f,String s,String t){
        return !f.equals("") && f.equals(s) && f.equals(t);
    }
    private void announceWinner(String w){
        JOptionPane.showMessageDialog(this, "winner is : "+w);
        resetGame();
    }
    private void resetGame(){
        // Clear the text on all buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        // Reset the player turn
        playerTurn= true;
    }
    private void titleBar(){
        if(playerTurn){
            setTitle("X's turn");
        }else{setTitle("O's turn");}
    }
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TicTac();
            }
        });
    }

}
