package spaceshapes.shapesApp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * The TestAll class declaration is annotated using JUnit's @RunWith and @Suite 
 * annotations. The effect of these annotations is to define a test suite (a 
 * collection of named unit test classes). When a test suite is run by the JUnit 
 * TestRunner, all @Test methods in all named test classes are executed.
 * 
 * @author Ian Warren
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({spaceshapes.TestCarrierShape.class, 
	spaceshapes.views.TestTask1.class, 
	spaceshapes.views.TestTask2.class,
	spaceshapes.forms.TestImageShapeFormHandler.class})
public class TestAll {}


