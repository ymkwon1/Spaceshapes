package spaceshapes.views;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import spaceshapes.CarrierShape;
import spaceshapes.Shape;
import spaceshapes.ShapeModel;

public class Task1 implements TreeModel {
	private ShapeModel _shapeModel;
	protected List<TreeModelListener> _listener = new ArrayList<>();

	public Task1(ShapeModel shapeModel) {
		_shapeModel = shapeModel;
	}

	@Override
	public CarrierShape getRoot() {
		return _shapeModel.root();
	}


	@Override
	public Object getChild(Object parent, int index) {
		if(parent instanceof CarrierShape) {
			try {
				return ((CarrierShape) parent).shapeAt(index);
			}
			catch (IndexOutOfBoundsException e)
			{
				return null;
			}
		}
		return null;
	}

	@Override
	public int getChildCount(Object parent) {
		if(parent instanceof CarrierShape) {
			return ((CarrierShape) parent).shapeCount();
		}
		return 0;
	}

	@Override
	public boolean isLeaf(Object node) {
		if (node instanceof CarrierShape) {
			return false;
		}
		return true;

	}

	//empty
	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		if(parent instanceof CarrierShape && child instanceof Shape) {
			return ((CarrierShape) parent).indexOf((Shape)child);
		}
		return -1;
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		_listener.add(l);

	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		_listener.remove(l);

	}


}
