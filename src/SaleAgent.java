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
    public ADigitalLottery createMega645Lottery(String date) {
        List<Integer> numbers = generateUniqueRandomNumbers(6, 45);
        String qrCode = "QR" + new Random().nextInt(100000);
        ADigitalLottery lottery = new ADigitalLottery(date, qrCode, id, numbers);
        addLottery(lottery);
        return lottery;
    }

    // Tạo vé Power 6/55 với số Power đặc biệt
    public ADigitalLottery createPower655Lottery(String date) {
        List<Integer> numbers = generateUniqueRandomNumbers(6, 55);
        Integer powerNumber = new Random().nextInt(55) + 1;
        String qrCode = "QR" + new Random().nextInt(100000);
        ADigitalLottery lottery = new ADigitalLottery(date, qrCode, id, numbers, powerNumber);
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
}

