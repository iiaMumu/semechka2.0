import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class psyhologist extends JFrame implements ActionListener {
    final String TITLE = "Chatter : Psyhologist bot";
    final int START = 200;
    final int WINDOW = 350;
    final int WHEIGHT = 450;
    JTextArea dialog;
    JCheckBox ai;
    JTextField message;
    SimpleBot sbot;

    public static void main(String[] args){new psyhologist();}
        psyhologist(){
            setTitle(TITLE);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setBounds(START,START,WINDOW,WHEIGHT);
            dialog = new JTextArea();
            dialog.setLineWrap(true);
            JScrollPane scrollPane = new JScrollPane(dialog);
            JPanel bp = new JPanel();
            bp.setLayout(new BoxLayout(bp,BoxLayout.X_AXIS));
            ai = new JCheckBox("AI");
            ai.doClick();
            message = new JTextField();
            message.addActionListener(this);
            JButton enter = new JButton("Enter");
            enter.addActionListener(this);
            bp.add(ai);
            bp.add(message);
            bp.add(enter);
            add(BorderLayout.CENTER , scrollPane);
            add(BorderLayout.SOUTH,bp);
            setVisible(true);
            sbot = new SimpleBot();
        }
        public void actionPerformed(ActionEvent event){
        if(message.getText().trim().length()>0){
            dialog.append(message.getText()+"\n");
            dialog.append(TITLE.substring(0,9)+
                    sbot.sayInReturn(message.getText(),ai.isSelected())+ "\n");
        }
        message.setText("");
        message.requestFocusInWindow();
        }

    private String sayInReturn(String text, boolean selected) {
        return text;
    }

}


