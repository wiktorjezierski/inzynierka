package gui;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class PhotoEditorPanel extends MainPanel {

	private static final long serialVersionUID = -2520633453273662741L;

	private boolean isPressed;
	private Color color;
	private int penSize;

	private BufferedImage image;
	private JPanel panel;
	private Canvas canvas;
	private File file;

	public PhotoEditorPanel(MainFrame mainFrame) {
		super(mainFrame);
		penSize = 1;
		setBounds(100, 100, 450, 300);
		setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		canvas = new Canvas() {
			private static final long serialVersionUID = 6517409616189537819L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				
				if(image != null)
					g.drawImage(image, 0, 0, null);
			}
		};
		panel.add(canvas, BorderLayout.CENTER);
		canvas.addMouseMotionListener(mouseMotionActions());
		canvas.addMouseListener(mouseButtonsActions());

	}
	
	public void loadImage(File file) {
		try {
			this.file = file;
			image = ImageIO.read(file);
			color = new Color(0, 0, 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveFile() {
		try {
			ImageIO.write(image, findFileExtension(file.getName()), file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private MouseMotionAdapter mouseMotionActions() {
		return new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				int x = arg0.getX();
				int y = arg0.getY();

				int radius = penSize == 1 ? 1 : penSize / 2;
				if (isPressed && x >= radius && x <= image.getWidth() - radius && y >= radius
						&& y <= image.getHeight() - radius) {

					for (int i = x - radius; i < x + radius; i++) {
						for (int j = y - radius; j < y + radius; j++) {
							image.setRGB(i, j, color.getRGB());
						}
					}
				}
			}
		};
	}

	private MouseAdapter mouseButtonsActions() {
		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				if (arg0.getButton() == MouseEvent.BUTTON1) {
					isPressed = true;
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					isPressed = false;
					canvas.repaint();
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON3) {	// RPM on mouse
					OptionDialog dialog = new OptionDialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					
					color = dialog.getColor();
					penSize = dialog.getSliderValue();
				}
			}
		};
	}
	
	private String findFileExtension(String name) {
		String[] split = name.split("\\.");
		return split[split.length - 1];
	}
}
