package level8game;

import javax.swing.JFrame;

public class Monopoly extends JFrame {
    protected static final int WIDTH = 800;
    protected static final int HEIGHT = 800;
    protected void setup() {
        GamePanel panel = new GamePanel();
        super.add(panel);
        super.setSize(WIDTH, HEIGHT + 28);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.addKeyListener(panel);
        super.setVisible(true);
    }
    public static void main(String[] args) {
        new Monopoly().setup();
    }
}
