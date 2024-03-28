package ui;

import dao.*;
import dto.*;
import ui.components.BlueButton;
import utils.NonNumericTextField;
import utils.NumericTextField;
import ui.components.RedButton;
import utils.PoppinsFontManager;
import utils.TextFieldLengthLimit;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.ScrollPaneUI;
import javax.swing.plaf.basic.BasicScrollPaneUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class Discounts extends JPanel {

    JFrame frame;
    Records records;
    JTextField searchBar;




    private SalesrepDAO salesrepDAO;

    private TransactionDAO transactionDAO;

    private ProductDAO productDAO;
    private DiscountDAO discountDAO;
    private CategoryDAO categoryDAO;
    public Discounts(JFrame frame, Records records){
        this.frame = frame;
        this.records = records;
        searchBar = new JTextField();
        salesrepDAO = new SalesrepDAO();
        transactionDAO = new TransactionDAO();
        productDAO = new ProductDAO();
        discountDAO = new DiscountDAO();
        categoryDAO = new CategoryDAO();

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setAlignmentY(CENTER_ALIGNMENT);
        topPanel.setBackground(new Color(217,217,217));
        topPanel.setBorder(new EmptyBorder(70, 60, 40, 60));


        JLabel currentRecordsLabel = new JLabel("Discounts");
        currentRecordsLabel.setBorder(new EmptyBorder(0,0,0,20));
        PoppinsFontManager.applyPoppinsFont( currentRecordsLabel, true, 32);
        topPanel.add(currentRecordsLabel, BorderLayout.WEST);

        searchBar = new JTextField(30);
        searchBar.setBorder(new EmptyBorder(60, 0, 60, 0));
        searchBar.setPreferredSize(new Dimension(20,20));
        PoppinsFontManager.applyPoppinsFont(searchBar, false, 16);
        searchBar.setForeground(Color.GRAY);
        searchBar.setText("Search");
        searchBar.setBackground(new Color(217,217,217));
        searchBar.setBorder(new RoundBorder(10, new Color(98,98,98))); // Set the border with rounded corner
        searchBar.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchBar.getText().equals("Search")) {
                    searchBar.setText("");
                    PoppinsFontManager.applyPoppinsFont(searchBar, false, 16);
                    searchBar.setForeground(Color.BLACK);

                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchBar.getText().isEmpty()) {
                    searchBar.setText("Search");
                    searchBar.setForeground(Color.GRAY);
                }
            }
        });
        topPanel.add(searchBar, BorderLayout.CENTER);

        JPanel profilePanel = new JPanel();
        profilePanel.setAlignmentX(RIGHT_ALIGNMENT);
        profilePanel.setBackground(new Color(217,217,217));
        profilePanel.setBorder(new EmptyBorder(0,400,0,0));
        JLabel  profileName = new JLabel("Miguel Layos");
        PoppinsFontManager.applyPoppinsFont( profileName, false, 16);
        JLabel profilePicture = new JLabel("Admin");
        profilePanel.add(profileName);
        profilePanel.add(profilePicture);
        topPanel.add(profilePanel, BorderLayout.EAST);
        add(topPanel);

        JPanel addCustomerPanel = new JPanel();
        addCustomerPanel.setLayout(new BorderLayout());
        addCustomerPanel.setBorder(new EmptyBorder(0, 60, 10, 60));
        addCustomerPanel.setBackground(new Color(217,217,217));

        JButton addSalesrepButton = new JButton("Add New Discount");
        addSalesrepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(frame, "Table Dialog", true);
                dialog.setLayout(new BorderLayout());
                dialog.setSize(600, 450);
                dialog.setLocationRelativeTo(null);

                JPanel addnewSalesrepPanel = new JPanel();
                addnewSalesrepPanel.setBorder(new EmptyBorder(20,20,20,20));
                JLabel addnewSalesrepLabel = new JLabel("Add New Discount");
                PoppinsFontManager.applyPoppinsFont(addnewSalesrepLabel, true,24);
                addnewSalesrepPanel.add(addnewSalesrepLabel);
                dialog.add(addnewSalesrepPanel, BorderLayout.NORTH);


                JPanel formPanel  = new JPanel();
                formPanel.setLayout(new GridBagLayout());
                formPanel.setBorder(new EmptyBorder(20,20,20,20));
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(20,20,20,20);
                gbc.gridy = 0;
                gbc.gridx = 0;
                gbc.anchor = GridBagConstraints.WEST;



                JLabel productToDiscountLabel = new JLabel("Product to discount:");
                PoppinsFontManager.applyPoppinsFont(productToDiscountLabel, true,16);
                formPanel.add(productToDiscountLabel,gbc);
                gbc.gridy++;
                JLabel discountPercentLabel = new JLabel("discount percent:");
                PoppinsFontManager.applyPoppinsFont(discountPercentLabel, true,16);
                formPanel.add(discountPercentLabel,gbc);

                gbc.gridx = 1;
                gbc.gridy = 0;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;

                List<ProductDTO> products = productDAO.getAllProducts();
                List<String> productsData = new ArrayList<>();
                for (ProductDTO product : products) {
                    productsData.add(product.getName());
                }
                JComboBox<String> productsComboBox = new JComboBox<>(productsData.toArray(new String[0]));
                formPanel.add(productsComboBox,gbc);

                gbc.gridy++;
                NumericTextField discountPercentField = new NumericTextField(20);
                TextFieldLengthLimit.addLengthLimit(discountPercentField, 20);
                PoppinsFontManager.applyPoppinsFont(discountPercentField, false,16);
                formPanel.add(discountPercentField,gbc);



                dialog.add(formPanel, BorderLayout.CENTER);



                JPanel buttonPanel = new JPanel();
                buttonPanel.setBorder(new EmptyBorder(20,20,20,20));
                JButton submitButton = new BlueButton("Submit");
                submitButton.addActionListener(new ActionListener() {


                    @Override
                    public void actionPerformed(ActionEvent e) {
                       String discountPercentText = discountPercentField.getText().trim();

                        if(discountPercentText.isEmpty() ){
                            JOptionPane.showMessageDialog(null, "Please fill in the text field.", "Error", JOptionPane.ERROR_MESSAGE);

                        }
                        else{

                            String category = (String) productsComboBox.getSelectedItem();
                            int discountPercent = Integer.parseInt(discountPercentField.getText());
                            discountDAO.addDiscount(new DiscountDTO(productDAO.getProductByName(category).getCode(), discountPercent));
                            JOptionPane.showMessageDialog(
                                    frame,
                                    "Sales representative added successfully.",
                                    "Add Salesrep",
                                    JOptionPane.PLAIN_MESSAGE
                            );
                            dialog.dispose();
                            records.remove(records.salesrepPanel);
                            records.add(new Salesreps(frame,records), "Salesreps");
                            records.showPanel("Salesreps");
                            frame.revalidate();
                            frame.repaint();
                        }
                    }
                });
                buttonPanel.add(submitButton);
                dialog.add(buttonPanel, BorderLayout.SOUTH);
                dialog.setVisible(true);
            }
        });
        addSalesrepButton.setBorderPainted(false);
        addSalesrepButton.setFocusPainted(false);
        addSalesrepButton.setContentAreaFilled(false);
        addCustomerPanel.add(addSalesrepButton, BorderLayout.WEST);
        add(addCustomerPanel);

        String[] columnNames = {"Code", "Product Discounted", "Discount Percent"};
        List<DiscountDTO> discounts = discountDAO.getAllDiscounts();
        Object[][] data = new Object[discounts.size()][3];
        for (int i = 0; i < discounts.size(); i++) {
            DiscountDTO discount = discounts.get(i);
            data[i][0] = discount.getCode();
            data[i][1] = discount.getProductName();
            data[i][2] = discount.getDiscountPercent();

        }


        // Create a table model
        DefaultTableModel model = new DefaultTableModel(data, columnNames){
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }

        };

        // Create a JTable with the model
        JTable table = new JTable(model);
        table.setBackground(new Color(246,246,246));
        table.setRowHeight(40);
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setForeground(new Color(6,6,6));
        table.getTableHeader().setBackground(new Color(195,195,195));
        table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 40));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        table.getTableHeader().setForeground(new Color(6,6,6));

        table.setIntercellSpacing(new Dimension(0, 0));
        table.setDefaultRenderer(Object.class, new TableCellRenderer() {
            private final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (column == 0) {
                    renderer.setFont(renderer.getFont().deriveFont(Font.BOLD));
                }
                ((JLabel) renderer).setHorizontalAlignment(SwingConstants.CENTER); // Center text horizontally
                return renderer;
            }
        });
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) { // Single-click
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        JDialog dialog = new JDialog(frame, "Table Dialog", true);
                        dialog.setLayout(new BorderLayout());
                        dialog.setSize(1050, 600);
                        dialog.setLocationRelativeTo(null);
                        int ID = (int) table.getValueAt(selectedRow, 0);
                        SalesrepDTO salesrep = salesrepDAO.getSalesrepByID(ID);

                        JPanel salesrepDetailsPanel = new JPanel();
                        salesrepDetailsPanel.setLayout(new BoxLayout(salesrepDetailsPanel, BoxLayout.Y_AXIS));
                        salesrepDetailsPanel.setBorder(new EmptyBorder(40,40,40,40));
                        JLabel salesrepInfoLabel = new JLabel("Sales Representative Infomation");
                        PoppinsFontManager.applyPoppinsFont(salesrepInfoLabel, true, 24);
                        JLabel IDLabel = new JLabel("ID: " + salesrep.getId());
                        PoppinsFontManager.applyPoppinsFont(IDLabel, false, 24);
                        JLabel nameLabel = new JLabel("Name: " + salesrep.getName());
                        PoppinsFontManager.applyPoppinsFont(nameLabel, false, 24);
                        JLabel contactLabel = new JLabel("Contact: " + salesrep.getContact());
                        PoppinsFontManager.applyPoppinsFont(contactLabel, false, 24);
                        JLabel addressLabel = new JLabel("Address: " + salesrep.getAddress());
                        PoppinsFontManager.applyPoppinsFont(addressLabel, false, 24);
                        salesrepDetailsPanel.add(salesrepInfoLabel);
                        salesrepDetailsPanel.add(IDLabel);
                        salesrepDetailsPanel.add(nameLabel);
                        salesrepDetailsPanel.add(contactLabel);
                        salesrepDetailsPanel.add(addressLabel);

                        dialog.add(salesrepDetailsPanel, BorderLayout.NORTH);



                        //transactions
                        JPanel transactionsPanel = new JPanel();
                        transactionsPanel.setLayout(new BoxLayout(transactionsPanel, BoxLayout.Y_AXIS));
                        transactionsPanel.setBorder(new EmptyBorder(0,40,20,40));
                        transactionsPanel.setPreferredSize(new Dimension(800,200));

                        JLabel transactionLabel = new JLabel("Sales History");

                        PoppinsFontManager.applyPoppinsFont(transactionLabel, true, 16);
                        transactionsPanel.add(transactionLabel);
                        String[] columnNames = {"ID", "Product Bought", "Quantity", "Discount Used", "Total Amount", "Sold To", "Date"  };


                        List<TransactionDTO> salesrepTransactions = transactionDAO.getSalesrepSalesTransactionsByID(salesrep.getId());
                        Object[][] transactionsData = new Object[salesrepTransactions.size()][7];
                        for (int i = 0; i < salesrepTransactions.size(); i++) {
                            TransactionDTO salesrepTransaction = salesrepTransactions.get(i);
                            transactionsData[i][0] = salesrepTransaction.getTransactionID();
                            transactionsData[i][1] = salesrepTransaction.getProductName();
                            transactionsData[i][2] = salesrepTransaction.getQuantity();
                            transactionsData[i][3] = salesrepTransaction.getDiscountCode();
                            transactionsData[i][4] = salesrepTransaction.getTotalAmount();
                            transactionsData[i][5] = salesrepTransaction.getCustomerName();
                            transactionsData[i][6] = salesrepTransaction.getDate();

                        }

                        DefaultTableModel model = new DefaultTableModel(transactionsData, columnNames){
                            public boolean isCellEditable(int row, int column) {
                                return false; // Make all cells non-editable
                            }

                        };
                        // Create a JTable with the model
                        JTable transactionsTable = new JTable(model);
                        transactionsTable.setRowHeight(25);
                        transactionsTable.setForeground(new Color(6,6,6));
                        transactionsTable.getTableHeader().setBackground(new Color(195,195,195));
                        transactionsTable.getTableHeader().setForeground(new Color(6,6,6));
                        transactionsTable.setFont(new Font("Arial", Font.PLAIN, 12));
                        transactionsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));







                        JScrollPane transactionsScrollPane = new JScrollPane(transactionsTable);
                        transactionsScrollPane.setBorder(new EmptyBorder(20,0,20,0));

                        transactionsPanel.add(transactionsScrollPane);
                        dialog.add(transactionsPanel, BorderLayout.CENTER);

                        JPanel ActionButtonsPanel = new JPanel();
                        dialog.add(ActionButtonsPanel, BorderLayout.SOUTH);
                        ActionButtonsPanel.setBorder(new EmptyBorder(0,40,40,40));

                        JButton updateButton = new BlueButton("Update Record");
                        updateButton.addActionListener(updateActionEvent -> {
                            dialog.setVisible(false);
                            JDialog updateDialog = new JDialog(frame, "Table Dialog", true);
                            updateDialog.setSize(600, 500);
                            updateDialog.setLocationRelativeTo(null);
                            updateDialog.setLayout(new BorderLayout());

                            JPanel updateLabelPanel = new JPanel();
                            updateLabelPanel.setBorder(new EmptyBorder(20,20,20,20));
                            JLabel updateLabel = new JLabel("Update Sales Representative Information");
                            PoppinsFontManager.applyPoppinsFont(updateLabel,true,20);
                            updateLabelPanel.add(updateLabel);
                            updateDialog.add(updateLabelPanel, BorderLayout.NORTH);


                            JPanel formPanel = new JPanel();
                            formPanel.setLayout(new GridBagLayout());

                            GridBagConstraints gbc = new GridBagConstraints();
                            gbc.insets = new Insets(20,20,20,20);
                            gbc.gridy = 0;
                            gbc.gridx = 0;
                            gbc.anchor = GridBagConstraints.WEST;

                            JLabel salesrepIDLabel = new JLabel("ID: ");
                            PoppinsFontManager.applyPoppinsFont(salesrepIDLabel, true,16);
                            formPanel.add(salesrepIDLabel, gbc);
                            gbc.gridy++;
                            JLabel salesrepNameLabel = new JLabel("Name: ");
                            PoppinsFontManager.applyPoppinsFont(salesrepNameLabel, true,16);
                            formPanel.add(salesrepNameLabel, gbc);
                            gbc.gridy++;
                            JLabel salesrepContactLabel = new JLabel("Contact:");
                            PoppinsFontManager.applyPoppinsFont(salesrepContactLabel, true,16);
                            formPanel.add(salesrepContactLabel, gbc);
                            gbc.gridy++;
                            JLabel salesrepAddressLabel = new JLabel("Address");
                            PoppinsFontManager.applyPoppinsFont(salesrepAddressLabel, true,16);
                            formPanel.add(salesrepAddressLabel, gbc);

                            gbc.gridx = 1;
                            gbc.gridy = 0;
                            gbc.weightx = 1.0;
                            gbc.fill = GridBagConstraints.HORIZONTAL;


                            JTextField salesrepIDField = new JTextField(20);
                            PoppinsFontManager.applyPoppinsFont(salesrepIDField, false , 16);
                            salesrepIDField.setText(Integer.toString(salesrep.getId()));
                            salesrepIDField.setEditable(false);
                            formPanel.add(salesrepIDField, gbc);
                            gbc.gridy++;
                            JTextField salesrepNameField = new NonNumericTextField(20);
                            TextFieldLengthLimit.addLengthLimit(salesrepNameField,40);
                            PoppinsFontManager.applyPoppinsFont(salesrepNameField, false , 16);
                            salesrepNameField.setText(salesrep.getName());
                            formPanel.add(salesrepNameField, gbc);
                            gbc.gridy++;
                            JTextField salesrepContactField = new NumericTextField(20);
                            TextFieldLengthLimit.addLengthLimit(salesrepContactField,20);
                            PoppinsFontManager.applyPoppinsFont(salesrepContactField, false , 16);
                            salesrepContactField.setText(salesrep.getContact());
                            formPanel.add(salesrepContactField, gbc);
                            gbc.gridy++;
                            JTextField salesrepAddressField = new JTextField(20);
                            TextFieldLengthLimit.addLengthLimit(salesrepAddressField,100);
                            PoppinsFontManager.applyPoppinsFont(salesrepAddressField, false , 16);
                            salesrepAddressField.setText(salesrep.getAddress());
                            formPanel.add(salesrepAddressField, gbc);


                            updateDialog.add(formPanel, BorderLayout.CENTER);

                            JPanel buttonPanel = new JPanel();
                            buttonPanel.setBorder(new EmptyBorder(20,20,20,20));
                            JButton submitButton = new BlueButton("Submit");
                            submitButton.addActionListener(e1 -> {
                                int id = Integer.parseInt(salesrepIDField.getText());
                                String name = salesrepNameField.getText();
                                String contact = salesrepContactField.getText();
                                String address = salesrepAddressField.getText();

                                salesrepDAO.updateSalesrep(new SalesrepDTO(id, name, contact, address));
                                JOptionPane.showMessageDialog(
                                        frame,
                                        "Record updated successfully.",
                                        "Record Update",
                                        JOptionPane.PLAIN_MESSAGE
                                );
                                dialog.dispose();
                                updateDialog.dispose();
                                records.remove(records.salesrepPanel);
                                records.add(new Salesreps(frame,records), "Salesreps");
                                records.showPanel("Salesreps");
                                frame.revalidate();
                                frame.repaint();

                            });
                            buttonPanel.add(submitButton);
                            updateDialog.add(buttonPanel, BorderLayout.SOUTH);

                            updateDialog.setVisible(true);
                        });
                        ActionButtonsPanel.add(updateButton);

                        JButton deleteButton = new RedButton("Delete Record");
                        deleteButton.addActionListener(deleteActionEvent -> {
                            int choice = JOptionPane.showConfirmDialog(
                                    frame,
                                    "Are you sure you want to delete Record?",
                                    "Delete Confirmation",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.PLAIN_MESSAGE
                            );
                            if (choice == JOptionPane.YES_OPTION) {
                                int idOfSalesrepToDelete = salesrep.getId();
                                salesrepDAO.deleteSalesrep(idOfSalesrepToDelete);
                                dialog.dispose();
                                records.remove(records.salesrepPanel);
                                records.add(new Salesreps(frame, records), "Salesreps");
                                records.showPanel("Salesreps");
                                frame.revalidate();
                                frame.repaint();

                                JOptionPane.showMessageDialog(
                                        frame,
                                        "Record deleted successfully.",
                                        "Delete Confirmation",
                                        JOptionPane.PLAIN_MESSAGE
                                );
                            }
                        });
                        ActionButtonsPanel.add(deleteButton);
                        dialog.setVisible(true);
                    }
                }
            }
        });

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        searchBar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

                filter();
                System.out.println(searchBar.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

                filter();
                System.out.println(searchBar.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

                filter();
                System.out.println(searchBar.getText());
            }

            private void filter() {
                String searchTerm = searchBar.getText().toLowerCase();
                if (searchTerm.equals("search")) {
                    sorter.setRowFilter(null);

                } else{
                    try {
                        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchTerm));
                    } catch (PatternSyntaxException e) {
                        sorter.setRowFilter(null);
                    }
                }

            }
        });

        JScrollPane scrollPane = new JScrollPane(table){
            @Override
            public void setUI(ScrollPaneUI ui) {
                super.setUI(new BasicScrollPaneUI() {
                    @Override
                    protected void installDefaults(JScrollPane scrollpane) {
                        super.installDefaults(scrollpane);
                        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Hide vertical scrollbar
                        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Hide horizontal scrollbar
                    }
                });
            }
        };
        scrollPane.setPreferredSize(new Dimension(1000, 700));
        scrollPane.setBorder(new EmptyBorder(0, 60, 0, 60));
        scrollPane.setBackground(new Color(217,217,217));
        add(scrollPane);

    }

    static class RoundBorder implements Border {
        private Color color;
        private int radius;

        public RoundBorder(int radius, Color color) {
            this.color = color;
            this.radius = radius;

        }

        public Insets getBorderInsets(Component c) {
            int top = radius + 3;
            int left = radius + 3;
            int bottom = radius + 3;
            int right = radius + 3;
            return new Insets(top, left, bottom, right);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(color);
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g.dispose();
        }
    }
}

