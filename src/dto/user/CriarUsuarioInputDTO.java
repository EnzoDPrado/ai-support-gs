package dto.user;

import entities.User;

public record CriarUsuarioInputDTO(
        String name,
        String email,
        String password
) {

    public User toUser() {
        return new User(
                this.name,
                this.email,
                this.password
        );
    }
}
