import java.util.List;

public class Company {
    private List<SaleAgent> agents;
    private List<APrize> prizes;

    public void addPrize(APrize prize) {
        prizes.add(prize);
    }

    public int checkMegaPrize(ADigitalLottery ticket, AResult result) {
        int matches = countMatches(ticket.getNumberSelection(), result.getWinningNumbers());
        return determinePrizeMega(matches);
    }

    public int checkPowerPrize(ADigitalLottery ticket, AResult result) {
        int matches = countMatches(ticket.getNumberSelection(), result.getWinningNumbers());
        boolean hasPowerMatch = ticket.getPowerNumber() != null && ticket.getPowerNumber().equals(result.getPowerNumber());
        return determinePrizePower(matches, hasPowerMatch);
    }

    private int determinePrizeMega(int matches) {
        switch (matches) {
            case 6: return 1; // Jackpot
            case 5: return 2;
            case 4: return 3;
            case 3: return 4;
            default: return 0;
        }
    }

    private int determinePrizePower(int matches, boolean hasPowerMatch) {
        if (matches == 6) return hasPowerMatch ? 1 : 2;
        else if (matches == 5) return 3;
        else if (matches == 4) return 4;
        else if (matches == 3) return 5;
        else return 0;
    }

    private int countMatches(List<Integer> ticketNumbers, List<Integer> winningNumbers) {
        int matches = 0;
        for (Integer num : ticketNumbers) {
            if (winningNumbers.contains(num)) matches++;
        }
        return matches;
    }
}


