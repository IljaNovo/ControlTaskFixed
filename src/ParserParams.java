import org.apache.commons.cli.*;

import java.util.HashMap;
import java.util.Map;

public class ParserParams {

    public ParserParams() { }

    private Option createOption(String opt, String description) {
        Option newOption = new Option(opt, true, description);
        newOption.setOptionalArg(false);

        return newOption;
    }

    public Map<String, String> parseWriteRead(String[] commandLineArguments) throws ParseCommandLineException {
        if (commandLineArguments == null) {
            throw new ParseCommandLineException();
        }
        Options posixOptions = new Options();

        posixOptions.addOption(createOption("w", "Write"));
        posixOptions.addOption(createOption("r", "Read"));

        CommandLineParser cmdLinePosixParser = new PosixParser();
        CommandLine commandLine = null;
        Map<String, String> valuesParams = new HashMap<>();

        try {
            commandLine = cmdLinePosixParser.parse(posixOptions, commandLineArguments);

            if (commandLine.hasOption("w") && commandLine.hasOption("r")) {
                valuesParams.put("Read", commandLine.getOptionValue("r"));
                valuesParams.put("Write", commandLine.getOptionValue("w"));
            }
            else {
                throw new ParseCommandLineException();
            }
        }
        catch (Throwable e) {
            throw new ParseCommandLineException();
        }
        return valuesParams;
    }
}
