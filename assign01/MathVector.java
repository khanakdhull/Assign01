package assign01;

/**
 * This class represents a simple row or column vector of numbers. In a row
 * vector, the numbers are written horizontally (i.e., along the columns). In a
 * column vector, the numbers are written vertically (i.e., along the rows).
 * 
 * @author CS 2420 course staff and Khanak Dhull
 * @version August 22, 2024
 */
public class MathVector {

	// 2D array to hold the numbers of the vector, either along the columns or rows
	private double[][] data;
	// set to true for a row vector and false for a column vector
	private boolean isRowVector;
	// count of elements in the vector
	private int vectorSize;

	/**
	 * Creates a new row or column vector. For a row vector, the input array is
	 * expected to have 1 row and a positive number of columns, and this number of
	 * columns represents the vector's length. For a column vector, the input array
	 * is expected to have 1 column and a positive number of rows, and this number
	 * of rows represents the vector's length.
	 * 
	 * @param data - a 2D array to hold the numbers of the vector
	 * @throws IllegalArgumentException if the numbers of rows and columns in the
	 *                                  input 2D array is not compatible with a row
	 *                                  or column vector
	 */
	public MathVector(double[][] data) {
		if (data.length == 0)
			throw new IllegalArgumentException("Number of rows must be positive.");
		if (data[0].length == 0)
			throw new IllegalArgumentException("Number of columns must be positive.");

		if (data.length == 1) {
			// This is a row vector with length = number of columns.
			this.isRowVector = true;
			this.vectorSize = data[0].length;
		} else if (data[0].length == 1) {
			for (int i = 1; i < data.length; i++)
				if (data[i].length != 1)
					throw new IllegalArgumentException("For each row, the number of columns must be 1.");
			// This is a column vector with length = number of rows.
			this.isRowVector = false;
			this.vectorSize = data.length;
		} else
			throw new IllegalArgumentException("Either the number of rows or the number of columns must be 1.");

		// Create the array and copy data over.
		if (this.isRowVector)
			this.data = new double[1][vectorSize];
		else
			this.data = new double[vectorSize][1];
		for (int i = 0; i < this.data.length; i++) {
			for (int j = 0; j < this.data[0].length; j++) {
				this.data[i][j] = data[i][j];
			}
		}
	}

