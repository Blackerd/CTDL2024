import java.util.ArrayList;
import java.util.List;

public class Company {
    private List<SaleAgent> agents;
    private List<APrize> prizes;

    public Company() {
        this.agents = new ArrayList<>();
        this.prizes = new ArrayList<>(); // Khởi tạo danh sách prizes
    }

    public void addPrize(APrize prize) {
        prizes.add(prize);
    }

    // Kiểm tra vé số của khách hàng có trúng giải Mega 6/45 không
    public int checkMegaPrize(ADigitalLottery ticket, AResult result) {
        int matches = countMatches(ticket.getNumberSelection(), result.getWinningNumbers());
        return determinePrizeMega(matches);
    }

    // Kiểm tra vé số của khách hàng có trúng giải Power 6/55 không
    public int checkPowerPrize(ADigitalLottery ticket, AResult result) {
        int matches = countMatches(ticket.getNumberSelection(), result.getWinningNumbers());
        boolean hasPowerMatch = ticket.getPowerNumber() != null && ticket.getPowerNumber().equals(result.getPowerNumber());
        return determinePrizePower(matches, hasPowerMatch);
    }

    // Cơ chế xác định giải thưởng cho Mega 6/45
    private int determinePrizeMega(int matches) {
        switch (matches) {
            case 6: return 1; // Jackpot
            case 5: return 2;
            case 4: return 3;
            case 3: return 4;
            default: return 0;
        }
    }

    // Cơ chế xác định giải thưởng cho Power 6/55
    private int determinePrizePower(int matches, boolean hasPowerMatch) {
        if (matches == 6) return hasPowerMatch ? 1 : 2;
        else if (matches == 5) return 3;
        else if (matches == 4) return 4;
        else if (matches == 3) return 5;
        else return 0;
    }

    // Đếm số lượng số trùng nhau giữa vé và kết quả
    private int countMatches(List<Integer> ticketNumbers, List<Integer> winningNumbers) {
        int matches = 0;
        for (Integer num : ticketNumbers) {
            if (winningNumbers.contains(num)) matches++;
        }
        return matches;
    }
}


