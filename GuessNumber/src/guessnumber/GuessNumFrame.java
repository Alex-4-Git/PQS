package guessnumber;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GuessNumFrame implements IGameListener {

    private JTextField textField = new JTextField();
    private JButton button = new JButton("Guess");
    private JTextArea textArea = new JTextArea();
    public GuessNumFrame(final GuessNum guessNum) {
        JFrame frame = new JFrame();
        textArea.setEditable(false);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(textArea);
        JPanel inputPanel = new JPanel(); {
            inputPanel.setLayout(new BorderLayout());
            inputPanel.add(textField, BorderLayout.CENTER);
            inputPanel.add(button, BorderLayout.EAST);
        }
        frame.getContentPane().add(inputPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.setSize(200,200);
        
        guessNum.addGameListener(this);
        
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                int guess = Integer.parseInt(textField.getText());
                guessNum.makeGuess(guess);
            }
            
        });
    }

    @Override
    public void gameStarted() {
        textArea.append("Game Started\n");
    }

    @Override
    public void guessMade(int num) {
        textArea.append("Guess made: " + num + "\n");
    }

    @Override
    public void tooHigh() {
        textArea.append("Too high!\n");
    }

    @Override
    public void tooLow() {
        textArea.append("Too low!\n");
    }

    @Override
    public void win() {
        textArea.append("Winner!\n");
    }
}