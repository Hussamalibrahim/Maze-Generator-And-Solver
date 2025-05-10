import javax.swing.*;

public class Frame extends JFrame {
    Frame(){
        add(new Panel());
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
