package algsex.support;

public class OutputInterceptor implements Out {
    public boolean verbose = System.getenv("VERBOSE") != null;
    public StringBuffer contents = new StringBuffer();

    public void print(Object s) {
        contents.append(s);
        if (verbose) System.out.print(s);
    }

    public void println(Object o) {
        println(o.toString());
    }

    public void println(String s) {
        contents.append(s);
        contents.append("\n");
        if (verbose) System.out.println(s);
    }

    public void printf(String s, Object... args) {
        contents.append(String.format(s, args));
        if (verbose) System.out.printf(s, args);
    }

    public String contents() { return contents.toString(); }

    public void reset() { contents.setLength(0); }
}