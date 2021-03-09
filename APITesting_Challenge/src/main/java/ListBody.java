public class ListBody {
    private String name;
    private String description;
    private String language;
    public ListBody(String n,String d,String l){
        this.name=n;
        this.description=d;
        this.language=l;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getLanguage() {
        return language;
    }
}
