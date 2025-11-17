package dto.investidor;

import entities.Investidor;

import java.sql.Date;

public record CreateInvestidorInputDTO(
        String cpf,
        Double saldo,
        Date dataNasc
) {

    public Investidor toInvestidor(){
        return new Investidor(
            this.cpf,
            this.saldo,
            this.dataNasc
        );
    }
}
