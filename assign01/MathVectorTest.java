package assign01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This tester class assesses the correctness of the MathVector class.
 * 
 * IMPORTANT NOTE: The tests provided to get you started rely heavily on a
 * correctly implemented equals method. Be careful of false positives (i.e.,
 * tests that pass because your equals method incorrectly returns true).
 * 
 * @author CS 2420 course staff and Khanak Dhull
 * @version August 22, 2024
 */
public class MathVectorTest {

	private MathVector rowVec, unitVec, colVec;

	@BeforeEach
	public void setUp() throws Exception {
		// Creates a row vector with three elements: 3.0, 1.0, 2.0
		rowVec = new MathVector(new double[][] { { 3, 1, 2 } });

		// Creates a row vector with three elements: 1.0, 1.0, 1.0
		unitVec = new MathVector(new double[][] { { 1, 1, 1 } });

		// Creates a column vector with five elements: -11.0, 2.5, 36.0, -3.4, 7.1
		colVec = new MathVector(new double[][] { { -11 }, { 2.5 }, { 36 }, { -3.4 }, { 7.1 } });
	}

	@Test
	public void createVectorFromMatrix() {
		double arr[][] = { { 1, 2 }, { 3, 4 } };
		assertThrows(IllegalArgumentException.class, () -> {
			new MathVector(arr);
		});
		// NOTE: The code above is an example of a lambda expression. See Lab 1 for more
		// info.
	}

	@Test
	public void createVectorFromJaggedArray() {
		double arr[][] = { { 1 }, { 2 }, { 3 }, { 4, 5 }, { 6 }, { 7 } };
		assertThrows(IllegalArgumentException.class, () -> {
			new MathVector(arr);
		});
	}

	@Test
	public void smallColVectorToString() {
		String expected = "-11.0\n2.5\n36.0\n-3.4\n7.1";
		assertEquals(expected, colVec.toString());
	}

	@Test
	public void smallRowVectorEquality() {
		assertTrue(rowVec.equals(new MathVector(new double[][] { { 3, 1, 2 } })));
	}

	@Test
	public void smallRowVectorInequality() {
		assertFalse(rowVec.equals(unitVec));
	}

	@Test
	public void scaleSmallColVector() {
		MathVector expected = new MathVector(new double[][] { { -27.5 }, { 6.25 }, { 90 }, { -8.5 }, { 17.75 } });
		colVec.scale(2.5);
		assertTrue(expected.equals(colVec));
	}

	@Test
	public void transposeSmallRowVector() {
		MathVector expected = new MathVector(new double[][] { { 3 }, { 1 }, { 2 } });
		assertTrue(expected.equals(rowVec.transpose()));
	}

	@Test
	public void addRowAndColVectors() {
		assertThrows(IllegalArgumentException.class, () -> {
			rowVec.add(colVec);
		});
		// NOTE: The code above is an example of a lambda expression. See Lab 1 for more
		// info.
	}

	@Test
	public void addSmallRowVectors() {
		MathVector expected = new MathVector(new double[][] { { 4, 2, 3 } });
		assertTrue(expected.equals(rowVec.add(unitVec)));
	}

	@Test
	public void dotProductSmallRowVectors() {
		assertEquals(3.0 * 1.0 + 1.0 * 1.0 + 2.0 * 1.0, rowVec.dotProduct(unitVec));
	}

	@Test
	public void smallRowVectorMagnitude() {
		assertEquals(Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0), rowVec.magnitude());
	}

	@Test
	public void smallRowVectorNormalize() {
		double length = Math.sqrt(3.0 * 3.0 + 1.0 * 1.0 + 2.0 * 2.0);
		MathVector expected = new MathVector(new double[][] { { 3.0 / length, 1.0 / length, 2.0 / length } });
		rowVec.normalize();
		assertTrue(expected.equals(rowVec));
	}

	@Test
	public void emptyVectors() {
		double[][] emptyArray = new double[][] {};
		assertThrows(IllegalArgumentException.class, () -> {
			new MathVector(emptyArray);
		});
	}

	@Test
	public void addVectorsOfDifferentType() {
		assertThrows(IllegalArgumentException.class, () -> {
			rowVec.add(colVec);
		});
	}

	@Test
	public void differentSizes() {
		MathVector vec1 = new MathVector(new double[][] { { 1, 2 } });
		MathVector vec2 = new MathVector(new double[][] { { 1, 2, 3 } });
		assertThrows(IllegalArgumentException.class, () -> {
			vec1.dotProduct(vec2);
		});

	}

	@Test
	public void negativeFactors() {
		MathVector vec = new MathVector(new double[][] { { 1, 2, 3 } });
		MathVector expected = new MathVector(new double[][] { { -1, -2, -3 } });
		vec.scale(-1);
		assertTrue(expected.equals(vec));

	}

	@Test
	public void differentTypeSameSize() {
		assertFalse(rowVec.equals(colVec));
	}

	@Test
	public void DifferentVectors() {
		double[][] d1 = { { 4, 5 } };
		double[][] d2 = { { 6, 7 } };
		MathVector vec1 = new MathVector(d1);
		MathVector vec2 = new MathVector(d2);

		assertFalse(vec1.equals(vec2));

	}

}