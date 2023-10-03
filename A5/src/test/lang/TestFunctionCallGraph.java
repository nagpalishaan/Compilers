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

/**
 * This is a parameterized test: one test case is generated for each input
 * file found in TEST_DIRECTORY. Input files should have the ".in" extension.
 */
@RunWith(Parameterized.class)
public class TestFunctionCallGraph {
	/** Directory where the test input files are stored. */
	private static final File TEST_DIRECTORY = new File("testfiles/functioncallgraph");

	private final String filename;
	public TestFunctionCallGraph(String testFile) {
		filename = testFile;
	}

	@Test public void runTest() throws Exception {
		Program program = (Program) Util.parse(new File(TEST_DIRECTORY, filename));
		StringBuilder sb = new StringBuilder();
		for (ErrorMessage m : program.errors()) {
			sb.append(m).append("\n");
		}

		// if (program.errors().size() == 0) {
		// 	List<Function> functions = program.getFunctionList();
		// 	for (Function f : functions){
		// 		// Function calls
		// 		for (Function cf : f.functionCalls()){
		// 			sb.append(f.getIdDecl().getID()).append(" calls ").append(cf.getIdDecl().getID()).append("\n");
		// 		}
		// 	}
		// 	for (Function f : functions){
		// 		sb.append(f.getIdDecl().getID()).append("\n");
		// 	}
		// }

		sb.append("FUNCTION CALLS:\n");
		for (Function f : program.getFunctionList()){
			for (Function cf : f.functionCalls()){
				sb.append(f.getIdDecl().getID()).append(" calls ").append(cf.getIdDecl().getID()).append("\n");
			}
		}
		sb.append("\nFUNCTION DECLARATIONS:\n");
		for (Function f : program.getFunctionList()){
			sb.append(f.getIdDecl().getID()).append(";\n");
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
