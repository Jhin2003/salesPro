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
import java.util.List;
import java.util.regex.PatternSyntaxException;

import dao.*;
import dto.CustomerDTO;
import dto.DiscountDTO;
import dto.ProductDTO;
import dto.TransactionDTO;
import ui.components.*;
import utils.*;

public class Customers extends JPanel {

    JFrame frame;
    Records records;
    JTextField searchBar;




    private CustomerDAO customerDAO;

    private ProductDAO productDAO;

    private  SalesrepDAO salesrepDAO;
    private  TransactionDAO transactionDAO;

    private DiscountDAO discountDAO;
   public Customers(JFrame frame, Records records){
       this.frame = frame;
       this.records = records;
       searchBar = new JTextField();
       customerDAO = new CustomerDAO();
       productDAO = new ProductDAO();
       salesrepDAO = new SalesrepDAO();
       transactionDAO = new TransactionDAO();
       discountDAO = new DiscountDAO();

       setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

       JPanel topPanel = new JPanel();
       topPanel.setLayout(new BorderLayout());
       topPanel.setAlignmentY(CENTER_ALIGNMENT);
       topPanel.setBackground(new Color(217,217,217));
       topPanel.setBorder(new EmptyBorder(70, 60, 40, 60));


       JLabel currentRecordsLabel = new JLabel("Customers");
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

       JButton addCustomersButton = new JButton("Add New Customer");
       addCustomersButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               JDialog dialog = new JDialog(frame, "Table Dialog", true);
               dialog.setLayout(new BorderLayout());
               dialog.setSize(600, 450);
               dialog.setLocationRelativeTo(null);

               JPanel addnewCustomerLabelPanel = new JPanel();
               addnewCustomerLabelPanel.setBorder(new EmptyBorder(20,20,20,20));
               JLabel addnewCustomerLabel = new JLabel("Add New Customer");
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
               JLabel contactLabel = new JLabel("Contact:");
               PoppinsFontManager.applyPoppinsFont(contactLabel, true,16);
               formPanel.add(contactLabel,gbc);
               gbc.gridy++;
               JLabel addressLabel = new JLabel("Address:");
               PoppinsFontManager.applyPoppinsFont(addressLabel, true,16);
               formPanel.add(addressLabel,gbc);

               gbc.gridx = 1;
               gbc.gridy = 0;
               gbc.weightx = 1.0;
               gbc.fill = GridBagConstraints.HORIZONTAL;

               NonNumericTextField nameField = new NonNumericTextField(20);
               TextFieldLengthLimit.addLengthLimit(nameField, 40);
               PoppinsFontManager.applyPoppinsFont(nameField, false,16);
               formPanel.add(nameField,gbc);
               gbc.gridy++;
               NumericTextField contactField = new NumericTextField(20);
               TextFieldLengthLimit.addLengthLimit(contactField, 20);
               PoppinsFontManager.applyPoppinsFont(contactField, false,16);
               formPanel.add(contactField,gbc);
               gbc.gridy++;
               JTextField addressField = new JTextField(20);
               TextFieldLengthLimit.addLengthLimit(addressField, 100);
               PoppinsFontManager.applyPoppinsFont(addressField, false,16);
               formPanel.add(addressField, gbc);


               dialog.add(formPanel, BorderLayout.CENTER);

               JPanel buttonPanel = new JPanel();
               buttonPanel.setBorder(new EmptyBorder(20,20,20,20));
               JButton submitButton = new BlueButton("Submit");
               submitButton.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       String nameText = nameField.getText().trim();
                       String contactText = contactField.getText().trim();
                       String addressText = addressField.getText().trim();
                       if(nameText.isEmpty() || contactText.isEmpty() || addressText.isEmpty()){
                           JOptionPane.showMessageDialog(null, "Please fill in the text field.", "Error", JOptionPane.ERROR_MESSAGE);

                       }

                       else if(nameText.length() < 6 || contactText.length() < 10 || addressText.length() < 10){
                           JOptionPane.showMessageDialog(null, "Cannot accept incomplete details.", "Error", JOptionPane.ERROR_MESSAGE);
                       }
                       else{
                           String name = nameField.getText();
                           String contact = contactField.getText();
                           String address = addressField.getText();
                           customerDAO.addCustomer(new CustomerDTO(name,contact,address));
                           JOptionPane.showMessageDialog(
                                   frame,
                                   "Customer added successfully.",
                                   "Add Customer",
                                   JOptionPane.PLAIN_MESSAGE
                           );
                           dialog.dispose();
                           records.remove(records.customersPanel);
                           records.add(new Customers(frame,records), "Customers");
                           records.showPanel("Customers");
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
        addCustomersButton.setBorderPainted(false);
       addCustomersButton.setFocusPainted(false);
       addCustomersButton.setContentAreaFilled(false);
       addCustomerPanel.add(addCustomersButton, BorderLayout.WEST);
       add(addCustomerPanel);

       String[] columnNames = {"ID", "Name", "Contact", "Address"};
       List<CustomerDTO> customers = customerDAO.getAllCustomers();
       Object[][] data = new Object[customers.size()][4];
       for (int i = 0; i < customers.size(); i++) {
           CustomerDTO customer = customers.get(i);
           data[i][0] = customer.getId();
           data[i][1] = customer.getName();
           data[i][2] = customer.getContact();
           data[i][3] = customer.getAddress();
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
                       dialog.setSize(1000, 620);
                       dialog.setLocationRelativeTo(null);
                       int ID = (int) table.getValueAt(selectedRow, 0);
                       CustomerDTO customer = customerDAO.getCustomerByID(ID);

                       JPanel customerDetailsPanel = new JPanel();
                       customerDetailsPanel.setLayout(new BoxLayout(customerDetailsPanel, BoxLayout.Y_AXIS));
                       customerDetailsPanel.setBorder(new EmptyBorder(40,40,40,40));
                       JLabel customerinfoLabel = new JLabel("Customer Information");
                       PoppinsFontManager.applyPoppinsFont(customerinfoLabel, true, 24);
                       JLabel IDLabel = new JLabel("ID: " + customer.getId());
                       PoppinsFontManager.applyPoppinsFont(IDLabel, false, 24);
                       JLabel nameLabel = new JLabel("Name: " + customer.getName());
                       PoppinsFontManager.applyPoppinsFont(nameLabel, false, 24);
                       JLabel contactLabel = new JLabel("Contact: " + customer.getContact());
                       PoppinsFontManager.applyPoppinsFont(contactLabel, false, 24);
                       JLabel addressLabel = new JLabel("Address: " + customer.getAddress());
                       PoppinsFontManager.applyPoppinsFont(addressLabel, false, 24);
                       customerDetailsPanel.add(customerinfoLabel);
                       customerDetailsPanel.add(IDLabel);
                       customerDetailsPanel.add(nameLabel);
                       customerDetailsPanel.add(contactLabel);
                       customerDetailsPanel.add(addressLabel);
                       dialog.add(customerDetailsPanel, BorderLayout.NORTH);

                       //transactions
                       JPanel transactionsPanel = new JPanel();
                       transactionsPanel.setLayout(new BoxLayout(transactionsPanel, BoxLayout.Y_AXIS));

                       transactionsPanel.setBorder(new EmptyBorder(0,40,20,40));
                       transactionsPanel.setPreferredSize(new Dimension(800,200));
                       JLabel transactionLabel = new JLabel("Purchase History");
                       PoppinsFontManager.applyPoppinsFont(transactionLabel, true, 16);
                       transactionsPanel.add(transactionLabel);

                       String[] columnNames = {"ID", "Product Bought", "Quantity", "Discount Code", "Total Amount", "Sold By", "Date"  };
                       List<TransactionDTO> customerTransactions = transactionDAO.getCustomerPurchaseTransactionsByID(customer.getId());
                       Object[][] transactionsData = new Object[customerTransactions.size()][7];
                       for (int i = 0; i < customerTransactions.size(); i++) {
                           TransactionDTO customerTransaction = customerTransactions.get(i);
                           transactionsData[i][0] = customerTransaction.getTransactionID();
                           transactionsData[i][1] = customerTransaction.getProductName();
                           transactionsData[i][2] = customerTransaction.getQuantity();
                           transactionsData[i][3] = customerTransaction.getDiscountCode();
                           transactionsData[i][4] = customerTransaction.getTotalAmount();
                           transactionsData[i][5] = customerTransaction.getSalesrepName();
                           transactionsData[i][6] = customerTransaction.getDate();

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
                       ActionButtonsPanel.setBorder(new EmptyBorder(0,40,40,40));
                       dialog.add(ActionButtonsPanel, BorderLayout.SOUTH);

                       JButton addPurchaseButton = new GreenButton("Add Purchase");
                       addPurchaseButton.addActionListener(addPurchaseEvent -> {
                           dialog.setVisible(false);
                           JDialog purchaseDialog = new JDialog(frame, "Table Dialog", true);
                           purchaseDialog.setLayout(new BorderLayout());
                           purchaseDialog.setSize(450, 550);
                           purchaseDialog.setLocationRelativeTo(null);
                           JPanel addCustomerPurchaseLabelPanel = new JPanel();
                           addCustomerPurchaseLabelPanel.setBorder(new EmptyBorder(20,20,20,20));
                           JLabel addCustomerPurchaaseLabel = new JLabel("New Customer Purchase");
                           PoppinsFontManager.applyPoppinsFont(addCustomerPurchaaseLabel,true,24);
                           addCustomerPurchaseLabelPanel.add(addCustomerPurchaaseLabel);
                           purchaseDialog.add(addCustomerPurchaseLabelPanel, BorderLayout.NORTH);

                           JPanel formPanel = new JPanel();
                           formPanel.setLayout(new GridBagLayout());



                           GridBagConstraints gbc = new GridBagConstraints();
                           gbc.insets = new Insets(20,20,20,20);
                           gbc.gridy = 0;
                           gbc.gridx = 0;
                           gbc.anchor = GridBagConstraints.WEST;


                           JLabel boughtbyLabel = new JLabel("Bought By(Customer ID): ");
                           PoppinsFontManager.applyPoppinsFont(boughtbyLabel,true,16);
                           formPanel.add(boughtbyLabel, gbc);

                           gbc.gridy++;
                           JLabel productboughtLabel = new JLabel("Product Bought(Product Code): ");
                           PoppinsFontManager.applyPoppinsFont(productboughtLabel,true,16);
                           formPanel.add(productboughtLabel, gbc);


                           gbc.gridy++;
                           JLabel discountusedlabel = new JLabel("Discount Used(Discount Code): ");
                           PoppinsFontManager.applyPoppinsFont(discountusedlabel,true,16);
                           formPanel.add(discountusedlabel, gbc);

                           gbc.gridy++;
                           JLabel soldbyLabel = new JLabel("Sold By(Salesrep ID): ");
                           PoppinsFontManager.applyPoppinsFont(soldbyLabel,true,16);
                           formPanel.add(soldbyLabel, gbc);

                           gbc.gridy++;
                           JLabel quantityLabel = new JLabel("Quantity Bought: ");
                           PoppinsFontManager.applyPoppinsFont(quantityLabel,true,16);
                           formPanel.add(quantityLabel, gbc);

                           gbc.gridx = 1;
                           gbc.gridy = 0;
                           gbc.weightx = 1.0;
                           gbc.fill = GridBagConstraints.HORIZONTAL;

                           NumericTextField boughtbyField = new NumericTextField( 20);
                           boughtbyField.setText(Integer.toString(customer.getId()));
                           PoppinsFontManager.applyPoppinsFont(boughtbyField, false , 16);
                           boughtbyField.setEditable(false);
                           formPanel.add(boughtbyField, gbc);

                           gbc.gridy++;
                           NumericTextField productboughtField = new NumericTextField( 20);
                           PoppinsFontManager.applyPoppinsFont(productboughtField, false , 16);
                           formPanel.add(productboughtField, gbc);

                           gbc.gridy++;
                           NumericTextField discountusedField = new NumericTextField(20);
                           PoppinsFontManager.applyPoppinsFont(discountusedField, false , 16);
                           formPanel.add(discountusedField, gbc);

                           gbc.gridy++;
                           NumericTextField soldbyField = new NumericTextField(20);
                           PoppinsFontManager.applyPoppinsFont(soldbyField, false , 16);
                           formPanel.add(soldbyField, gbc);

                           gbc.gridy++;

                           NumericTextField quantityField = new NumericTextField(20);
                           PoppinsFontManager.applyPoppinsFont(quantityField, false , 16);
                           formPanel.add(quantityField, gbc);


                           purchaseDialog.add(formPanel, BorderLayout.CENTER);


                           JPanel buttonPanel = new JPanel();
                           buttonPanel.setBorder(new EmptyBorder(20,20,20,20));
                           JButton submitButton = new BlueButton("Submit");
                           submitButton.addActionListener(e1 -> {
                               String boughtbytext = boughtbyField.getText().trim();
                               String productboughtText = productboughtField.getText().trim();
                               System.out.println(productboughtField.getText());
                               String quantityText = quantityField.getText().trim();
                               String discountusedText = discountusedField.getText().trim();
                               String soldbyText = soldbyField.getText().trim();
                               DiscountDTO discountToCheck = discountDAO.getDiscountByCode(Integer.parseInt(discountusedField.getText()));

                               if (boughtbytext.isEmpty() || productboughtText.isEmpty()|| quantityText.isEmpty()|| soldbyText.isEmpty()) {

                                   JOptionPane.showMessageDialog(null, "Please fill in the text field.", "Error", JOptionPane.ERROR_MESSAGE);
                               }else if(Integer.parseInt(discountusedField.getText()) == 0){
                                   if(productDAO.getProductByCode(Integer.parseInt(productboughtField.getText())) != null && salesrepDAO.getSalesrepByID(Integer.parseInt(soldbyField.getText())) != null){

                                       int salesrepID = Integer.parseInt(soldbyField.getText());
                                       int customerID = Integer.parseInt(boughtbyField.getText());
                                       int productCode = Integer.parseInt(productboughtField.getText());
                                       String date = DateTimeUtil.getCurrentDateTimeInPhilippines();
                                       int quantity = Integer.parseInt(quantityField.getText());
                                       int discountUsed = Integer.parseInt(discountusedField.getText());
                                       ProductDTO product = productDAO.getProductByCode(Integer.parseInt(productboughtField.getText()));
                                       int productPrice = product.getPrice();
                                       DiscountDTO discounTOUse = new DiscountDTO(1,1,1);
                                       int totalAmount = (int) Math.round((productPrice  * (discounTOUse.getDiscountPercent() / 100.0)) * quantity);



                                       if(productDAO.getProductByCode(productCode).getStockQuantity() - quantity >= 0){
                                           transactionDAO.addCustomerPurchaseTransaction(new TransactionDTO(salesrepID, customerID, productCode, date,quantity, discountUsed, totalAmount));
                                           JOptionPane.showMessageDialog(null, "Purchase Transaction is succesfully recorded.", "Added Purchase", JOptionPane.PLAIN_MESSAGE);
                                       }

                                       else{
                                           JOptionPane.showMessageDialog(null, "Error processing the Entry, the database stock record should not fall below 0", "Error", JOptionPane.ERROR_MESSAGE);

                                       }
                                   }
                                   else{
                                       JOptionPane.showMessageDialog(null, "Error processing the Entry, the database received incorrect ID", "Error", JOptionPane.ERROR_MESSAGE);
                                   }

                               }
                               else if( discountToCheck.getProductCode() != Integer.parseInt(productboughtText)){
                                   JOptionPane.showMessageDialog(null, "Discount is not applicable to the product.", "Error", JOptionPane.ERROR_MESSAGE);
                               }
                               else{
                                   if(productDAO.getProductByCode(Integer.parseInt(productboughtField.getText())) != null && salesrepDAO.getSalesrepByID(Integer.parseInt(soldbyField.getText())) != null){

                                       int salesrepID = Integer.parseInt(soldbyField.getText());
                                       int customerID = Integer.parseInt(boughtbyField.getText());
                                       int productCode = Integer.parseInt(productboughtField.getText());
                                       String date = DateTimeUtil.getCurrentDateTimeInPhilippines();
                                       int quantity = Integer.parseInt(quantityField.getText());
                                       int discountUsed = Integer.parseInt(discountusedField.getText());
                                       ProductDTO product = productDAO.getProductByCode(Integer.parseInt(productboughtField.getText()));
                                       int productPrice = product.getPrice();
                                       DiscountDTO discounTOUse = discountDAO.getDiscountByCode(discountUsed);
                                       int totalAmount = (int) Math.round((productPrice  * (discounTOUse.getDiscountPercent() / 100.0)) * 4);



                                       if(productDAO.getProductByCode(productCode).getStockQuantity() - quantity >= 0){
                                           transactionDAO.addCustomerPurchaseTransaction(new TransactionDTO(salesrepID, customerID, productCode, date,quantity, discountUsed, totalAmount));
                                           JOptionPane.showMessageDialog(null, "Purchase Transaction is succesfully recorded.", "Added Purchase", JOptionPane.PLAIN_MESSAGE);
                                       }

                                       else{
                                           JOptionPane.showMessageDialog(null, "Error processing the Entry, the database stock record should not fall below 0", "Error", JOptionPane.ERROR_MESSAGE);

                                       }
                                   }
                                   else{
                                       JOptionPane.showMessageDialog(null, "Error processing the Entry, the database received incorrect ID", "Error", JOptionPane.ERROR_MESSAGE);
                                   }

                               }








                               /*log.dispose();
                               purchaseDialog.dispose();
                               records.remove(records.customersPanel);
                               records.add(new Customers(frame,records), "Customers");
                               records.showPanel("Customers");
                               frame.revalidate();
                               frame.repaint();*/

                           });
                           buttonPanel.add(submitButton);
                           purchaseDialog.add(buttonPanel, BorderLayout.SOUTH);

                           purchaseDialog.setVisible(true);

                       });
                       ActionButtonsPanel.add(addPurchaseButton);


                       JButton updateButton = new BlueButton("Update Record");
                       updateButton.addActionListener(updateActionEvent -> {
                           dialog.setVisible(false);
                           JDialog updateDialog = new JDialog(frame, "Table Dialog", true);
                           updateDialog.setSize(600, 500);
                           updateDialog.setLocationRelativeTo(null);
                           updateDialog.setLayout(new BorderLayout());

                           JPanel updateLabelPanel = new JPanel();
                           updateLabelPanel.setBorder(new EmptyBorder(20,20,20,20));
                           JLabel updateLabel = new JLabel("Update Customer Information");
                           PoppinsFontManager.applyPoppinsFont(updateLabel,true,24);
                           updateLabelPanel.add(updateLabel);
                           updateDialog.add(updateLabelPanel, BorderLayout.NORTH);


                           JPanel formPanel = new JPanel();
                           formPanel.setLayout(new GridBagLayout());

                           GridBagConstraints gbc = new GridBagConstraints();
                           gbc.insets = new Insets(20,20,20,20);
                           gbc.gridy = 0;
                           gbc.gridx = 0;
                           gbc.anchor = GridBagConstraints.WEST;

                           JLabel customerIDLabel = new JLabel("ID:");
                           PoppinsFontManager.applyPoppinsFont(customerIDLabel, true,16);
                           formPanel.add(customerIDLabel, gbc);
                           gbc.gridy++;
                           JLabel customerNameLabel = new JLabel("Name:");
                           PoppinsFontManager.applyPoppinsFont(customerNameLabel, true,16);
                           formPanel.add(customerNameLabel, gbc);
                           gbc.gridy++;
                           JLabel customerContactLabel = new JLabel("Contact:");
                           PoppinsFontManager.applyPoppinsFont(customerContactLabel, true,16);
                           formPanel.add(customerContactLabel, gbc);
                           gbc.gridy++;
                           JLabel customerAddressLabel = new JLabel("Address:");
                           PoppinsFontManager.applyPoppinsFont(customerAddressLabel, true,16);
                           formPanel.add(customerAddressLabel, gbc);

                           gbc.gridx = 1;
                           gbc.gridy = 0;
                           gbc.weightx = 1.0;
                           gbc.fill = GridBagConstraints.HORIZONTAL;


                           JTextField customerIDField = new JTextField(20);
                           PoppinsFontManager.applyPoppinsFont(customerIDField, false , 16);
                           customerIDField.setText(Integer.toString(customer.getId()));
                           customerIDField.setEditable(false);
                           formPanel.add(customerIDField, gbc);
                           gbc.gridy++;
                           JTextField customerNameField = new NonNumericTextField(20);
                           TextFieldLengthLimit.addLengthLimit(customerNameField,40);
                           PoppinsFontManager.applyPoppinsFont(customerNameField, false , 16);
                           customerNameField.setText(customer.getName());
                           formPanel.add(customerNameField, gbc);
                           gbc.gridy++;
                           JTextField customerContactField = new NumericTextField(20);
                           TextFieldLengthLimit.addLengthLimit(customerContactField,20);
                           PoppinsFontManager.applyPoppinsFont(customerContactField, false , 16);
                           customerContactField.setText(customer.getContact());
                           formPanel.add(customerContactField, gbc);
                           gbc.gridy++;
                           JTextField customerAddressField = new JTextField(20);
                           TextFieldLengthLimit.addLengthLimit(customerAddressField,100);
                           PoppinsFontManager.applyPoppinsFont(customerAddressField, false , 16);
                           customerAddressField.setText(customer.getAddress());
                           formPanel.add(customerAddressField, gbc);


                          updateDialog.add(formPanel, BorderLayout.CENTER);



                           JPanel buttonPanel = new JPanel();
                           buttonPanel.setBorder(new EmptyBorder(20,20,20,20));
                           JButton submitButton = new BlueButton("Submit");
                           submitButton.addActionListener(e1 -> {
                               int id = Integer.parseInt(customerIDField.getText());
                               String name = customerNameField.getText();
                               String contact = customerContactField.getText();
                               String address = customerAddressField.getText();

                               customerDAO.updateCustomer(new CustomerDTO(id, name, contact, address));
                               JOptionPane.showMessageDialog(
                                       frame,
                                       "Record updated successfully.",
                                       "Record Upddate",
                                       JOptionPane.PLAIN_MESSAGE
                               );
                               dialog.dispose();
                               updateDialog.dispose();
                               records.remove(records.customersPanel);
                               records.add(new Customers(frame,records), "Customers");
                               records.showPanel("Customers");
                               frame.revalidate();
                               frame.repaint();

                           });

                           buttonPanel.add(submitButton);
                           updateDialog.add(buttonPanel, BorderLayout.SOUTH);
                           updateDialog.setVisible(true);
                       });
                       ActionButtonsPanel.add(updateButton);

                       JButton deleteButton = new RedButton("Delete Record");
                       deleteButton.addActionListener(new ActionListener() {
                           @Override
                           public void actionPerformed(ActionEvent e) {
                               int choice = JOptionPane.showConfirmDialog(
                                       frame,
                                       "Are you sure you want to delete Record?",
                                       "Delete Confirmation",
                                       JOptionPane.YES_NO_OPTION,
                                       JOptionPane.PLAIN_MESSAGE
                               );
                               if (choice == JOptionPane.YES_OPTION) {
                                   int idOfCustomerToDelete = customer.getId();
                                   customerDAO.deleteCustomer(idOfCustomerToDelete);
                                   dialog.dispose();
                                   records.remove(records.customersPanel);
                                   records.add(new Customers(frame,records), "Customers");
                                   records.showPanel("Customers");
                                   frame.revalidate();
                                   frame.repaint();
                                   // Delete the item
                                   JOptionPane.showMessageDialog(
                                           frame,
                                           "Record deleted successfully.",
                                           "Delete Confirmation",
                                           JOptionPane.PLAIN_MESSAGE
                                   );
                               }
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
