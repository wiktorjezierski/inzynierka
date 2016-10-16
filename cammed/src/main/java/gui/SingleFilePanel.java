package gui;
import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SingleFilePanel extends MainPanel {
	
	private File file;
	private JButton button;
	
	public SingleFilePanel(MainFrame mainFrame, File file) {
		super(mainFrame);
		this.file = file;
		setLayout(new BorderLayout(0, 0));
		
		button = new JButton("New button");
		button.addMouseListener(mouseAction());
		add(button, BorderLayout.NORTH);
		button.setText(file.getName());
		
	}

	private MouseAdapter mouseAction() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				PhotoEditorPanel photoEditorPanel = (PhotoEditorPanel) mainFrame.getPanel(MainFrame.PHOTO_EDITOR);
				photoEditorPanel.loadImage(file);
				
				mainFrame.changeCenterPanel(MainFrame.PHOTO_EDITOR);
			}
		};
	}

	private static final long serialVersionUID = -509936834713546107L;

}
