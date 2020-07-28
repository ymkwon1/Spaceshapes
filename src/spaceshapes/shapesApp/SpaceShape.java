package spaceshapes.shapesApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import spaceshapes.DynamicShape;
import spaceshapes.HexagonShape;
import spaceshapes.CarrierShape;
import spaceshapes.OvalShape;
import spaceshapes.RectangleShape;
import spaceshapes.Shape;
import spaceshapes.ShapeModel;
import spaceshapes.forms.FormResolver;
import spaceshapes.forms.util.Form;
import spaceshapes.forms.util.FormComponent;
import spaceshapes.forms.util.FormHandler;
import spaceshapes.views.AnimationView;
import spaceshapes.views.TableModelAdapter;
import spaceshapes.views.Task2;


/**
 * Main program for SpaceShape application. A SpaceShape instance sets up a GUI 
 * comprising three views of a ShapeModel: an animation view, a table view and
 * a tree view. In addition the GUI includes buttons and associated event
 * handlers to add new shapes to the animation and to remove existing shapes. 
 * A SpaceShape object uses a Timer to progress the animation; this results in the 
 * ShapeModel being sent a clock() message to which it responds by moving its
 * constituent Shape objects and then by notifying the three views 
 * (ShapeModelListeners). The application uses a ShapeConfig object to read 
 * properties from the spaceshapes.properties file, one of which is the name of a
 * ShapeFactory implementation class that is used to create Shapes on request. 
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
@SuppressWarnings("serial")
public class SpaceShape extends JPanel {
	private static final int DELAY = 25;

	// Underlying model for the application.
	private ShapeModel _model;
	
	private ShapeClassComboBoxModel _comboBoxModel;
	
	// View instances.
	private JTree _treeView;
	private AnimationView _animationView;
	private JTable _tabularView;
	
	/*
	 * Adapter objects (ShapeModelListeners) that transform ShapeModelEvents 
	 * into Swing TreeModel and TableModel events. 
	 */ 
	private Task2 _treeModelAdapter;
	private TableModelAdapter _tableModelAdapter;
	
	// Swing components to handle user input.
	private JButton _newShape;
	private JButton _deleteShape;
	private JComboBox<Class<? extends Shape>> _shapeTypes;
	
	// Shape selected in the JTree view.
	private Shape _shapeSelected;

	/**
	 * Creates a SpaceShape object.
	 */
	public SpaceShape() {
		// Instantiate model and populate it with an initial set of shapes.
		ShapeConfig config = ShapeConfig.instance();
		_model = new ShapeModel(config.getAnimationBounds());
		populateModel();
		
		_comboBoxModel = new ShapeClassComboBoxModel();
		
		// Instantiate GUI objects and construct GUI.
		buildGUI();
		
		// Register views with models.
		_model.addShapeModelListener(_animationView);
		_model.addShapeModelListener(_tableModelAdapter);
		_model.addShapeModelListener(_treeModelAdapter);
		
		// Setup event handlers to process user input.
		setUpEventHandlers();
		
		// Show GUI and ensure the root shape within the JTree view is selected.
		_treeView.setSelectionPath(new TreePath(_model.root()));
		
		// Start animation.
		Timer timer = new Timer(DELAY, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_model.clock();
			}
		} );
		timer.start();
	}

	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Space-Shapes");
		JComponent newContentPane = new SpaceShape();
		frame.add(newContentPane);
		frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	/*
	 * Adds shapes to the model.
	 */
	private void populateModel() {
		CarrierShape root = _model.root();
		
		_model.add(new RectangleShape(440, 0, 10, 10, 4, 2), root);
		//_model.add(new RectangleShape(0, 0, 5, 7), root);
		_model.add(new HexagonShape(20, 20, 4, 4, 200, 20, "Space-Shape"), root);
		//_model.add(new RectangleShape(0, 0, 2, 2, 10, 10), root);
		_model.add(new DynamicShape(0, 0, 2, 3, 180, 130, "Changes Color", Color.CYAN), root);
		_model.add(new OvalShape(50,110,2,2), root);
		

		CarrierShape child = new CarrierShape(10, 10, 2, 2, 100, 100);
		_model.add(new RectangleShape(10, 10, 10, 10, 4, 2), child);
		_model.add(new DynamicShape(0, 0, 2, 3, 50, 80, Color.RED), child);
		_model.add(new OvalShape(10,10,2,2, 60, 60), child);
		_model.add(child, root);
		_model.add(new DynamicShape(0,0,0,20,35,25), root);
	}
	
	/*
	 * Registers event handlers with Swing components to process user inputs.
	 */
	private void setUpEventHandlers() {
		/*
		 * Event handling code to be executed whenever the users presses the 
		 * "New" button. Based on the Shape type selected in the combo box, a
		 * suitable Form/FormHandler pair is acquired from the FormResolver.
		 * The Form is used by the user to specify attribute values for the 
		 * new Shape to be created, and the FormHandler is responsible for 
		 * instantiating the correct Shape subclass and adding the new instance
		 * to the application's ShapeModel. 
		 */
		_newShape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				Class<? extends Shape> cls = (Class<? extends Shape>)_comboBoxModel.getSelectedItem();
				FormComponent form = FormResolver.getForm(cls);
				FormHandler handler = FormResolver.getFormHandler(cls, _model, (CarrierShape)_shapeSelected);
				form.setFormHandler(handler);
				form.prepare();
				
				// Display the form.
				form.setLocationRelativeTo(null);
				form.setVisible(true);

			}
		});
		
		/*
		 * Event handling code to be executed whenever the user presses the
		 * "Delete" button. The shape that is currently selected in the JTree
		 * view is removed from the model. During removal, the removed shape's
		 * former parent is selected in the JTree.
		 */
		_deleteShape.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Shape selection = _shapeSelected;
				CarrierShape parent = selection.parent();
				
				_treeView.setSelectionPath(new TreePath(parent.path().toArray()));
				_model.remove(selection);
				
			}
		});
		
		/*
		 * Event handling code to be executed whenever the user selects a node
		 * within the JTree view. The event handler records which shape is
		 * selected and in addition enables/disables the "New" and "Delete"
		 * buttons appropriately. In addition, the TableModel representing the
		 * the shape selected in the JTree component is informed of the newly
		 * selected shape.
		 */
		_treeView.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				TreePath selectionPath = _treeView.getSelectionPath();
				_shapeSelected = (Shape)selectionPath.getLastPathComponent();
				
				/*
				 * Enable button fNewShape only if what is selected in the 
				 * JTree is a CarrierShape. Rationale: new shapes can only be
				 * added to CarrierShape instances. 
				 */
				_newShape.setEnabled(_shapeSelected instanceof CarrierShape);
				
				/*
				 * Enable button fDeleteShape only if what is selected in the
				 * JTree is not the root node. Rationale: any shape can be 
				 * removed with the exception of the root.
				 */
				_deleteShape.setEnabled(_shapeSelected != _model.root());
				
				/*
				 * Tell the table model to represent the shape that is now
				 * selected in the JTree component.
				 */
				_tableModelAdapter.setAdaptee(_shapeSelected);
			}
		});
	}
	
	/*
	 * Creates and lays out GUI components. Note: there is nothing particularly
	 * interesting about this method - it simply builds up a composition of GUI
	 * components and makes use of borders, scroll bars and layout managers. 
	 */
	@SuppressWarnings("unchecked")
	private void buildGUI() {
		// Create Swing model objects.
		_treeModelAdapter = new Task2(_model);
		_tableModelAdapter = new TableModelAdapter(_model.root());
		
		// Create main Swing components.
		_treeView = new JTree(_treeModelAdapter);
		_treeView.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		_tabularView = new JTable(_tableModelAdapter);
		_animationView = new AnimationView(ShapeConfig.instance().getAnimationBounds());
		
		/*
		 * Create a panel to house the JTree component. The panel includes a 
		 * titled border and scrollbars that will be activated when necessary.
		 */
		JPanel treePanel = new JPanel();
		treePanel.setBorder(BorderFactory.createTitledBorder("Shape composition hierarchy"));
		JScrollPane scrollPaneForTree = new JScrollPane(_treeView);
		scrollPaneForTree.setPreferredSize(new Dimension(300,504));
		treePanel.add(scrollPaneForTree);
		
		/*
		 * Create a panel to house the animation view. This panel includes a 
		 * titled border and scroll bars if the animation area exceeds the 
		 * allocated screen space.
		 */
		JPanel animationPanel = new JPanel();
		animationPanel.setBorder(BorderFactory.createTitledBorder("Space shape animation"));
		JScrollPane scrollPaneForAnimation = new JScrollPane(_animationView);
		scrollPaneForAnimation.setPreferredSize(new Dimension(504,504));
		animationPanel.add(scrollPaneForAnimation);
		_animationView.setPreferredSize(ShapeConfig.instance().getAnimationBounds());

		
		/*
		 * Create a panel to house the tabular view. Again, decorate the 
		 * tabular view with a border and enable automatic activation of 
		 * scroll bars.
		 */
		JPanel tablePanel = new JPanel();
		tablePanel.setBorder(BorderFactory.createTitledBorder("Shape state"));
		JScrollPane scrollPaneForTable = new JScrollPane(_tabularView);
		scrollPaneForTable.setPreferredSize(new Dimension(810,150));
		tablePanel.add(scrollPaneForTable);
		
		/*
		 * Create a control panel housing buttons for creating and destroying 
		 * shapes, plus a combo box for selecting the type of shape to create.
		 */
		JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		controlPanel.setBorder(BorderFactory.createTitledBorder("Control panel"));
		_newShape = new JButton("New");
		_deleteShape = new JButton("Delete");
		_shapeTypes = new JComboBox<Class<? extends Shape>>(_comboBoxModel);
		
		/*
		 * Set up a custom renderer for the Combo box. Instead of displaying 
		 * the fully qualified names (that include packages) of Shape 
		 * subclasses, display only the class names (without the package 
		 * prefixes).
		 */
		_shapeTypes.setRenderer(new BasicComboBoxRenderer() {
			@Override
			public Component getListCellRendererComponent(@SuppressWarnings("rawtypes") JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				String className = value.toString().substring(value.toString().lastIndexOf('.') + 1);
				return super.getListCellRendererComponent(list, className, index, isSelected, cellHasFocus);
			}
		});
		
		
		controlPanel.add(_newShape);
		controlPanel.add(_deleteShape);
		controlPanel.add(_shapeTypes);
		
		JPanel top = new JPanel(new BorderLayout());
		top.add(animationPanel, BorderLayout.CENTER);
		top.add(treePanel, BorderLayout.WEST);
		top.add(tablePanel, BorderLayout.SOUTH);
		
		setLayout(new BorderLayout());
		add(top, BorderLayout.CENTER);
		add(controlPanel, BorderLayout.SOUTH);
	}
	
	
	/*
	 * Helper class to define a custom model for the Combo box. This 
	 * ComboBoxModel stores Shape subclasses that are acquired from 
	 * ShapeConfig. 
	 */
	private class ShapeClassComboBoxModel extends
			DefaultComboBoxModel<Class<? extends Shape>> {

		public ShapeClassComboBoxModel() {
			List<Class<? extends Shape>> shapeClasses = ShapeConfig.instance().getShapeClasses();
			
			for (Class<? extends Shape> cls : shapeClasses) {
				addElement(cls);
			}
		}

	}
}
