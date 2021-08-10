package DTO;

public class Category {

    private String id;
    private String name;

    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return String.format("%s;%s", id, name);
    }

    public boolean equals(Object category) {
        return this.getId().equals(((Category) category).getId())
                || this.getName().equals(((Category) category).getName());

    }

}
