package in.abhinavsaurabh.flybase;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;

import in.abhinavsaurabh.flybase.generator.SqlGenerator;
import in.abhinavsaurabh.flybase.model.Schema;
import in.abhinavsaurabh.flybase.parser.YamlSchemaParser;

@Command(name = "flybase", mixinStandardHelpOptions = true, version = "1.0",
        description = "Generates SQL from YAML schema files")
public class Main implements Runnable {

    @Option(names = {"-f", "--file"}, description = "YAML schema file", required = true)
    private File file;

    @Option(names = {"-o", "--operation"}, description = "Operation: create, drop", required = true)
    private String operation;

    public void run() {
        Schema schema = YamlSchemaParser.parse(file);

        switch (operation.toLowerCase()) {
            case "create":
                schema.getTables().forEach(t ->
                        System.out.println(SqlGenerator.generateCreateTable(t) + "\n"));
                break;
            case "drop":
                schema.getTables().forEach(t ->
                        System.out.println("DROP TABLE IF EXISTS " + t.getName() + ";\n"));
                break;
            case "insert":
                schema.getTables().forEach(t ->
                        System.out.println(SqlGenerator.generateAlterTableAddColumns(t, t.getColumns()) + "\n"));
                break;
            default:
                System.out.println("Unsupported operation: " + operation);
        }
    }

    public static void main(String[] args) {

        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }
}
