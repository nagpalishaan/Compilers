package lang;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import lang.ast.ErrorMessage;
import lang.ast.Program;
import lang.ast.Function;
import lang.ast.FunctionCall;
import lang.ast.List;

import java.util.Set;


/**
 * This is a parameterized test: one test case is generated for each input
 * file found in TEST_DIRECTORY. Input files should have the ".in" extension.
 */
@RunWith(Parameterized.class)
public class TestReachability {
	/** Directory where the test input files are stored. */
	private static final File TEST_DIRECTORY = new File("testfiles/reachability");

	private final String filename;
	public TestReachability(String testFile) {
		filename = testFile;
	}

	@Test public void runTest() throws Exception {
		Program program = (Program) Util.parse(new File(TEST_DIRECTORY, filename));
		StringBuilder sb = new StringBuilder();
		for (ErrorMessage m : program.errors()) {
			sb.append(m).append("\n");
		}

		sb.append("REACHABILITY:\n\n");
		for (Function f : program.getFunctionList()){
			sb.append("Function: ").append(f.getIdDecl().getID()).append("\n");

            Set<Function> reachable = f.reachable();
            for (Function r : reachable){
                if (f.getIdDecl().getID() == r.getIdDecl().getID()){
                    sb.append("this function is circular\n");
                }
                else{
                    sb.append(r.getIdDecl().getID()).append("\n");
                }
            }
            sb.append("\n");
		}

		String actual = sb.toString();
		Util.compareOutput(actual,
				new File(TEST_DIRECTORY, Util.changeExtension(filename, ".out")),
				new File(TEST_DIRECTORY, Util.changeExtension(filename, ".expected")));
	}

	@Parameters(name = "{0}")
	public static Iterable<Object[]> getTests() {
		return Util.getTestParameters(TEST_DIRECTORY, ".in");
	}
}
