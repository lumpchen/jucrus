package com.jucrus;

import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SahpeSerializer {

	public static Shape readShape(final ObjectInputStream stream)
			throws IOException, ClassNotFoundException {

		if (stream == null) {
			throw new IllegalArgumentException("Null 'stream' argument.");
		}

		Shape result = null;
		final boolean isNull = stream.readBoolean();
		if (!isNull) {
			final Class c = (Class) stream.readObject();
			if (c.equals(Line2D.class)) {
				final double x1 = stream.readDouble();
				final double y1 = stream.readDouble();
				final double x2 = stream.readDouble();
				final double y2 = stream.readDouble();
				result = new Line2D.Double(x1, y1, x2, y2);
			} else if (c.equals(Rectangle2D.class)) {
				final double x = stream.readDouble();
				final double y = stream.readDouble();
				final double w = stream.readDouble();
				final double h = stream.readDouble();
				result = new Rectangle2D.Double(x, y, w, h);
			} else if (c.equals(Ellipse2D.class)) {
				final double x = stream.readDouble();
				final double y = stream.readDouble();
				final double w = stream.readDouble();
				final double h = stream.readDouble();
				result = new Ellipse2D.Double(x, y, w, h);
			} else if (c.equals(Arc2D.class)) {
				final double x = stream.readDouble();
				final double y = stream.readDouble();
				final double w = stream.readDouble();
				final double h = stream.readDouble();
				final double as = stream.readDouble(); // Angle Start
				final double ae = stream.readDouble(); // Angle Extent
				final int at = stream.readInt(); // Arc type
				result = new Arc2D.Double(x, y, w, h, as, ae, at);
			} else if (c.equals(GeneralPath.class)) {
				final GeneralPath gp = new GeneralPath();
				final float[] args = new float[6];
				boolean hasNext = stream.readBoolean();
				while (!hasNext) {
					final int type = stream.readInt();
					for (int i = 0; i < 6; i++) {
						args[i] = stream.readFloat();
					}
					switch (type) {
					case PathIterator.SEG_MOVETO:
						gp.moveTo(args[0], args[1]);
						break;
					case PathIterator.SEG_LINETO:
						gp.lineTo(args[0], args[1]);
						break;
					case PathIterator.SEG_CUBICTO:
						gp.curveTo(args[0], args[1], args[2], args[3], args[4],
								args[5]);
						break;
					case PathIterator.SEG_QUADTO:
						gp.quadTo(args[0], args[1], args[2], args[3]);
						break;
					case PathIterator.SEG_CLOSE:
						gp.closePath();
						break;
					default:
						throw new RuntimeException(
								"JFreeChart - No path exists");
					}
					gp.setWindingRule(stream.readInt());
					hasNext = stream.readBoolean();
				}
				result = gp;
			} else {
				result = (Shape) stream.readObject();
			}
		}
		return result;
	}

	public static void writeShape(final Shape shape,
			final ObjectOutputStream stream) throws IOException {

		if (stream == null) {
			throw new IllegalArgumentException("Null 'stream' argument.");
		}

		if (shape != null) {
			stream.writeBoolean(false);
			if (shape instanceof Line2D) {
				final Line2D line = (Line2D) shape;
				stream.writeObject(Line2D.class);
				stream.writeDouble(line.getX1());
				stream.writeDouble(line.getY1());
				stream.writeDouble(line.getX2());
				stream.writeDouble(line.getY2());
			} else if (shape instanceof Rectangle2D) {
				final Rectangle2D rectangle = (Rectangle2D) shape;
				stream.writeObject(Rectangle2D.class);
				stream.writeDouble(rectangle.getX());
				stream.writeDouble(rectangle.getY());
				stream.writeDouble(rectangle.getWidth());
				stream.writeDouble(rectangle.getHeight());
			} else if (shape instanceof Ellipse2D) {
				final Ellipse2D ellipse = (Ellipse2D) shape;
				stream.writeObject(Ellipse2D.class);
				stream.writeDouble(ellipse.getX());
				stream.writeDouble(ellipse.getY());
				stream.writeDouble(ellipse.getWidth());
				stream.writeDouble(ellipse.getHeight());
			} else if (shape instanceof Arc2D) {
				final Arc2D arc = (Arc2D) shape;
				stream.writeObject(Arc2D.class);
				stream.writeDouble(arc.getX());
				stream.writeDouble(arc.getY());
				stream.writeDouble(arc.getWidth());
				stream.writeDouble(arc.getHeight());
				stream.writeDouble(arc.getAngleStart());
				stream.writeDouble(arc.getAngleExtent());
				stream.writeInt(arc.getArcType());
			} else if (shape instanceof GeneralPath) {
				stream.writeObject(GeneralPath.class);
				final PathIterator pi = shape.getPathIterator(null);
				final float[] args = new float[6];
				stream.writeBoolean(pi.isDone());
				while (!pi.isDone()) {
					final int type = pi.currentSegment(args);
					stream.writeInt(type);
					for (int i = 0; i < 6; i++) {
						stream.writeFloat(args[i]);
					}
					stream.writeInt(pi.getWindingRule());
					pi.next();
					stream.writeBoolean(pi.isDone());
				}
			} else {
				stream.writeObject(shape.getClass());
				stream.writeObject(shape);
			}
		} else {
			stream.writeBoolean(true);
		}
	}
}
