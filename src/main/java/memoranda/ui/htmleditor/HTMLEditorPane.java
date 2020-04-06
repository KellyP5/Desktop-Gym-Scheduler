package main.java.memoranda.ui.htmleditor;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JEditorPane;

/**
 * The type Html editor pane.
 */
public class HTMLEditorPane extends JEditorPane {

    /**
     * The Anti alias.
     */
    boolean antiAlias = true;

    /**
     * Instantiates a new Html editor pane.
     *
     * @param text the text
     */
    public HTMLEditorPane(String text) {
		super("text/html", text);
	}

    /**
     * Is antialias on boolean.
     *
     * @return the boolean
     */
    public boolean isAntialiasOn() {
		return antiAlias;
	}

    /**
     * Sets anti alias.
     *
     * @param on the on
     */
    public void setAntiAlias(boolean on) {
		antiAlias = on;
	}

	public void paint(Graphics g) {
		if (antiAlias) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_RENDERING,
					RenderingHints.VALUE_RENDER_QUALITY);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			/*g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
					RenderingHints.VALUE_FRACTIONALMETRICS_ON);*/
			super.paint(g2);
		} else {
			super.paint(g);
		}
	}
}
