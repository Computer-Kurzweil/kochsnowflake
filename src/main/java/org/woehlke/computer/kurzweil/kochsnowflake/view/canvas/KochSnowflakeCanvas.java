package org.woehlke.computer.kurzweil.kochsnowflake.view.canvas;

import org.woehlke.computer.kurzweil.kochsnowflake.model.KochSnowflakeModel;
import org.woehlke.computer.kurzweil.kochsnowflake.model.koch.LinkedListNode;
import org.woehlke.computer.kurzweil.kochsnowflake.view.KochSnowflakeFrame;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;


/**
 * Koch Snowflake. A Fractal with self self-similarity.
 * (C) 2006 - 2022 Thomas Woehlke
 * @author Thomas Woehlke
 *
 * @see KochSnowflakeModel
 * @see Dimension
 *
 * @see JComponent
 * @see Graphics
 *
 * @see <a href="https://github.com/Computer-Kurzweil/kochsnowflake">Github Repository</a>
 * @see <a href="https://java.woehlke.org/kochsnowflake/">Maven Project Reports</a>
 *
 * Date: 05.02.2006
 * Time: 00:51:51
 */
public class KochSnowflakeCanvas extends JComponent {

    @Serial
    private final static long serialVersionUID = 242L;

    private volatile KochSnowflakeModel model;
    private volatile Dimension preferredSize;

    public KochSnowflakeCanvas(KochSnowflakeFrame tab) {
        this.model = tab.getModel();
        int width = this.model.getWorldDimensions().getWidth();
        int height = this.model.getWorldDimensions().getHeight();
        this.preferredSize = new Dimension(width, height);
        Rectangle bounds = new Rectangle(0,0,width,height);
        this.setBounds(bounds);
        this.setSize(this.preferredSize);
        this.setPreferredSize(preferredSize);
    }

    public void paint(Graphics g) {
        this.setSize(this.preferredSize);
        this.setPreferredSize(preferredSize);
        super.paintComponent(g);
        super.setBackground(Color.BLACK);
        g.setColor(Color.BLACK);
        g.fillRect(
           0,0, this.model.getWorldDimensions().getWidth(),
            this.model.getWorldDimensions().getHeight()
        );
        g.setColor(Color.RED);
        LinkedListNode startNode = model.getLinkedListNodeContainer().getStartNode();
        LinkedListNode currentNode = model.getLinkedListNodeContainer().getStartNode();
        do {
            g.drawLine(
                currentNode.getLine().getStart().getX(),
                currentNode.getLine().getStart().getY(),
                currentNode.getNext().getLine().getStart().getX(),
                currentNode.getNext().getLine().getStart().getY()
            );
            currentNode = model.getLinkedListNodeContainer().getNext();
        } while (! startNode.equals(currentNode));
    }

    public void update(Graphics g) {
        paint(g);
    }

}
