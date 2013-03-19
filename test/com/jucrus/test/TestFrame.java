package com.jucrus.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.GeneralPath;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.jucrus.Canvas;
import com.jucrus.CanvasBuilder;

public class TestFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton openButton;
	private JButton actionButton;

	private CanvasPanel canvasPanel;

	public TestFrame() {
		super();

		Container container = getContentPane();

		this.canvasPanel = new CanvasPanel();
		container.add(this.canvasPanel);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		panel.setBorder(new TitledBorder(""));

		this.openButton = new JButton("OPEN");
		this.openButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

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

	void tilt_shift() {

	}

	class CanvasPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		private CanvasBuilder builder = new CanvasBuilder();
		private Canvas canvas;

		public CanvasPanel() {
			super();
			canvas = builder
					.build(Util.getTestFile("610px-TUX-G2-SVG.svg.png"));
		}

		public void polynomial() {
			canvas = this.builder.polynomial();
		}

		public void drawText(String text) {
			canvas = this.builder.drawText(text);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;

			g2.setColor(Color.BLACK);
			g2.fillRect(0, 0, this.getWidth(), this.getHeight());
			if (canvas != null) {
				canvas.paint(g2);
			}

			// draw GeneralPath (polygon)
			g2.setPaint(Color.red);
			int x = 5;
			int y = 7;
			int xPoints[] = { x, 200, x, 200 };
			int yPoints[] = { y, 200, 200, y };
			GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD,
					xPoints.length);
			polygon.moveTo(xPoints[0], yPoints[0]);
			for (int index = 1; index < xPoints.length; index++) {
				polygon.lineTo(xPoints[index], yPoints[index]);
			}
			polygon.closePath();
			
			System.out.println(polygon.toString());
			
			g2.draw(polygon);
		}
	}

	public static void main(String[] args) {
		new TestFrame();
	}
}
