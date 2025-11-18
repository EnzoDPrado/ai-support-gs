package dto.user;

import entities.User;

public record AtualizarUsuarioInputDTO(
        String name,
        String email,
        String password
) {

    public User toUser(Long id) {
        return new User(
                id,
                this.name,
                this.email,
                this.password
        );
    }
}
