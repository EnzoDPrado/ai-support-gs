package dto.curso;

import entities.Course;

public record CriarCursoInputDTO(
        Long userId,
        String name,
        Long hours,
        String description
) {

    public Course toEntity() {
        return new Course(
                userId,
                name,
                hours,
                description
        );
    }
}
