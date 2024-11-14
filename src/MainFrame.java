import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private Company company;
    private SaleAgent agent;
    private DefaultTableModel lotteryTableModel;
    private DefaultTableModel resultTableModel;

    public MainFrame() {
        company = new Company();
        agent = new SaleAgent("AG001", "Địa chỉ đại lý 1");

        setTitle("Chương trình Xổ Số");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Tạo bảng để hiển thị danh sách vé số
        lotteryTableModel = new DefaultTableModel(new String[]{"Mã QR", "Ngày", "Loại vé"}, 0);
        JTable lotteryTable = new JTable(lotteryTableModel);
        JScrollPane lotteryScrollPane = new JScrollPane(lotteryTable);
        lotteryScrollPane.setBorder(BorderFactory.createTitledBorder("Danh Sách Vé Số"));

        // Tạo bảng để hiển thị kết quả xổ số
        resultTableModel = new DefaultTableModel(new String[]{"Ngày", "Giải", "Số Trúng"}, 0);
        JTable resultTable = new JTable(resultTableModel);
        JScrollPane resultScrollPane = new JScrollPane(resultTable);
        resultScrollPane.setBorder(BorderFactory.createTitledBorder("Kết Quả Xổ Số"));

        // Panel chứa các nút chức năng
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton btnCreateMega = new JButton("Tạo vé Mega 6/45");
        JButton btnCreatePower = new JButton("Tạo vé Power 6/55");
        JButton btnCheckPrize = new JButton("Kiểm tra kết quả");

        buttonPanel.add(btnCreateMega);
        buttonPanel.add(btnCreatePower);
        buttonPanel.add(btnCheckPrize);

        add(buttonPanel, BorderLayout.WEST);
        add(lotteryScrollPane, BorderLayout.CENTER);
        add(resultScrollPane, BorderLayout.EAST);

        // Thêm sự kiện cho các nút
        btnCreateMega.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createMegaLottery();
            }
        });

        btnCreatePower.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPowerLottery();
            }
        });

        btnCheckPrize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkPrize();
            }
        });

        // Cập nhật bảng với danh sách vé
        updateLotteryTable();
    }

    private void createMegaLottery() {
        String date = JOptionPane.showInputDialog(this, "Nhập ngày (yyyy-MM-dd):");
        if (date == null || date.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ngày không được để trống.");
            return;
        }
        Mega645Lottery lottery = agent.createMega645Lottery(date);
        company.addPrize(new APrize(1000000, "Mega 6/45 Jackpot", "Khớp 6 số", new ArrayList<>(), 45, 6, false));
        JOptionPane.showMessageDialog(this, "Vé Mega 6/45 được tạo thành công:\n" + lottery.toString());
        updateLotteryTable();
    }

    private void createPowerLottery() {
        String date = JOptionPane.showInputDialog(this, "Nhập ngày (yyyy-MM-dd):");
        if (date == null || date.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ngày không được để trống.");
            return;
        }
        Power655Lottery lottery = agent.createPower655Lottery(date);
        company.addPrize(new APrize(3000000, "Power 6/55 Jackpot", "Khớp 6 số và Power Number", new ArrayList<>(), 55, 6, true));
        JOptionPane.showMessageDialog(this, "Vé Power 6/55 được tạo thành công:\n" + lottery.toString());
        updateLotteryTable();
    }

    private void checkPrize() {
        String input = JOptionPane.showInputDialog(this, "Nhập mã QR của vé:");
        if (input == null || input.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã QR không được để trống.");
            return;
        }

        // Giả sử chúng ta có ngày quay thưởng là hiện tại
        String currentDate = "2024-11-13"; // Bạn có thể thay đổi hoặc lấy từ đầu vào người dùng

        // Quay thưởng cho Mega 6/45 và Power 6/55
        APrize mega645Prize = new APrize(1000000, "Mega 6/45 Jackpot", "Khớp 6 số", new ArrayList<>(), 45, 6, false);
        AResult mega645Result = mega645Prize.drawResult(currentDate);
        company.addPrize(mega645Prize);

        APrize power655Prize = new APrize(3000000, "Power 6/55 Jackpot", "Khớp 6 số và Power Number", new ArrayList<>(), 55, 6, true);
        AResult power655Result = power655Prize.drawResult(currentDate);
        company.addPrize(power655Prize);

        // Thêm kết quả vào bảng kết quả xổ số
        resultTableModel.addRow(new Object[]{currentDate, "Mega 6/45", mega645Result.toString()});
        resultTableModel.addRow(new Object[]{currentDate, "Power 6/55", power655Result.toString()});

        // Kiểm tra vé
        ADigitalLottery foundLottery = null;
        for (ADigitalLottery lottery : agent.getListLottery()) {
            if (lottery.getQrCode().equals(input)) {
                foundLottery = lottery;
                break;
            }
        }

        if (foundLottery == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy vé với mã QR đã nhập.");
            return;
        }

        // Xác định loại vé và kiểm tra giải
        String prizeMessage = "";
        if (foundLottery instanceof Mega645Lottery) {
            int prizeLevel = company.checkMegaPrize(foundLottery, mega645Result);
            prizeMessage = prizeLevel > 0 ? "Trúng giải mức: " + prizeLevel : "Không trúng giải.";
        } else if (foundLottery instanceof Power655Lottery) {
            int prizeLevel = company.checkPowerPrize(foundLottery, power655Result);
            prizeMessage = prizeLevel > 0 ? "Trúng giải mức: " + prizeLevel : "Không trúng giải.";
        }

        JOptionPane.showMessageDialog(this, "Kết quả: " + prizeMessage);
    }

    private void updateLotteryTable() {
        lotteryTableModel.setRowCount(0); // Xóa các hàng hiện tại trong bảng
        List<ADigitalLottery> lotteries = agent.getListLottery();
        for (ADigitalLottery lottery : lotteries) {
            String type = "";
            if (lottery instanceof Mega645Lottery) {
                type = "Mega 6/45";
            } else if (lottery instanceof Power655Lottery) {
                type = "Power 6/55";
            }
            lotteryTableModel.addRow(new Object[]{
                    lottery.getQrCode(),
                    lottery.getDate(),
                    type
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
