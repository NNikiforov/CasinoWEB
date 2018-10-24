package by.epam.casino.domain;

/**
 * Allowed result type.
 *
 */
public enum ResultType {
	/**
	 * BIG WIN
	 */
	BIGWIN("BIG WIN!", 1.5),
	/**
	 * WIN
	 */
	WIN("WIN!", 1),
	/**
	 * LOSING
	 */
	LOSE("LOSING!", -1),
	/**
	 * SMALL LOSINIG
	 */
	SLOSE("SMALL LOSINIG!", -0.5);
	/**
	 * Text
	 */
	private String text;
	/**
	 * coefficient for calculating profit.
	 */
	private double coefficient;

	/**
	 * Constructor.
	 * 
	 * @param TEXT        text
	 * @param COEFFICIENT coefficient
	 */
	ResultType(final String TEXT, final double COEFFICIENT) {
		text = TEXT;
		coefficient = COEFFICIENT;
	}

	/**
	 * Getter text.
	 * 
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Getter coefficient.
	 * 
	 * @return Coefficient
	 */
	public double getCoefficient() {
		return coefficient;
	}
}
