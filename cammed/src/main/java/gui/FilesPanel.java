package gui;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class FilesPanel extends MainPanel {

	private static final long serialVersionUID = -6212995464528143641L;

	public FilesPanel(MainFrame mainFrame) {
		super(mainFrame);
		setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		add(tabbedPane);
		
		JPanel files = new JPanel();
		tabbedPane.addTab("All files", null, files, null);
		
		JPanel dicom = new JPanel();
		tabbedPane.addTab("DICOM", null, dicom, null);
	}
}
