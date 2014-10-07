
package definePolynomial;

import java.awt.Graphics;
import javax.swing.JPanel;

public class Window extends javax.swing.JFrame {
    private static final int X_LOWER_BOUND = -400;
    private static final int X_UPPER_BOUND = 400;

    private final int X_AXIS_POS;
    private final int Y_AXIS_POS;

    private Polynomial poly;
    private MyPanel panel;

    class MyPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            g.drawLine(0, X_AXIS_POS, getWidth(), X_AXIS_POS);
            g.drawLine(Y_AXIS_POS, 0, Y_AXIS_POS, getHeight());
            for (int i=0; i<getWidth(); i++) {
                g.drawOval(i, (int) (X_AXIS_POS - poly.eval(i)), 2, 2);
            }
        }
    }

    public Window(Polynomial p) {
        this.poly = p;
        initComponents();
        panel = new MyPanel();
        panel.setSize(X_UPPER_BOUND - X_LOWER_BOUND, (int) (p.getMax(X_LOWER_BOUND, X_UPPER_BOUND, 1) - p.getMin(X_LOWER_BOUND, X_UPPER_BOUND, 1)));
        X_AXIS_POS = (int) (p.getMax(X_LOWER_BOUND, X_UPPER_BOUND, 1));
        Y_AXIS_POS = (int) (0 - X_LOWER_BOUND);
        add(panel);
        setSize(panel.getWidth() + 50, panel.getHeight() + 75);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
