package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class OptionDialog extends JDialog {

	private static final long serialVersionUID = 9061247776369391266L;

	private final JPanel contentPanel = new JPanel();

	private JLabel value;
	private JSlider slider;
	private static JColorChooser colorChooser;
	private static int sliderValue = 1;

	public OptionDialog() {
		if(colorChooser == null) {
			colorChooser = new JColorChooser();
		} 
		
		setModal(true);
		setBounds(100, 100, 615, 499);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addMouseListener(buttonOkAction());
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(buttonCancelAction());
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		contentPanel.add(colorChooser);

		Panel panel = new Panel();
		contentPanel.add(panel);
		JLabel lblNewLabel = new JLabel("grubość pióra");
		panel.add(lblNewLabel);
		
		slider = new JSlider();
		slider.setMinimum(1);
		slider.setValue(sliderValue);
		slider.setMaximum(10);
		slider.addChangeListener(sliderAction());
		panel.add(slider);

		value = new JLabel(Integer.toString(sliderValue));
		panel.add(value);
	}

	private MouseAdapter buttonCancelAction() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		};
	}

	private MouseAdapter buttonOkAction() {
		return new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				sliderValue = slider.getValue();
				dispose();
			}
		};
	}

	private ChangeListener sliderAction() {
		return new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				value.setText(slider.getValue() + "");
			}
		};
	}

	public int getSliderValue() {
		return slider.getValue();
	}

	public Color getColor() {
		return colorChooser.getColor();
	}
}
