package dto.softSkills;

import entities.SoftSkills;

public record CriarSoftSkillInputDTO(
        String cdUser, // vem como string no JSON; converteremos para Long
        String name
) {
    /**
     * Converte para a entidade SoftSkills.
     * Converte cdUser (String) -> Long, porque sua entidade/dao usam Long para cdUser.
     */
    public SoftSkills toSoftSkill() {
        Long userId = null;
        if (this.cdUser != null && !this.cdUser.isBlank()) {
            userId = Long.valueOf(this.cdUser);
        }
        return new SoftSkills(
                userId,
                this.name
        );
    }
}
