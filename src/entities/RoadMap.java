package entities;

public class RoadMap {
    private Long id;
    private Long userId;
    private String title;
    private String description;

    public RoadMap() {}

    public RoadMap(Long id, Long userId, String description, String title) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.title = title;
    }

    public RoadMap(Long userId, String description, String title) {
        this.userId = userId;
        this.description = description;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
