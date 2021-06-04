package controllers;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class HelpMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HelpMenu() {
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Arial", Font.CENTER_BASELINE, 14));
		textArea.setLineWrap(true);
		textArea.setTabSize(0);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);
		try {
			textArea.read(new InputStreamReader(getClass().getResourceAsStream("/controllers/helptext.txt")), null);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.err.println("Can't find file location");
		}

		this.setPreferredSize(new Dimension(1000, 400));
		this.setLayout(new BorderLayout());
		this.add(scrollPane, BorderLayout.CENTER);
	}

	public static void showFrame() {
		JPanel panel = new HelpMenu();
		panel.setOpaque(true);

		JFrame frame = new JFrame("Help");
		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
	}
}
