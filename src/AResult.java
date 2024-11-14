import java.util.List;

public class AResult {
    private String date;
    private List<Integer> winningNumbers;
    private Integer powerNumber;  // Số Power cho Power 6/55

    public AResult(String date, List<Integer> winningNumbers, Integer powerNumber) {
        this.date = date;
        this.winningNumbers = winningNumbers;
        this.powerNumber = powerNumber;
    }

    public String getDate() {
        return date;
    }

    public List<Integer> getWinningNumbers() {
        return winningNumbers;
    }

    public Integer getPowerNumber() {
        return powerNumber;
    }

    @Override
    public String toString() {
        return "AResult{" +
                "date='" + date + '\'' +
                ", winningNumbers=" + winningNumbers +
                ", powerNumber=" + (powerNumber != null ? powerNumber : "N/A") +
                '}';
    }
}


