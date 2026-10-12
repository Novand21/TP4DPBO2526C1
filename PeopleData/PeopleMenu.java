import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PeopleMenu extends JFrame {
    // Component GUI
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JTextField idField;
    private JTextField namaField;
    private JTextField tahunLahirField;
    private JTextField kategoriField;
    private JTextField emailField;
    private JTable personTable;
    private JButton addUpdateButton;
    private JButton addCancelButton;
    private JButton deleteButton;

    // Data Binding & Index State
    private ArrayList<Person> listPerson;
    private int selectedIndex = -1;

    // main method
    public static void main(String[] args) {
        PeopleMenu menu = new PeopleMenu();
        menu.setSize(700, 600);
        menu.setLocationRelativeTo(null);
        menu.setContentPane(menu.mainPanel);
        menu.getContentPane().setBackground(Color.WHITE);
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Constructor
    public PeopleMenu() {
        listPerson = new ArrayList<>();
        populateList();

        personTable.setModel(setTable());

        if (titleLabel != null) {
            titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));
        }



        deleteButton.setVisible(false);


        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) {
                    insertData();
                } else {
                    updateData();
                }
            }
        });

        // Listener Delete
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex != -1) {
                    int confirm = JOptionPane.showConfirmDialog(
                            null,
                            "Apakah Anda yakin ingin menghapus data ini?",
                            "Konfirmasi Hapus",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );

                    if (confirm == JOptionPane.YES_OPTION) {
                        deleteData();
                    }
                }
            }
        });

        // Listener Cancel
        addCancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        // Listener Klik Baris
        personTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectedIndex = personTable.getSelectedRow();

                String curId = personTable.getModel().getValueAt(selectedIndex, 1).toString();
                String curNama = personTable.getModel().getValueAt(selectedIndex, 2).toString();
                String curTahunLahir = personTable.getModel().getValueAt(selectedIndex, 3).toString();
                String curKategori = personTable.getModel().getValueAt(selectedIndex, 4).toString();
                String curEmail = personTable.getModel().getValueAt(selectedIndex, 5).toString();

                idField.setText(curId);
                namaField.setText(curNama);
                tahunLahirField.setText(curTahunLahir);
                kategoriField.setText(curKategori);
                emailField.setText(curEmail);

                addUpdateButton.setText("Update");
                deleteButton.setVisible(true);
            }
        });
    }

    // Populate data awal
    private void populateList() {
        listPerson.add(new Person("001", "Alex Turner", 1992, "Admin", "alex002@gmail.com"));
        listPerson.add(new Person("002", "Brian May", 1997, "Palladin", "maybrian21@gmail.com"));
        listPerson.add(new Person("003", "John Mayer", 1990, "Bard", "mayerlol@gmail.com"));
    }

    // setTable()
    public final DefaultTableModel setTable() {
        Object[] cols = { "No", "ID", "Nama", "Tahun Lahir", "Kategori", "Email" };
        DefaultTableModel tmp = new DefaultTableModel(null, cols);

        for (int i = 0; i < listPerson.size(); i++) {
            Object[] row = {
                    i + 1,
                    listPerson.get(i).getId(),
                    listPerson.get(i).getNama(),
                    listPerson.get(i).getTahunLahir(),
                    listPerson.get(i).getKategori(),
                    listPerson.get(i).getEmail()
            };
            tmp.addRow(row);
        }

        return tmp;
    }

    // insertData()
    public void insertData() {
        try {
            String id = idField.getText();
            String nama = namaField.getText();
            int tahunLahir = Integer.parseInt(tahunLahirField.getText());
            String kategori = kategoriField.getText();
            String email = emailField.getText();

            listPerson.add(new Person(id, nama, tahunLahir, kategori, email));

            personTable.setModel(setTable());
            clearForm();

            System.out.println("Insert berhasil");
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Tahun Lahir harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // updateData()
    public void updateData() {
        try {
            String id = idField.getText();
            String nama = namaField.getText();
            int tahunLahir = Integer.parseInt(tahunLahirField.getText());
            String kategori = kategoriField.getText();
            String email = emailField.getText();

            listPerson.get(selectedIndex).setId(id);
            listPerson.get(selectedIndex).setNama(nama);
            listPerson.get(selectedIndex).setTahunLahir(tahunLahir);
            listPerson.get(selectedIndex).setKategori(kategori);
            listPerson.get(selectedIndex).setEmail(email);

            personTable.setModel(setTable());
            clearForm();

            System.out.println("Update berhasil");
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Tahun Lahir harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // deleteData()
    public void deleteData() {
        listPerson.remove(selectedIndex);

        personTable.setModel(setTable());
        clearForm();

        System.out.println("Delete berhasil");
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
    }

    // clearForm()
    public void clearForm() {
        idField.setText("");
        namaField.setText("");
        tahunLahirField.setText("");
        kategoriField.setText("");
        emailField.setText("");

        addUpdateButton.setText("Add");
        deleteButton.setVisible(false);
        selectedIndex = -1;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}