package net.jordanlabs.fixclean;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.io.File;
import java.nio.file.Files;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

@Command(
    name = "fixclean",
    mixinStandardHelpOptions = true,
    version = "fixclean 1.0",
    description = "Extracts FIX messages from text files."
)
public class FixClean implements Callable<Integer> {

    @Parameters(index = "0", description = "File to extract the FIX messages")
    private File inputFile;

    @Option(names = {"-f", "--file"}, paramLabel = "FILE", description = "Output file")
    private File outputFile;

    @Option(names = {"-d", "--delimiter"}, description = "FIX message field delimiter")
    private String delimiter;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new FixClean()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        try (Stream<String> fileLines = Files.lines(inputFile.toPath())) {
            fileLines.forEach(System.out::println);
        }
        return 0;
    }
}
