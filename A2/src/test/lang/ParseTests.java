package lang;

import java.io.File;

import org.junit.Test;

public class ParseTests {
	/** Directory where the test input files are stored. */
	private static final File TEST_DIRECTORY = new File("testfiles/parser");

	@Test
	public void example2() {
		Util.testValidSyntax(TEST_DIRECTORY, "example2.in");
	}

	@Test
	public void numFunction() {
		Util.testSyntaxError(TEST_DIRECTORY, "numFunction.in");
	}

	@Test
	public void error() {
		Util.testSyntaxError(TEST_DIRECTORY, "error.in");
	}

	@Test
	public void multipleFunctionParamExtraComma() {
		Util.testSyntaxError(TEST_DIRECTORY, "multipleFunctionParamExtraComma.in");
	}

	@Test
	public void lessThanError() {
		Util.testSyntaxError(TEST_DIRECTORY, "lessThanError.in");
	}

	@Test
	public void multipleIdError() {
		Util.testSyntaxError(TEST_DIRECTORY, "multipleIdError.in");
	}
}
