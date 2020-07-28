package spaceshapes.forms;

import java.awt.Color;

import spaceshapes.DynamicShape;
import spaceshapes.CarrierShape;
import spaceshapes.ShapeModel;
import spaceshapes.forms.util.Form;
import spaceshapes.forms.util.FormHandler;

public class DynamicShapeFormHandler implements FormHandler {
	private ShapeModel _model;
	private CarrierShape _parentOfNewShape;

	public DynamicShapeFormHandler(ShapeModel model,
			CarrierShape parent) {
		_model = model;
		_parentOfNewShape = parent;
	}

	@Override
	public void processForm(Form form) {
		int x = 0;
		int y = 0;
		int deltaX = form.getFieldValue(Integer.class, ShapeFormElement.DELTA_X);
		int deltaY = form.getFieldValue(Integer.class, ShapeFormElement.DELTA_Y);
		int width = form.getFieldValue(Integer.class, ShapeFormElement.WIDTH);
		int height = form.getFieldValue(Integer.class, ShapeFormElement.HEIGHT);
		String text = form.getFieldValue(String.class, ShapeFormElement.TEXT);
		Color colour = (Color) form
				.getFieldValue(Color.class, ColourFormElement.COLOUR);

		DynamicShape newShape = new DynamicShape(x, y,
				deltaX, deltaY, width, height, text, colour);
		_model.add(newShape, _parentOfNewShape);
	}

}
