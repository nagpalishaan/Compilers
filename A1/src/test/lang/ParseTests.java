package lang;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.Assert.fail;

public class ParseTests {
	/** Directory where the test input files are stored. */
	private static final File TEST_DIRECTORY = new File("testfiles");

	@Test public void identifier() {
		Util.testValidSyntax(TEST_DIRECTORY, "identifier.in");
	}

	@Test public void let() {
		Util.testValidSyntax(TEST_DIRECTORY, "let.in");
	}

	@Test public void numerical() {
		Util.testValidSyntax(TEST_DIRECTORY, "numerical.in");
	}

	@Test public void product() {
		Util.testValidSyntax(TEST_DIRECTORY, "product.in");
	}

	@Test public void error() {
		Util.testSyntaxError(TEST_DIRECTORY, "error.in");
	}

	@Test public void error2() {
		Util.testSyntaxError(TEST_DIRECTORY, "error2.in");
	}

	@Test public void error3() {
		Util.testSyntaxError(TEST_DIRECTORY, "error3.in");
	}

	@Test public void error4() {
		Util.testSyntaxError(TEST_DIRECTORY, "error4.in");
	}
}
