package interfaces;

import java.math.BigDecimal;

public interface IOperacaoTransacao {

    void investir(BigDecimal valor);

    void sacar(BigDecimal valor);
}
