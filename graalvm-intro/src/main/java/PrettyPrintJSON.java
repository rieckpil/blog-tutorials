import java.io.*;
import java.util.stream.*;
import org.graalvm.polyglot.*;

public class PrettyPrintJSON {

  public static void main(String[] args) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String input = reader.lines().collect(Collectors.joining(System.lineSeparator()));
    try (Context context = Context.create("js")) {
      Value parse = context.eval("js", "JSON.parse");
      Value stringify = context.eval("js", "JSON.stringify");
      Value result = stringify.execute(parse.execute(input));
      System.out.println(result.asString());
    }
  }
}
