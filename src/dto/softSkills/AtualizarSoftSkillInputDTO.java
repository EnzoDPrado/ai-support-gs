package dto.softSkills;

import entities.SoftSkills;

public record AtualizarSoftSkillInputDTO(
        String name
) {
    /**
     * Monta a entidade para update.
     * Recebe id (String) e cdUser (Long) — normalmente você vai buscar o cdUser existente no DB
     * e passar aqui para construir a entidade completa.
     */
    public SoftSkills toSoftSkill(String id, Long cdUser) {
        return new SoftSkills(
                id,
                cdUser,
                this.name
        );
    }
}
