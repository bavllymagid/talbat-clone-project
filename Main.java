import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class Main{

    //intialize register buttons
    static JButton register = new JButton("register");
    static JButton customerRbutton = new JButton("Customer");
    static JButton resturantRbutton = new JButton("Resturant");
    static JPanel panel = new JPanel();

     public static void main(String[] args) {
         JFrame mainFrame = new JFrame("talbat");

         mainFrame.setBounds(300, 50, 400, 700);
         mainFrame.setVisible(true);


         mainFrame.add(panel);
         panel.add(register);

         mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         mainFrame.setResizable(false);
         register.addActionListener(new Action());

     }

     static class Action implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.add(customerRbutton);
            panel.add(resturantRbutton);
            register.setVisible(false);
        }
    }



}
