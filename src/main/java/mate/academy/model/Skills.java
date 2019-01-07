package mate.academy.model;

public class Skills {
    private  int id;
    private  String language;
    private  String level;

    public Skills() {
    }

    public Skills(String language, String level) {
        this.language = language;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
