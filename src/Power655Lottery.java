import java.util.List;

public class Power655Lottery extends ADigitalLottery {
    public Power655Lottery(String qrCode, String date, String idSaleAgent, List<Integer> numberSelection, Integer powerNumber) {
        super(date, qrCode, idSaleAgent, numberSelection, powerNumber);
    }

    @Override
    public String toString() {
        return "Power655Lottery{" +
                "date='" + getDate() + '\'' +
                ", qrCode='" + getQrCode() + '\'' +
                ", idSaleAgent='" + getIdSaleAgent() + '\'' +
                ", numberSelection=" + getNumberSelection() +
                ", powerNumber=" + (getPowerNumber() != null ? getPowerNumber() : "N/A") +
                '}';
    }
}
