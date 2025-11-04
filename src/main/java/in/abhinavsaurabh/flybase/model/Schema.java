package in.abhinavsaurabh.flybase.model;

import java.util.List;

public class Schema {

    private String schema;
    private List<Table> tables;

    public String getSchema() { return schema; }
    public void setSchema(String schema) { this.schema = schema; }

    public List<Table> getTables() { return tables; }
    public void setTables(List<Table> tables) { this.tables = tables; }
}
