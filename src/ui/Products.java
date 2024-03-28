package ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.ScrollPaneUI;
import javax.swing.plaf.basic.BasicScrollPaneUI;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import dao.CategoryDAO;
import dao.CustomerDAO;
import dao.ProductDAO;
import dao.TransactionDAO;
import dto.CategoryDTO;
import dto.CustomerDTO;
import dto.ProductDTO;
import dto.TransactionDTO;
import ui.components.BlueButton;
import ui.components.RedButton;
import utils.NonNumericTextField;
import utils.NumericTextField;
import utils.PoppinsFontManager;
import utils.TextFieldLengthLimit;

public class Products extends JPanel {
    JFrame frame;
    Records records;
    JTextField searchBar;

    private ProductDAO productDAO;

    private CustomerDAO customerDAO;
    private  TransactionDAO transactionDAO;

    private CategoryDAO categoryDAO;
    public Products(JFrame frame, Records records){

        this.frame = frame;
        this.records = records;
        searchBar = new JTextField();
        productDAO = new ProductDAO();
        transactionDAO = new TransactionDAO();
        customerDAO = new CustomerDAO();
        categoryDAO = new CategoryDAO();

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setAlignmentY(CENTER_ALIGNMENT);
        topPanel.setBackground(new Color(217,217,217));
        topPanel.setBorder(new EmptyBorder(70, 60, 40, 60));


        JLabel currentRecordsLabel = new JLabel("Products");
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
        searchBar.setBorder(new Customers.RoundBorder(10, new Color(98,98,98))); // Set the border with rounded corner
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

        JPanel addProductPanel = new JPanel();
        addProductPanel.setLayout(new BorderLayout());
        addProductPanel.setBorder(new EmptyBorder(0, 60, 10, 60));
        addProductPanel.setBackground(new Color(217,217,217));

        JButton addProductButton = new JButton("Add New product");
        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog(frame, "Table Dialog", true);
                dialog.setLayout(new BorderLayout());
                dialog.setSize(600, 700);
                dialog.setLocationRelativeTo(null);

                JPanel addnewCustomerLabelPanel = new JPanel();
                addnewCustomerLabelPanel.setBorder(new EmptyBorder(20,20,20,20));
                JLabel addnewCustomerLabel = new JLabel("Add New Product");
                PoppinsFontManager.applyPoppinsFont(addnewCustomerLabel, true,24);
                addnewCustomerLabelPanel.add(addnewCustomerLabel);
                dialog.add(addnewCustomerLabelPanel, BorderLayout.NORTH);


                JPanel formPanel  = new JPanel();
                formPanel.setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(20,20,20,20);
                gbc.gridy = 0;
                gbc.gridx = 0;
                gbc.anchor = GridBagConstraints.WEST;

                JLabel nameLabel = new JLabel("Name:");
                PoppinsFontManager.applyPoppinsFont(nameLabel, true,16);
                formPanel.add(nameLabel,gbc);
                gbc.gridy++;
                JLabel categoryLabel= new JLabel("Category:");
                PoppinsFontManager.applyPoppinsFont(categoryLabel, true,16);
                formPanel.add(categoryLabel,gbc);
                gbc.gridy++;
                JLabel descriptionLabel = new JLabel("Description:");
                PoppinsFontManager.applyPoppinsFont(descriptionLabel, true,16);
                formPanel.add(descriptionLabel,gbc);
                gbc.gridy++;
                JLabel priceLabel = new JLabel("price:");
                PoppinsFontManager.applyPoppinsFont(priceLabel, true,16);
                formPanel.add(priceLabel,gbc);
                gbc.gridy++;
                JLabel stockquantityLabel = new JLabel("Stock Quantity:");
                PoppinsFontManager.applyPoppinsFont(stockquantityLabel, true,16);
                formPanel.add(stockquantityLabel,gbc);
                gbc.gridy++;
                JLabel soldquantityLabel = new JLabel("Sold Quantity:");
                PoppinsFontManager.applyPoppinsFont(soldquantityLabel, true,16);
                formPanel.add(soldquantityLabel,gbc);
                gbc.gridy++;
                JLabel reorderpointLabel = new JLabel("Reorder Point:");
                PoppinsFontManager.applyPoppinsFont(reorderpointLabel, true,16);
                formPanel.add(reorderpointLabel,gbc);


                gbc.gridx = 1;
                gbc.gridy = 0;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;

                JTextField nameField = new JTextField(20);
                TextFieldLengthLimit.addLengthLimit(nameField, 40);
                PoppinsFontManager.applyPoppinsFont(nameField, false,16);
                formPanel.add(nameField,gbc);
                gbc.gridy++;
                List<CategoryDTO> categories = categoryDAO.getAllCategories();
                List<String> categoriesData = new ArrayList<>();
                for (CategoryDTO category : categories) {
                    categoriesData.add(category.getName());
                }
                JComboBox<String> categoriescomboBox = new JComboBox<>(categoriesData.toArray(new String[0]));
                formPanel.add(categoriescomboBox,gbc);

                gbc.gridy++;
                JTextField descriptionField = new JTextField(20);
                TextFieldLengthLimit.addLengthLimit(descriptionField, 120);
                PoppinsFontManager.applyPoppinsFont(descriptionField, false,16);
                formPanel.add(descriptionField,gbc);

                gbc.gridy++;
                NumericTextField priceField = new NumericTextField(20);
                TextFieldLengthLimit.addLengthLimit(priceField, 12);
                PoppinsFontManager.applyPoppinsFont(priceField, false,16);
                formPanel.add(priceField,gbc);

                gbc.gridy++;
                NumericTextField stockquantityField = new NumericTextField(20);
                TextFieldLengthLimit.addLengthLimit(stockquantityField, 12);
                PoppinsFontManager.applyPoppinsFont(stockquantityField, false,16);
                formPanel.add(stockquantityField,gbc);

                gbc.gridy++;
                NumericTextField soldquantityField = new NumericTextField(20);
                TextFieldLengthLimit.addLengthLimit(soldquantityField, 12);
                PoppinsFontManager.applyPoppinsFont(soldquantityField, false,16);
                formPanel.add(soldquantityField,gbc);


                gbc.gridy++;
                NumericTextField reorderpointField = new NumericTextField(20);
                TextFieldLengthLimit.addLengthLimit(reorderpointField, 12);
                PoppinsFontManager.applyPoppinsFont(reorderpointField, false,16);
                formPanel.add(reorderpointField,gbc);


                dialog.add(formPanel, BorderLayout.CENTER);

                JPanel buttonPanel = new JPanel();
                buttonPanel.setBorder(new EmptyBorder(20,20,20,20));
                JButton submitButton = new BlueButton("Submit");
                submitButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String nameText = nameField.getText().trim();
                        String descriptionText = descriptionField.getText().trim();
                        String priceText = priceField.getText().trim();
                        String stockquantityText = stockquantityField.getText().trim();
                        String soldquantityText = soldquantityField.getText().trim();
                        String reordpointText = reorderpointField.getText().trim();

                        if(nameText.isEmpty()|| descriptionText.isEmpty()|| priceText.isEmpty()|| stockquantityText.isEmpty()|| soldquantityText.isEmpty()|| reordpointText.isEmpty()){
                            JOptionPane.showMessageDialog(null, "Please fill in the text field.", "Error", JOptionPane.ERROR_MESSAGE);

                        }
                        else{
                            String name = nameField.getText();
                            String category = (String) categoriescomboBox.getSelectedItem();
                            String description = descriptionField.getText();
                            String price = priceField.getText();
                            String stockQuantity = stockquantityField.getText();
                            String soldQuantity = stockquantityField.getText();
                            String reorderPoint = reorderpointField.getText();
                            productDAO.addProduct(new ProductDTO(categoryDAO.getCategoryByName(category).getId(), name, description, Integer.parseInt(price), Integer.parseInt(stockQuantity),Integer.parseInt(soldQuantity), Integer.parseInt(reorderPoint)));
                            JOptionPane.showMessageDialog(
                                    frame,
                                    "Product added successfully.",
                                    "Add Product",
                                    JOptionPane.PLAIN_MESSAGE
                            );
                            dialog.dispose();
                            records.remove(records.productsPanel);
                            records.add(new Products(frame,records), "Products");
                            records.showPanel("Products");
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
        addProductButton.setBorderPainted(false);
        addProductButton.setFocusPainted(false);
        addProductButton.setContentAreaFilled(false);
        addProductPanel.add(addProductButton, BorderLayout.WEST);
        add(addProductPanel);



        String[] columnNames = {"Code", "Category", "Name", "Description", "Price", "Stock Quantity", "Sold Quantity", "Reorder Point"};
        List<ProductDTO> products = productDAO.getAllProducts();
        Object[][] data = new Object[products.size()][8];
        for (int i = 0; i < products.size(); i++) {
            ProductDTO product = products.get(i);
            data[i][0] = product.getCode();
            data[i][1]= product.getCategoryName();
            data[i][2] = product.getName();
            data[i][3] = product.getDescription();
            data[i][4] = product.getPrice();
            data[i][5] = product.getStockQuantity();
            data[i][6] = product.getSoldQuantity();
            data[i][7] = product.getReorderPoint();

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
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
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
                        dialog.setSize(1000, 800);
                        dialog.setLocationRelativeTo(null);
                        int ID = (int) table.getValueAt(selectedRow, 0);
                        ProductDTO product = productDAO.getProductByCode(ID);

                        JPanel productsDetailsPanel = new JPanel();
                        productsDetailsPanel.setLayout(new BoxLayout(productsDetailsPanel, BoxLayout.Y_AXIS));
                        productsDetailsPanel.setBorder(new EmptyBorder(40,60,30,60));
                        JLabel productInfoLabel = new JLabel("Product Information");
                        PoppinsFontManager.applyPoppinsFont(productInfoLabel, true, 28);
                        JLabel codeLabel = new JLabel("Code: " + product.getCode());
                        PoppinsFontManager.applyPoppinsFont(codeLabel, false, 24);
                        JLabel categoryLabel = new JLabel("Category: " + product.getCategoryName());
                        PoppinsFontManager.applyPoppinsFont(categoryLabel, false, 24);
                        JLabel nameLabel = new JLabel("Name: " + product.getName());
                        PoppinsFontManager.applyPoppinsFont(nameLabel, false, 24);
                        JLabel descriptionLabel = new JLabel("description: " + product.getDescription());
                        PoppinsFontManager.applyPoppinsFont(descriptionLabel, false, 24);
                        JLabel priceLabel = new JLabel("price: " + product.getPrice());
                        PoppinsFontManager.applyPoppinsFont(priceLabel, false, 24);
                        JLabel stockquantityLabel = new JLabel("Stock Quantity: " + product.getStockQuantity());
                        PoppinsFontManager.applyPoppinsFont(stockquantityLabel, false, 24);
                        JLabel soldquantityLabel = new JLabel("Sold Quantity: " + product.getSoldQuantity());
                        PoppinsFontManager.applyPoppinsFont(soldquantityLabel, false, 24);
                        JLabel reorderpointLabel = new JLabel("Reorder Point: " + product.getReorderPoint());
                        PoppinsFontManager.applyPoppinsFont(reorderpointLabel, false, 24);

                        productsDetailsPanel.add(productInfoLabel);
                        productsDetailsPanel.add(codeLabel);
                        productsDetailsPanel.add(categoryLabel);
                        productsDetailsPanel.add(nameLabel);
                        productsDetailsPanel.add(descriptionLabel);
                        productsDetailsPanel.add(priceLabel);
                        productsDetailsPanel.add(stockquantityLabel);
                        productsDetailsPanel.add(soldquantityLabel);
                        productsDetailsPanel.add(reorderpointLabel);


                        dialog.add(productsDetailsPanel, BorderLayout.NORTH);

                        //transactions
                        JPanel transactionsPanel = new JPanel();
                        transactionsPanel.setLayout(new BoxLayout(transactionsPanel, BoxLayout.Y_AXIS));
                        transactionsPanel.setBorder(new EmptyBorder(40,40,40,40));
                        transactionsPanel.setPreferredSize(new Dimension(800,200));

                        JLabel transactionLabel = new JLabel("Transactions");
                        PoppinsFontManager.applyPoppinsFont(transactionLabel, true, 16);
                        transactionsPanel.add(transactionLabel);
                        String[] columnNames =  { "ID", "Quantity", "Discount Code", "Total Amount", "Sold To", "Sold By", "Date"  };


                        List<TransactionDTO> productTransactions = transactionDAO.getProductSellTransactionsByCode(product.getCode());
                        Object[][] transactionsData = new Object[productTransactions.size()][7];
                        for (int i = 0; i < productTransactions.size(); i++) {
                            TransactionDTO productTransaction = productTransactions.get(i);
                             transactionsData[i][0] = productTransaction.getTransactionID();
                            transactionsData[i][1] = productTransaction.getQuantity();
                            transactionsData[i][2] = productTransaction.getDiscountCode();
                            transactionsData[i][3] = productTransaction.getTotalAmount();
                            transactionsData[i][4] = productTransaction.getCustomerName();
                            transactionsData[i][5] = productTransaction.getSalesrepName();
                            transactionsData[i][6] = productTransaction.getDate();


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
                        ActionButtonsPanel.setBorder(new EmptyBorder(20,20,20,20));
                        JButton updateButton = new BlueButton("Update");
                        updateButton.addActionListener(updateActionEvent -> {
                            dialog.setVisible(false);
                            JDialog updateDialog = new JDialog(frame, "Table Dialog", true);
                            updateDialog.setSize(600, 600);
                            updateDialog.setLocationRelativeTo(null);
                            updateDialog.setLayout(new FlowLayout());

                            // Add ID field
                            JLabel idLabel = new JLabel("ID: ");
                            PoppinsFontManager.applyPoppinsFont(idLabel, false, 16);
                            updateDialog.add(idLabel);
                            JTextField idField = new JTextField(Integer.toString(product.getCode()) , 20);
                            idField.setPreferredSize(new Dimension(200,40));
                            idField.setEditable(false); // ID is not editable
                            updateDialog.add(idField);

                            JLabel stockLabel = new JLabel("Stock: ");
                            PoppinsFontManager.applyPoppinsFont(idLabel, false, 16);
                            updateDialog.add(idLabel);
                            JTextField stockField = new JTextField();
                            stockField.setPreferredSize(new Dimension(200,40));
                            updateDialog.add(stockField);



                            JButton submitButton = new BlueButton("Submit");
                            submitButton.addActionListener(e1 -> {
                                System.out.println(stockField.getText());
                                int id = Integer.parseInt(idField.getText());
                                int field = Integer.parseInt(idField.getText());
                                productDAO.updateProduct(new ProductDTO(product.getCode(), product.getCategoryID(),product.getName(), product.getDescription(),product.getPrice(),50, product.getSoldQuantity(), product.getReorderPoint()));


                                dialog.dispose();
                                updateDialog.dispose();
                                records.remove(records.customersPanel);
                                records.add(new Customers(frame,records), "Customers");
                                records.showPanel("Customers");
                                frame.revalidate();
                                frame.repaint();

                            });
                            updateDialog.add(submitButton);

                            updateDialog.setVisible(true);
                        });
                        ActionButtonsPanel.add(updateButton);

                        JButton deleteButton = new RedButton("Delete");
                        deleteButton.addActionListener(deleteActionEvent -> {

                           productDAO.deleteProduct(product.getCode());
                            dialog.dispose();
                            records.remove(records.productsPanel);
                            records.add(new Products(frame,records), "Products");
                            records.showPanel("Products");
                            frame.revalidate();
                            frame.repaint();
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

            }

            @Override
            public void removeUpdate(DocumentEvent e) {

                filter();

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

                filter();

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
