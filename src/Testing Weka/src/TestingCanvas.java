
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jovan Gunawan
 */
public class TestingCanvas extends JFrame
{  
    public static final int CANVAS_WIDTH = 640;
    public static final int CANVAS_HEIGHT = 480;
    
    private DrawCanvas canvas;
    
    public TestingCanvas()
    {
        canvas = new DrawCanvas();    // Construct the drawing canvas
      canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
 
      // Set the Drawing JPanel as the JFrame's content-pane
      Container cp = getContentPane();
      cp.add(canvas);
      // or
      // setContentPane(canvas);
 
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);   // Handle the CLOSE button
      this.pack();              // Either pack() the components; or setSize()
      this.setTitle("......");  // this JFrame sets the title
      this.setVisible(true);    // this JFrame show
    }
    
    private class DrawCanvas extends JPanel {
      // Override paintComponent to perform your own painting
      @Override
      public void paintComponent(Graphics g) {
         super.paintComponent(g);     // paint parent's background
         setBackground(Color.BLACK);  // set background color for this JPanel
 
         // Your custom painting codes. For example,
         // Drawing primitive shapes
         g.setColor(Color.YELLOW);    // set the drawing color
         g.drawOval(150, 180, 10, 10);
         g.drawRect(200, 210, 200, 30);
         g.setColor(Color.RED);       // change the drawing color
         g.fillOval(300, 310, 30, 50);
         g.fillRect(400, 350, 60, 50);
         g.drawLine(30, 40, 400, 400);
         // Printing texts
         g.setColor(Color.WHITE);
         g.setFont(new Font("Monospaced", Font.PLAIN, 12));
         g.drawString("Testing custom drawing ...", 200, 230);
      }
   }
    
    public static void main(String [] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new TestingCanvas(); // Let the constructor do the job
         }
      });
    }
}
