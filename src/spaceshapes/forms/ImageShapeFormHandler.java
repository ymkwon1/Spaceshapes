package spaceshapes.forms;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.SwingWorker;

import spaceshapes.CarrierShape;
import spaceshapes.ImageRectangleShape;
import spaceshapes.ShapeModel;
import spaceshapes.forms.util.Form;
import spaceshapes.forms.util.FormHandler;

public class ImageShapeFormHandler extends SwingWorker<Void, Void> implements FormHandler {

	private ShapeModel _model;
	private CarrierShape _parentOfNewShape;
	private Form _form;


	public ImageShapeFormHandler(ShapeModel model, CarrierShape parent) {
		_model = model;
		_parentOfNewShape = parent;
	}

	@Override
	public void processForm(Form form) {
		_form = form;
		execute();
	}

	public Void doInBackground() throws Exception {
		long startTime = System.currentTimeMillis();

		// Read field values from the form.
		File imageFile = (File)_form.getFieldValue(File.class, ImageFormElement.IMAGE);
		int width = _form.getFieldValue(Integer.class, ShapeFormElement.WIDTH);
		int deltaX = _form.getFieldValue(Integer.class, ShapeFormElement.DELTA_X);
		int deltaY = _form.getFieldValue(Integer.class, ShapeFormElement.DELTA_Y);


		// Load the original image (ImageIO.read() is a blocking call).
		BufferedImage fullImage = null;
		try {
			fullImage = ImageIO.read(imageFile);
		} catch(IOException e) {
			System.out.println("Error loading image.");
		}

		int fullImageWidth = fullImage.getWidth();
		int fullImageHeight = fullImage.getHeight();

		BufferedImage scaledImage = fullImage;

		// Scale the image if necessary.
		if(fullImageWidth > width) {
			double scaleFactor = (double)width / (double)fullImageWidth;
			int height = (int)((double)fullImageHeight * scaleFactor);

			scaledImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB); 
			Graphics2D g = scaledImage.createGraphics();

			// Method drawImage() scales an already loaded image. The 
			// ImageObserver argument is null because we don't need to monitor 
			// the scaling operation.
			g.drawImage(fullImage, 0, 0, width, height, null);
		}

		// Create the new Shape and add it to the model.
		ImageRectangleShape imageShape = new ImageRectangleShape(deltaX, deltaY, scaledImage);
		_model.add(imageShape, _parentOfNewShape);

		long elapsedTime = System.currentTimeMillis() - startTime;
		System.out.println("Image loading and scaling took " + elapsedTime + "ms.");
		
		return null;
	}
}