	/**
	 * Generates a textual representation of this vector.
	 * 
	 * For example, "1.0 2.0 3.0 4.0 5.0" for a sample row vector of length 5 and
	 * "1.0\n2.0\n3.0\n4.0\n5.0" for a sample column vector of length 5. In both
	 * cases, notice the lack of a newline or space after the last number.
	 * 
	 * @return textual representation of this vector
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		if (this.isRowVector) {
			for (int j = 0; j < this.vectorSize; j++)
			// This is for row vectors
			{
				result.append(this.data[0][j]);
				if (j < this.vectorSize - 1) {
					result.append("");
				}
			}
		} else {
			for (int i = 0; i < this.vectorSize; i++)
			// This is for column vectors
			{
				result.append(this.data[i][0]);
				if (i < this.vectorSize - 1) {
					result.append("\n");
				}
			}
		}
		return result.toString();
	}

	/**
	 * Determines whether this vector is "equal to" another vector, where equality
	 * is defined as both vectors being row (or both being column), having the same
	 * vector length, and containing the same numbers in the same positions.
	 * 
	 * @param other - another vector to compare
	 * @return true if this vector is equivalent to other, false otherwise
	 */
	public boolean equals(Object other) {
		if (!(other instanceof MathVector))
			return false;

		MathVector otherVec = (MathVector) other;

		if (this.isRowVector != otherVec.isRowVector) {
			return false;
			// If one vector is a row and another is a column, then they are not equal.
		}

		if (this.vectorSize != otherVec.vectorSize) {
			return false;
			// If one vector is not the same as the other, then they are not equal.
		}

		for (int i = 0; i < this.data.length; i++) {
			for (int j = 0; j < this.data[0].length; j++) {
				if (this.data[i][j] != otherVec.data[i][j]) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Updates this vector by using a given scaling factor to multiply each entry.
	 * 
	 * @param factor - the scaling factor
	 */
	public void scale(double factor) {
		for (int i = 0; i < this.data.length; i++) {
			for (int j = 0; j < this.data[0].length; j++) {
				this.data[i][j] *= factor;
			}
		}

	}

	/**
	 * Generates a new vector that is the transposed version of this vector.
	 * 
	 * @return transposed version of this vector
	 */
	public MathVector transpose() {
		double[][] transposedData;

		if (this.isRowVector) {
			transposedData = new double[this.vectorSize][1];
			// Converts a row vector into a column vector

			for (int i = 0; i < this.vectorSize; i++) {
				transposedData[i][0] = this.data[0][i];
			}
		} else {
			transposedData = new double[1][this.vectorSize];
			// Converts a row column into a row vector
			for (int i = 0; i < this.vectorSize; i++) {
				transposedData[0][i] = this.data[i][0];
			}
		}
		return new MathVector(transposedData);
	}

	/**
	 * Generates a new vector representing the sum of this vector and another
	 * vector.
	 * 
	 * @param other - another vector to be added to this vector
	 * @throws IllegalArgumentException if the other vector and this vector are not
	 *                                  both row vectors of the same length or
	 *                                  column vectors of the same length
	 * @return sum of this vector and other
	 */
	public MathVector add(MathVector other) {
		if (this.isRowVector != other.isRowVector) {
			throw new IllegalArgumentException("All vectors need to be the same size and type.");
		}

		if (this.vectorSize != other.vectorSize) {
			throw new IllegalArgumentException("All vectors need to be the same size and type.");
		}
		double[][] sumData;
		if (this.isRowVector) {
			sumData = new double[1][this.vectorSize];
			for (int j = 0; j < this.vectorSize; j++) {
				sumData[0][j] = this.data[0][j] + other.data[0][j];
			}
		} else {
			sumData = new double[this.vectorSize][1];
			for (int i = 0; i < this.vectorSize; i++) {
				sumData[i][0] = this.data[i][0] + other.data[i][0];
			}
		}
		return new MathVector(sumData);
	}

	/**
	 * Computes the dot product of this vector and another vector.
	 * 
	 * @param other - another vector to be combined with this vector to produce the
	 *              dot product
	 * @throws IllegalArgumentException if the other vector and this vector are not
	 *                                  both row vectors of the same length or
	 *                                  column vectors of the same length
	 * @return dot product of this vector and other
	 */
	public double dotProduct(MathVector other) {
		if (this.isRowVector != other.isRowVector) {
			throw new IllegalArgumentException("All vectors need to be the same size and type.");
		}
		if (this.vectorSize != other.vectorSize) {
			throw new IllegalArgumentException("All vectors need to be the same size and type.");
		}

		double dotProduct = 0.0;

		if (this.isRowVector) {
			for (int j = 0; j < this.vectorSize; j++) {
				dotProduct += this.data[0][j] * other.data[0][j];
			}
		} else {
			for (int i = 0; i < this.vectorSize; i++) {
				dotProduct += this.data[i][0] * other.data[i][0];
			}
		}
		return dotProduct;
	}

	/**
	 * Computes this vector's magnitude (also known as a vector's length).
	 * 
	 * @return magnitude of this vector
	 */
	public double magnitude() {
		double squared = 0.0;
		for (int i = 0; i < this.data.length; i++) {
			for (int j = 0; j < this.data[0].length; j++) {
				squared += this.data[i][j] * this.data[i][j];
			}
		}

		return Math.sqrt(squared);
	}

	/**
	 * Updates this vector by using standardizing it (AKA normalization).
	 */
	public void normalize() {
		double magnitude = this.magnitude();
		if (magnitude == 0) {
			throw new ArithmeticException("Cannot normalize a zero vector.");
		}

		this.scale(1 / magnitude);
	}
}