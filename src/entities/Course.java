package entities;

public class Course {

    private Long id;
    private Long userId;
    private String name;
    private Long hours;
    private String description;


    public Course(){}

    public Course(Long userId, String name, Long hours, String description) {
        this.userId = userId;
        this.name = name;
        this.hours = hours;
        this.description = description;
    }

    public Course(Long id, Long userId, String name, Long hours, String description) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.hours = hours;
        this.description = description;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getHours() {
        return hours;
    }

    public void setHours(Long hours) {
        this.hours = hours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
