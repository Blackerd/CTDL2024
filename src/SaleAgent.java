import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class SaleAgent {
    private String id;
    private String address;
    private List<ADigitalLottery> listLottery;

    public SaleAgent(String id, String address) {
        this.id = id;
        this.address = address;
        this.listLottery = new ArrayList<>();
    }

    public void addLottery(ADigitalLottery lottery) {
        listLottery.add(lottery);
    }

    // Tạo vé Mega 6/45
    public Mega645Lottery createMega645Lottery(String date) {
        List<Integer> numbers = generateUniqueRandomNumbers(6, 45);
        String qrCode = generateQrCode();
        Mega645Lottery lottery = new Mega645Lottery(qrCode, date, id, numbers);
        addLottery(lottery);
        return lottery;
    }


    // Tạo vé Power 6/55 với số Power đặc biệt
    public Power655Lottery createPower655Lottery(String date) {
        List<Integer> numbers = generateUniqueRandomNumbers(6, 55);
        Integer powerNumber = new Random().nextInt(55) + 1;
        String qrCode = generateQrCode();
        Power655Lottery lottery = new Power655Lottery(qrCode, date, id, numbers, powerNumber);
        addLottery(lottery);
        return lottery;
    }

    // Phương thức tạo dãy số ngẫu nhiên không trùng lặp
    private List<Integer> generateUniqueRandomNumbers(int count, int maxNumber) {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        while (numbers.size() < count) {
            int number = random.nextInt(maxNumber) + 1;
            if (!numbers.contains(number)) {
                numbers.add(number);
            }
        }
        return numbers;
    }

    // In ra thông tin tất cả các vé số đã bán
    public String printRandomLottery() {
        if (listLottery.isEmpty()) return "Không có vé nào được bán";
        Random rand = new Random();
        ADigitalLottery randomLottery = listLottery.get(rand.nextInt(listLottery.size()));
        return "Vé số ngẫu nhiên: " + randomLottery.toString();
    }

    @Override
    public String toString() {
        return "SaleAgent{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", listLottery=" + listLottery +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ADigitalLottery> getListLottery() {
        return listLottery;
    }

    public void setListLottery(List<ADigitalLottery> listLottery) {
        this.listLottery = listLottery;
    }


    private String generateQrCode() {
        return "QR" + System.currentTimeMillis(); // Ví dụ đơn giản
    }
}

