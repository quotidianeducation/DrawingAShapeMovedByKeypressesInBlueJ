import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MovingShape extends JPanel implements KeyListener{

    private int x = 50;
    private int y = 50;
    private int size = 100;

    public MovingShape() {
        addKeyListener(this);
        setFocusable(true); // Very important to receive key eventsóú
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Determine the color based on vertical position
        Color circleColour;
        if (y + size > getHeight() / 2) {  //Check if bottom of circle is below the middle
            circleColour = Color.RED;
        } else {
            circleColour = Color.YELLOW;
        }
        g.setColor(circleColour);
        g.fillOval(x, y, size, size); //Draw the shape
        g.setColor(Color.BLACK);
        g.drawString("Coordinates: (" + x + ", " + y + ")", 10, 20); //Show coordinates
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int step = 10;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT: // Handle key arrow press
            x -= step;
            break;
            case KeyEvent.VK_RIGHT: // Handle key arrow press
            x += step;
            break;
            case KeyEvent.VK_UP: // Handle key arrow pressú
            y -= step;
            break;
            case KeyEvent.VK_DOWN: // Handle key arrow press
            y += step;
            break;
            case KeyEvent.VK_SPACE: // Handle spacebar press
            centerCircle();
            break;
        }

        // Boundary checking using if statements
        if (x < 0) {
            x = 0;
        } else if (x > getWidth() - size) {
            x = getWidth() - size;
        }

        if (y < 0) {
            y = 0;
        } else if (y > getHeight() - size) {
            y = getHeight() - size;
        }

        repaint(); //Redraw the panel

    }

    @Override
    public void keyTyped(KeyEvent e) {} //Not used

    @Override
    public void keyReleased(KeyEvent e) {} //Not used

    // Method to center the circle
    private void centerCircle() {
        x = (getWidth() - size) / 2;
        y = (getHeight() - size) / 2;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Moving Shape"); 
        MovingShape canvas = new MovingShape();
        frame.add(canvas);
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
