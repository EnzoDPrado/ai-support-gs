package dto.softSkills;

import entities.SoftSkills;

public record CriarSoftSkillInputDTO(
        String cdUser,
        String name
) {
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
