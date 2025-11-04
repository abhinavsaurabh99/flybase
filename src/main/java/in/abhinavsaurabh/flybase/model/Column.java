package in.abhinavsaurabh.flybase.model;

public class Column {

    private String name;
    private String type;
    private boolean primaryKey;
    private boolean unique;
    private ForeignKey foreignKey;

    public static class ForeignKey {

        private String references;

        public String getReferences() { return references; }
        public void setReferences(String references) { this.references = references; }
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public boolean isPrimaryKey() { return primaryKey; }
    public void setPrimaryKey(boolean primaryKey) { this.primaryKey = primaryKey; }

    public boolean isUnique() { return unique; }
    public void setUnique(boolean unique) { this.unique = unique; }

    public ForeignKey getForeignKey() { return foreignKey; }
    public void setForeignKey(ForeignKey foreignKey) { this.foreignKey = foreignKey; }
}
