package in.abhinavsaurabh.flybase.generator;

import in.abhinavsaurabh.flybase.model.Column;
import in.abhinavsaurabh.flybase.model.Table;

import java.util.List;
import java.util.stream.Collectors;

public class SqlGenerator {

    public static String generateCreateTable(Table table) {

        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ").append(table.getName()).append(" (\n");

        for (Column col : table.getColumns()) {
            sb.append("  ").append(col.getName())
                    .append(" ").append(col.getType());

            if (col.isPrimaryKey()) sb.append(" PRIMARY KEY");
            if (col.isUnique()) sb.append(" UNIQUE");
            if (col.getForeignKey() != null) {
                sb.append(" REFERENCES ").append(col.getForeignKey().getReferences());
            }
            sb.append(",\n");
        }

        sb.setLength(sb.length() - 2); // Removing last comma
        sb.append("\n);");
        return sb.toString();
    }

    public static String generateAlterTableAddColumns(Table table, List<Column> newColumns) {
        StringBuilder sb = new StringBuilder();
        sb.append("ALTER TABLE ").append(table.getName()).append("\n");

        // Append each column with its constraints
        for (int i = 0; i < newColumns.size(); i++) {
            Column col = newColumns.get(i);
            sb.append("  ADD COLUMN ").append(col.getName())
                    .append(" ").append(col.getType());

            if (col.isUnique()) sb.append(" UNIQUE");
            if (col.isPrimaryKey()) sb.append(" PRIMARY KEY");
            if (col.getForeignKey() != null) {
                sb.append(" REFERENCES ").append(col.getForeignKey().getReferences());
            }

            // Add comma if not last
            if (i < newColumns.size() - 1) {
                sb.append(",\n");
            }
        }

        sb.append(";");
        return sb.toString();
    }

    public static String generateInsert(Table table) {

        String colNames = table.getColumns().stream()
                .map(Column::getName)
                .collect(Collectors.joining(", "));

        String placeholders = table.getColumns().stream()
                .map(c -> "?")
                .collect(Collectors.joining(", "));

        return String.format("INSERT INTO %s (%s) VALUES (%s);",
                table.getName(), colNames, placeholders);
    }

    public static String generateUpdate(Table table, String whereColumn) {

        String setClause = table.getColumns().stream()
                .filter(c -> !c.isPrimaryKey())
                .map(c -> c.getName() + " = ?")
                .collect(Collectors.joining(", "));

        return String.format("UPDATE %s SET %s WHERE %s = ?;",
                table.getName(), setClause, whereColumn);
    }

    public static String generateDelete(Table table, String whereColumn) {

        return String.format("DELETE FROM %s WHERE %s = ?;", table.getName(), whereColumn);
    }

    public static String generateJoin(
            Table left,
            Table right,
            String leftCol,
            String rightCol,
            String joinType
    ) {
        return String.format(
                "SELECT * FROM %s %s JOIN %s ON %s.%s = %s.%s;",
                left.getName(),
                joinType.toUpperCase(),
                right.getName(),
                left.getName(), leftCol,
                right.getName(), rightCol
        );
    }
}
