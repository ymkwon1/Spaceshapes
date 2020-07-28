package spaceshapes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestHexagonShape {
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
	 * Test to check a HexagonShape is made correctly.
	 */
	@Test
	public void testMakeHexagon() {
		HexagonShape shape = new HexagonShape(100, 20, 12, 15, 60, 60);
		shape.paint(_painter);
		assertEquals("(line 100,50,120,20)(line 120,20,140,20)(line 140,20,160,50)(line 160,50,140,80)(line 140,80,120,80)(line 120,80,100,50)", 
				_painter.toString());
	}

	/**
	 * Test to check a small HexagonShape is made correctly.
	 */
	@Test
	public void testMakeHexagonSmall() {
		HexagonShape shape = new HexagonShape(100, 20, 12, 15, 20, 20);
		shape.paint(_painter);
		assertEquals("(line 100,30,110,20)(line 110,20,120,30)(line 120,30,110,40)(line 110,40,100,30)", 
				_painter.toString());
	}

	
	/**
	 * Test to perform a simple (non-bouncing) movement, and to ensure that a
	 * Shape's position after the movement is correct.
	 */
	@Test
	public void testSimpleMove() {
		HexagonShape shape = new HexagonShape(100, 20, 12, 15, 60, 60);
		shape.paint(_painter);
		shape.move(500, 500);
		shape.paint(_painter);
		assertEquals("(line 100,50,120,20)(line 120,20,140,20)(line 140,20,160,50)(line 160,50,140,80)(line 140,80,120,80)(line 120,80,100,50)"
				+ "(line 112,65,132,35)(line 132,35,152,35)(line 152,35,172,65)(line 172,65,152,95)(line 152,95,132,95)(line 132,95,112,65)"
				, _painter.toString());
	}

	/**
	 * Test to perform a simple (non-bouncing) movement, and to ensure that a
	 * Shape's position after the movement is correct for a small Hexagon.
	 */
	@Test
	public void testSimpleMoveSmall() {
		HexagonShape shape = new HexagonShape(100, 20, 12, 15, 20, 20);
		shape.paint(_painter);
		shape.move(500, 500);
		shape.paint(_painter);
		assertEquals("(line 100,30,110,20)(line 110,20,120,30)(line 120,30,110,40)(line 110,40,100,30)" 
				+ "(line 112,45,122,35)(line 122,35,132,45)(line 132,45,122,55)(line 122,55,112,45)"
				, _painter.toString());
	}

	
	/**
	 * Test to perform a bounce movement off the right-most boundary and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffRight() {
		HexagonShape shape = new HexagonShape(100, 20, 12, 15, 60, 60);
		shape.paint(_painter);
		shape.move(170, 10000);
		shape.paint(_painter);
		shape.move(170, 10000);
		shape.paint(_painter);
		assertEquals("(line 100,50,120,20)(line 120,20,140,20)(line 140,20,160,50)(line 160,50,140,80)(line 140,80,120,80)(line 120,80,100,50)"
				+ "(line 110,65,130,35)(line 130,35,150,35)(line 150,35,170,65)(line 170,65,150,95)(line 150,95,130,95)(line 130,95,110,65)"
				+ "(line 98,80,118,50)(line 118,50,138,50)(line 138,50,158,80)(line 158,80,138,110)(line 138,110,118,110)(line 118,110,98,80)"
				, _painter.toString());
	}
	/**
	 * Test to perform a bounce movement off the right-most boundary and to
	 * ensure that the Shape's position after the movement is correct for a small hexagon.
	 */
	@Test
	public void testShapeMoveWithBounceOffRightSmall() {
		HexagonShape shape = new HexagonShape(100, 20, 12, 15, 20, 20);
		shape.paint(_painter);
		shape.move(125, 10000);
		shape.paint(_painter);
		shape.move(125, 10000);
		shape.paint(_painter);
		assertEquals("(line 100,30,110,20)(line 110,20,120,30)(line 120,30,110,40)(line 110,40,100,30)"
				+ "(line 105,45,115,35)(line 115,35,125,45)(line 125,45,115,55)(line 115,55,105,45)"
				+ "(line 93,60,103,50)(line 103,50,113,60)(line 113,60,103,70)(line 103,70,93,60)"
				, _painter.toString());
	}

	/**
	 * Test to perform a bounce movement off the left-most boundary and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffLeft() {
		HexagonShape shape = new HexagonShape(10, 20, -12, 15, 60 ,60);
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		//System.out.println(_painter.toString());
		assertEquals("(line 10,50,30,20)(line 30,20,50,20)(line 50,20,70,50)(line 70,50,50,80)(line 50,80,30,80)(line 30,80,10,50)"
				+ "(line 0,65,20,35)(line 20,35,40,35)(line 40,35,60,65)(line 60,65,40,95)(line 40,95,20,95)(line 20,95,0,65)"
				+ "(line 12,80,32,50)(line 32,50,52,50)(line 52,50,72,80)(line 72,80,52,110)(line 52,110,32,110)(line 32,110,12,80)"
				, _painter.toString());
	}
	/**
	 * Test to perform a bounce movement off the left-most boundary and to
	 * ensure that the Shape's position after the movement is correct for a small hexagon.
	 */
	@Test
	public void testShapeMoveWithBounceOffLeftSmall() {
		HexagonShape shape = new HexagonShape(10, 20, -12, 15, 20, 20);
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		//System.out.println(_painter.toString());
		assertEquals("(line 10,30,20,20)(line 20,20,30,30)(line 30,30,20,40)(line 20,40,10,30)"
				+ "(line 0,45,10,35)(line 10,35,20,45)(line 20,45,10,55)(line 10,55,0,45)"
				+ "(line 12,60,22,50)(line 22,50,32,60)(line 32,60,22,70)(line 22,70,12,60)"
				, _painter.toString());
	}

	/**
	 * Test to perform a bounce movement off the bottom right corner and to
	 * ensure that the Shape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffBottomAndLeft() {
		HexagonShape shape = new HexagonShape(10, 90, -12, 15, 60, 60);
		shape.paint(_painter);
		shape.move(125, 160);
		shape.paint(_painter);
		shape.move(125, 160);
		shape.paint(_painter);
		assertEquals("(line 10,120,30,90)(line 30,90,50,90)(line 50,90,70,120)(line 70,120,50,150)(line 50,150,30,150)(line 30,150,10,120)"
				+ "(line 0,130,20,100)(line 20,100,40,100)(line 40,100,60,130)(line 60,130,40,160)(line 40,160,20,160)(line 20,160,0,130)"
				+ "(line 12,115,32,85)(line 32,85,52,85)(line 52,85,72,115)(line 72,115,52,145)(line 52,145,32,145)(line 32,145,12,115)"
				, _painter.toString());
	}
	/**
	 * Test to perform a bounce movement off the bottom right corner and to
	 * ensure that the Shape's position after the movement is correct for a small hexagon.
	 */
	@Test
	public void testShapeMoveWithBounceOffBottomAndLeftSmall() {
		HexagonShape shape = new HexagonShape(10, 90, -12, 15, 20, 20);
		shape.paint(_painter);
		shape.move(125, 125);
		shape.paint(_painter);
		shape.move(125, 125);
		shape.paint(_painter);
		assertEquals("(line 10,100,20,90)(line 20,90,30,100)(line 30,100,20,110)(line 20,110,10,100)"
				+ "(line 0,115,10,105)(line 10,105,20,115)(line 20,115,10,125)(line 10,125,0,115)"
				+ "(line 12,100,22,90)(line 22,90,32,100)(line 32,100,22,110)(line 22,110,12,100)"
				, _painter.toString());
	}
}
