import java.util.*;

// Lớp đại diện cho Thông Tin Giải Thưởng
class APrize {
    private double amount;
    private String name;
    private String rule;
    private List<AResult> listOfResult;
    private int maxNumber;      // Thêm số tối đa (45 cho Mega, 55 cho Power)
    private int pickCount;
    private boolean hasPowerNumber;

    public APrize(double amount, String name, String rule, List<AResult> listOfResult, int maxNumber, int pickCount, boolean hasPowerNumber) {
        this.amount = amount;
        this.name = name;
        this.rule = rule;
        this.listOfResult = listOfResult;
        this.maxNumber = maxNumber;
        this.pickCount = pickCount;
        this.hasPowerNumber = hasPowerNumber;
    }

    public boolean isHasPowerNumber() {
        return hasPowerNumber;
    }

    public void setHasPowerNumber(boolean hasPowerNumber) {
        this.hasPowerNumber = hasPowerNumber;
    }

    public int getPickCount() {
        return pickCount;
    }

    public void setPickCount(int pickCount) {
        this.pickCount = pickCount;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public List<AResult> getListOfResult() {
        return listOfResult;
    }

    public void setListOfResult(List<AResult> listOfResult) {
        this.listOfResult = listOfResult;
    }

    @Override
    public String toString() {
        return "APrize{" +
                "amount=" + amount +
                ", name='" + name + '\'' +
                ", rule='" + rule + '\'' +
                ", listOfResult=" + listOfResult +
                '}';
    }

    public void addResult(AResult result) {
        listOfResult.add(result);
    }

    // Phương thức để quay thưởng mà không dùng Set
    public AResult drawResult() {
        Random random = new Random();
        List<Integer> winningNumbers = new ArrayList<>();

        while (winningNumbers.size() < pickCount) {
            int nextNumber = random.nextInt(maxNumber) + 1;
            if (!winningNumbers.contains(nextNumber)) {  // Kiểm tra trùng lặp trước khi thêm
                winningNumbers.add(nextNumber);
            }
        }

        Integer powerNumber = hasPowerNumber ? random.nextInt(maxNumber) + 1 : null;
        return new AResult(winningNumbers, powerNumber);
    }

}


