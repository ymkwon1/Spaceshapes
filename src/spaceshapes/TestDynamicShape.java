
package spaceshapes;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class TestDynamicShape {
	// Fixture object that is used by the tests.
		private MockPainter _painter;

		/**
		 * This method is called automatically by the JUnit test-runner immediately
		 * before each @Test method is executed. setUp() recreates the fixture so 
		 * that there no side effects from running individual tests.
		 */
		@Before
		public void setUp() {
			_painter = new MockPainter();
		}


		/**
		 * Test to perform a bounce movement off the right-most boundary and to
		 * ensure that the Shape's position and color after the movement is correct.
		 */
		@Test
		public void testShapeMoveWithBounceOffRight() {
			DynamicShape shape = new DynamicShape(100, 20, 12, 15, 25, 35, Color.BLUE);
			shape.paint(_painter);
			shape.move(135, 10000);
			shape.paint(_painter);
			
			shape.move(135, 10000);
			shape.paint(_painter);
			assertEquals("(rectangle 100,20,25,35)"
					+ "(color changed to java.awt.Color[r=0,g=0,b=255])(rectangle filled 110,35,25,35)(color changed to java.awt.Color[r=212,g=212,b=212])"
					+ "(color changed to java.awt.Color[r=0,g=0,b=255])(rectangle filled 98,50,25,35)(color changed to java.awt.Color[r=212,g=212,b=212])"
					, _painter.toString());
		}

		
		/**
		 * Test to perform a bounce movement off the left-most boundary and to
		 * ensure that the Shape's position and color after the movement is correct.
		 */
		@Test
		public void testShapeMoveWithBounceOffLeft() {
			DynamicShape shape = new DynamicShape(10, 20, -12, 15, 25, 35, Color.BLUE);
			shape.paint(_painter);
			shape.move(10000, 10000);
			shape.paint(_painter);
			shape.move(10000, 10000);
			shape.paint(_painter);
			assertEquals("(rectangle 10,20,25,35)"
					+ "(color changed to java.awt.Color[r=0,g=0,b=255])(rectangle filled 0,35,25,35)(color changed to java.awt.Color[r=212,g=212,b=212])"
					+ "(color changed to java.awt.Color[r=0,g=0,b=255])(rectangle filled 12,50,25,35)(color changed to java.awt.Color[r=212,g=212,b=212])"
					, _painter.toString());
		}

		
		/**
		 * Test to perform a bounce movement off the left-most boundary and to
		 * ensure that the Shape's position and color after the movement is correct.
		 */
		@Test
		public void testShapeMoveWithBounceOffTop() {
			DynamicShape shape = new DynamicShape(10, 10, 12, -15, 25, 35, Color.BLUE);
			shape.paint(_painter);
			shape.move(10000, 135);
			shape.paint(_painter);
			shape.move(10000, 135);
			shape.paint(_painter);
			assertEquals("(rectangle 10,10,25,35)"
					+ "(rectangle 22,0,25,35)"
					+ "(rectangle 34,15,25,35)"
					, _painter.toString());
		}
		
		
		/**
		 * Test to perform a bounce movement off the left-most boundary and to
		 * ensure that the Shape's position and color after the movement is correct.
		 */
		@Test
		
		public void testShapeMoveWithBounceOffBottom() {
			DynamicShape shape = new DynamicShape(10, 100, -12, 15, 25, 35, Color.BLUE);
			shape.paint(_painter);
			shape.move(10000, 145);
			shape.paint(_painter);
			shape.move(10000, 145);
			shape.paint(_painter);
			assertEquals("(rectangle 10,100,25,35)"
					+ "(rectangle 0,110,25,35)"
					+ "(rectangle 12,95,25,35)"
					, _painter.toString());
		}
		
		/**
		 * Test to perform a bounce movement off the bottom right corner and to
		 * ensure that the Shape's position and color after the movement is correct.
		 */
		@Test
		public void testShapeMoveWithBounceOffBottomAndRight() {
			DynamicShape shape = new DynamicShape(90, 90, 12, 15, 25, 35, Color.BLUE);
			shape.paint(_painter);
			shape.move(125, 135);
			shape.paint(_painter);
			shape.move(125, 135);
			shape.paint(_painter);
			assertEquals("(rectangle 90,90,25,35)"
					+ "(rectangle 100,100,25,35)"
					+ "(rectangle 88,85,25,35)"
					, _painter.toString());
		}
		
		
		/**
		 * Test to perform a bounce movement off the bottom right corner and to
		 * ensure that the Shape's position and color after the movement is correct.
		 */
		@Test
		public void testShapeMoveWithBounceOffBottomAndLeft() {
			DynamicShape shape = new DynamicShape(10, 90, -12, 15, 25, 35, Color.BLUE);
			shape.paint(_painter);
			shape.move(125, 135);
			shape.paint(_painter);
			shape.move(125, 135);
			shape.paint(_painter);
			assertEquals("(rectangle 10,90,25,35)"
					+ "(rectangle 0,100,25,35)"
					+ "(rectangle 12,85,25,35)"
					, _painter.toString());
		}
		
		/**
		 * Test to perform a bounce movement off the bottom right corner and to
		 * ensure that the Shape's position and color after the movement is correct.
		 */
		@Test
		public void testShapeMoveWithBounceOffTopAndLeft() {
			DynamicShape shape = new DynamicShape(10, 90, -12, 15, 25, 35, Color.BLUE);
			shape.paint(_painter);
			shape.move(125, 135);
			shape.paint(_painter);
			shape.move(125, 135);
			shape.paint(_painter);
			assertEquals("(rectangle 10,90,25,35)"
					+ "(rectangle 0,100,25,35)"
					+ "(rectangle 12,85,25,35)"
					, _painter.toString());
		}
		
		/**
		 * Test to perform a bounce movement off the bottom right corner and to
		 * ensure that the Shape's position after the movement is correct.
		 */
		@Test
		public void testShapeMoveWithBounceOffTopAndRight() {
			DynamicShape shape = new DynamicShape(10, 90, -12, 15, 25, 35, Color.BLUE);
			shape.paint(_painter);
			shape.move(125, 135);
			shape.paint(_painter);
			shape.move(125, 135);
			shape.paint(_painter);
			assertEquals("(rectangle 10,90,25,35)"
					+ "(rectangle 0,100,25,35)"
					+ "(rectangle 12,85,25,35)"
					, _painter.toString());
		}
}
