package spaceshapes.forms;


import spaceshapes.DynamicShape;
import spaceshapes.ImageRectangleShape;
import spaceshapes.CarrierShape;
import spaceshapes.Shape;
import spaceshapes.ShapeModel;
import spaceshapes.forms.util.FormComponent;
import spaceshapes.forms.util.FormHandler;

public class FormResolver {

	/**
	 * Returns a FormComponent object for entering attribute values for the 
	 * specified Shape subclass.
	 * 
	 * @param shapeClass the subclass of Shape for which a Form is required. 
	 */
	public static FormComponent getForm(Class<? extends Shape> shapeClass) {
		FormComponent form = new FormComponent();
		
		form.addFormElement(new ShapeFormElement());
		
		if(shapeClass == DynamicShape.class) {
			form.addFormElement(new ColourFormElement());
		} else if(shapeClass == ImageRectangleShape.class) {
			form.addFormElement(new ImageFormElement());
		}
		
		return form;
	}
	
	
	/**
	 * Returns a FormHandler implementation for creating an instance of a 
	 * specified Shape subclass. In response to a process(Form) call, a 
	 * FormHandler extracts form data and uses this to instantiate a Shape 
	 * subclass, to add the new instance to a ShapeModel and as a child of an
	 * existing CarrierShape parent.
	 * 
	 * @param shapeClass the subclass of Shape to be instantiated.
	 * @param model the ShapeModel to which the new Shape instance should be 
	 *        added.
	 * @param parent the CarrierShape object that will serve as the parent of
	 *        the newly created Shape object.
	 */
	public static FormHandler getFormHandler(Class<? extends Shape> shapeClass, ShapeModel model, CarrierShape parent) {
		FormHandler handler = null;
		
		if(shapeClass == DynamicShape.class) {
			handler = new DynamicShapeFormHandler(model, parent);
		} else if(shapeClass == ImageRectangleShape.class) {
			handler = new ImageShapeFormHandler(model, parent);
		} else {
			handler = new ShapeFormHandler(shapeClass, model, parent);
		}
		
		return handler;
	}
}
