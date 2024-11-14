import java.util.List;

public class Mega645Lottery extends ADigitalLottery {
    public Mega645Lottery(String qrCode, String date, String idSaleAgent, List<Integer> numberSelection) {
        super(date, qrCode, idSaleAgent, numberSelection);
    }

    @Override
    public String toString() {
        return "Mega645Lottery{" +
                "date='" + getDate() + '\'' +
                ", qrCode='" + getQrCode() + '\'' +
                ", idSaleAgent='" + getIdSaleAgent() + '\'' +
                ", numberSelection=" + getNumberSelection() +
                ", powerNumber=" + (getPowerNumber() != null ? getPowerNumber() : "N/A") +
                '}';
    }
}
