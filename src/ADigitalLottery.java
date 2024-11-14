import java.util.List;

public class ADigitalLottery {
    private String date;
    private String qrCode;
    private String idSaleAgent;
    private List<Integer> numberSelection;
    private Integer powerNumber;

    // Constructor cho Mega 6/45
    public ADigitalLottery(String date, String qrCode, String idSaleAgent, List<Integer> numberSelection) {
        this.date = date;
        this.qrCode = qrCode;
        this.idSaleAgent = idSaleAgent;
        this.numberSelection = numberSelection;
    }

    // Constructor cho Power 6/55
    public ADigitalLottery(String date, String qrCode, String idSaleAgent, List<Integer> numberSelection, Integer powerNumber) {
        this.date = date;
        this.qrCode = qrCode;
        this.idSaleAgent = idSaleAgent;
        this.numberSelection = numberSelection;
        this.powerNumber = powerNumber;
    }

    public ADigitalLottery(String qrCode, String date) {
        this.qrCode = qrCode;
        this.date = date;
    }

    public String getQrCode() {
        return qrCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getIdSaleAgent() {
        return idSaleAgent;
    }

    public void setIdSaleAgent(String idSaleAgent) {
        this.idSaleAgent = idSaleAgent;
    }

    public List<Integer> getNumberSelection() {
        return numberSelection;
    }

    public void setNumberSelection(List<Integer> numberSelection) {
        this.numberSelection = numberSelection;
    }

    public Integer getPowerNumber() {
        return powerNumber;
    }

    public void setPowerNumber(Integer powerNumber) {
        this.powerNumber = powerNumber;
    }

    @Override
    public String toString() {
        return "ADigitalLottery{" +
                "date='" + date + '\'' +
                ", qrCode='" + qrCode + '\'' +
                ", idSaleAgent='" + idSaleAgent + '\'' +
                ", numberSelection=" + numberSelection +
                ", powerNumber=" + (powerNumber != null ? powerNumber : "N/A") +
                '}';
    }
}

