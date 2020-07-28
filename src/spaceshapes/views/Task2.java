package spaceshapes.views;

import java.util.List;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

import spaceshapes.Shape;
import spaceshapes.ShapeModel;
import spaceshapes.ShapeModelEvent;
import spaceshapes.ShapeModelListener;
import spaceshapes.ShapeModelEvent.EventType;

public class Task2 extends Task1 implements ShapeModelListener{

	public Task2(ShapeModel shapeModel) {
		super(shapeModel);
	}

	@Override
	public void update(ShapeModelEvent event) {
		
		Shape shape = event.operand();
		ShapeModel source = event.source();
		int childIndex = event.index();

		Object[] path = {shape}; // if parent is null the path is to itself
		if (event.parent() != null) {
			List<Shape> pathList = event.parent().path();
			path = pathList.toArray();
		}

		int[] childIndices = {childIndex};
		Shape[] children = {shape};

		TreeModelEvent treeModelEvent = new TreeModelEvent(source, path, childIndices, children);
		for (TreeModelListener listener: _listener) {
			if (event.eventType() == EventType.ShapeAdded) {
				listener.treeNodesInserted(treeModelEvent);
			}

			if (event.eventType() == EventType.ShapeRemoved) {
				listener.treeNodesRemoved(treeModelEvent);

			}
		}
	}
}
