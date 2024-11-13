import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Company company = new Company();


        SaleAgent agent1 = new SaleAgent("SA001", "Hanoi");

        // Tạo vé số Mega 6/45
        ADigitalLottery megaLottery1 = agent1.createMega645Lottery("2021-01-01");
        ADigitalLottery megaLottery2 = agent1.createMega645Lottery("2021-01-02");

        // Tạo vé số Power 6/55
        ADigitalLottery powerLottery1 = agent1.createPower655Lottery("2021-01-01");
        ADigitalLottery powerLottery2 = agent1.createPower655Lottery("2021-01-02");

        // check lottery
        System.out.println(agent1.printRandomLottery());




    }




}



