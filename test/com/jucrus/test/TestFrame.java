package com.jucrus.test;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.jucrus.ImageCanvas;
import com.jucrus.MagickWand;

public class TestFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton openButton;
	private JButton actionButton;

	private ImageCanvas canvasPanel;
	private MagickWand wand;

	public TestFrame() {
		super();

		Container container = getContentPane();

		this.wand = new MagickWand();
		this.canvasPanel = new ImageCanvas(this.wand);
		
		container.add(this.canvasPanel);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		panel.setBorder(new TitledBorder(""));

		this.openButton = new JButton("OPEN");
		this.openButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canvasPanel.openImage(Util.getTestFile("610px-TUX-G2-SVG.svg.png"));
			}
		});

		this.actionButton = new JButton("ACTION");
		this.actionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				canvasPanel.drawText("jucrus");
				// canvasPanel.polynomial();
				canvasPanel.repaint();
			}
		});

		panel.add(openButton);
		panel.add(actionButton);
		container.add(BorderLayout.SOUTH, panel);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		this.setSize(600, 480);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new TestFrame();
	}
}
