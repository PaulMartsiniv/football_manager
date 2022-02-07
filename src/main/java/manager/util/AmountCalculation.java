package manager.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AmountCalculation {
    private static final Integer AMOUNT_PRE_MONTH = 100_000;

    public BigDecimal getTransferAmount(Integer experiencePlayer, Integer agePlayer,
                                        Float commissionFromTheTeam) {
        BigDecimal transfer = BigDecimal.valueOf(experiencePlayer)
                .multiply(BigDecimal.valueOf(AMOUNT_PRE_MONTH))
                .divide(BigDecimal.valueOf(agePlayer), RoundingMode.CEILING);
        BigDecimal commission = transfer
                .multiply(BigDecimal.valueOf(commissionFromTheTeam));
        return transfer.add(commission);
    }
}
