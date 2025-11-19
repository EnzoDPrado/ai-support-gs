package dto.curso;

import entities.Course;

public record AtualizarCursoInputDTO(
        Long userId,
        String name,
        Long hours,
        String description
) {

    public Course toEntity(Long courseId, Long userId) {
        return new Course(
                courseId,
                userId,
                name,
                hours,
                description
        );
    }
}
