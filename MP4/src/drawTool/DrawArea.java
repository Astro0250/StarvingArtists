package drawTool;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import main.GamePanel;

public class DrawArea extends JComponent {
	private GamePanel gp;
	private Image image;
	private Graphics2D g2;
	private int currentX, currentY, oldX, oldY;
    private int strokeSize = 10;
	private Rectangle MSrect = new Rectangle(0, 0, strokeSize, strokeSize);
	public void setMSRectX(int x){
		MSrect.x = x;
	}
	public void setMSRectY(int y){
		MSrect.y = y;
	}
	public boolean checkIfMSrectNull() {
		return MSrect != null;
	}
	public int checkStrokeSize() {
		return strokeSize;
	}
	public Container checkParentFrame(){
		return getParent();
	}

	public DrawArea(GamePanel gp) {
		this.gp = gp;
		setBackground(Color.white);
		setDoubleBuffered(false);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				oldX = e.getX();
				oldY = e.getY();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				currentX = e.getX();
				currentY = e.getY();

				if (g2 != null) {
                    g2.setStroke(new BasicStroke(strokeSize));
					g2.drawLine(oldX, oldY, currentX, currentY);
					repaint();
					oldX = currentX;
					oldY = currentY;
				}
			}
		});
	}

	public void draw(Graphics g) {
		if (image == null) {
			image = createImage(getBounds().width, getBounds().height);
			g2 = (Graphics2D) image.getGraphics();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			clear();
		}
		g.drawImage(image, getBounds().x, getBounds().y, null);
		if(MouseInfo.getPointerInfo().getLocation().x-12>getBounds().x&&MouseInfo.getPointerInfo().getLocation().x<(getBounds().width+getBounds().x+3)) {
			if(MouseInfo.getPointerInfo().getLocation().y-33>getBounds().y&&MouseInfo.getPointerInfo().getLocation().y<(getBounds().height+getBounds().y)+25){
				g.setColor((Color)g2.getPaint());
				g.fillRect(MouseInfo.getPointerInfo().getLocation().x-12, MouseInfo.getPointerInfo().getLocation().y-33, strokeSize, strokeSize);
			}
		}
		repaint();

	}
	public void writeToPng(String location) {
		BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2D = image.createGraphics();
		this.paintAll(g2D);
		try {
				if (ImageIO.write(image, "png", new File(location))) {}
		} catch (IOException e) { e.printStackTrace(); }
	}
	public void clear() {
		g2.setPaint(Color.white);
		g2.fillRect(0, 0, getSize().width, getSize().height);
		g2.setPaint(Color.black);
		repaint();
	}

	public void red() {
		g2.setPaint(Color.red);
	}

	public void black() {
		g2.setPaint(Color.black);
	}

	public void magenta() {
		g2.setPaint(Color.magenta);
	}

	public void green() {
		g2.setPaint(Color.green);
	}

	public void blue() {
		g2.setPaint(Color.blue);
	}

    public void eraser(){
        g2.setPaint(Color.white);
    }

}