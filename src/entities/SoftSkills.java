package entities;

public class SoftSkills {

    private String id;
    private Long cdUser;
    private String name;

    public SoftSkills() {}

    public SoftSkills(String id, Long cdUser, String name) {
        this.id = id;
        this.cdUser = cdUser;
        this.name = name;
    }

    public SoftSkills(Long cdUser, String name) {
        this.cdUser = cdUser;
        this.name = name;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Long getCdUser() { return cdUser; }
    public void setCdUser(Long cdUser) { this.cdUser = cdUser; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}

