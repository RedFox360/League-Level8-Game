package level8game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements KeyListener {
    private static BufferedImage image;
    private static boolean needImage = true;
    private static boolean gotImage = false;
    private ArrayList<Player> players = new ArrayList<>();
    protected GameState state = GameState.BEGIN;
    Font titleFont = new Font("Fredoka One", Font.PLAIN, 60);
    Font normalFont = new Font("JetBrains Mono", Font.PLAIN, 20);
    Timer frameRate;
    public GamePanel() {
        frameRate = new Timer(1000 / 25, (event) -> {
            switch (state) {
                case BEGIN ->
                    updateBeginState();
                case SETUP ->
                    updateSetupState();
                case GAME ->
                    updateGameState();
            }
            super.repaint();
        });
        frameRate.start();
        if (needImage) loadImage("monopoly-game-board.jpg");
        players.add(new Player(20, 760, 25, 25));
    }
    @Override
    public void paintComponent(Graphics g) {
        switch (state) {
            case BEGIN ->
                drawBeginState(g);
            case SETUP ->
                drawSetupState(g);
            case GAME ->
                drawGameState(g);
        }
    }

    private void updateBeginState() {
    }

    private void updateGameState() {
    }

    private void updateSetupState() {
    }

    private void drawBeginState(Graphics g) {
        g.setColor(new Color(  20, 143, 119  ));
        g.fillRect(0, 0, Monopoly.WIDTH, Monopoly.HEIGHT);
        g.setFont(titleFont);
        g.setColor(Color.WHITE);
        g.drawString("Monopoly", 250, 200);
        g.setFont(normalFont);
        g.drawString("Press enter to set up the game", 200, 400);
    }


    private void drawSetupState(Graphics g) {
        g.setColor(new Color( 36, 113, 163 ));
        g.fillRect(0, 0, Monopoly.WIDTH, Monopoly.HEIGHT);
        g.setFont(titleFont);
        g.setColor(Color.WHITE);
        g.drawString("Monopoly Setup", 150, 200);
        g.setFont(normalFont);
        g.drawString("Enter the number of players", 225, 400);
    }

    private void drawGameState(Graphics g) {
        if (gotImage) {
            g.drawImage(image, 0, 0, Monopoly.WIDTH, Monopoly.HEIGHT, null);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, Monopoly.WIDTH, Monopoly.HEIGHT);
        }
        players.forEach(player -> player.draw(g));
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER -> {
                if (state == GameState.BEGIN) state = GameState.SETUP;
                else if (state == GameState.SETUP) state = GameState.GAME;
                else if (state == GameState.GAME) state = GameState.BEGIN;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void loadImage(String imageFile) {
        if (needImage) {
            try {
                image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
                gotImage = true;
            } catch (Exception e) {
                System.out.println("An error occured trying to fetch the monopoly board image.");
            } finally {
                needImage = false;
            }
        }
    }
}
