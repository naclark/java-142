import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.UIManager;

/**
 * This class is complete. DON'T CHANGE IT. It builds the GUI for the user.
 * <p>
 * There is no need for you to understand the code. But of course, if you have
 * any question, don't hesitate to ask me.
 */

public class PaintShop extends JFrame {

	// Set the look and feel according to the user's machine
	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Possible values for the combo boxes
	private String[] footValues = new String[101];

	private String[] inchValues = new String[12];
	// initialize the arrays
	{
		for (int i = 0; i < footValues.length; i++)
			footValues[i] = "" + i;
		for (int i = 0; i < inchValues.length; i++)
			inchValues[i] = "" + i;
	}

	// Elements of the GUI
	private JPanel paintShopPanel = new JPanel();

	private JComboBox cboHeightFeet = new JComboBox(footValues);

	private JLabel lblHeight = new JLabel();

	private JLabel lblHeightFeet = new JLabel();

	private JComboBox cboHeightInches = new JComboBox(inchValues);

	private JLabel lblHeightInches = new JLabel();

	private JComboBox cboLengthInches = new JComboBox(inchValues);

	private JComboBox cboLengthFeet = new JComboBox(footValues);

	private JLabel lblLengthInches = new JLabel();

	private JLabel lblLengthFeet = new JLabel();

	private JLabel lblLength = new JLabel();

	private JComboBox cboWidthInches = new JComboBox(inchValues);

	private JComboBox cboWidthFeet = new JComboBox(footValues);

	private JLabel lblWidthInches = new JLabel();

	private JLabel lblWidthFeet = new JLabel();

	private JLabel lblWidth = new JLabel();

	private JButton btnCalculate = new JButton();

	private JLabel lblTitle = new JLabel();

	private JScrollPane scpResult = new JScrollPane();

	private JTextArea txtResult = new JTextArea();

	/** Construct the GUI for the paint shop */
	public PaintShop() {
		// Show the frame to compute its size
		this.setVisible(true);
		Insets insets = this.getInsets();

		// Set the client size to 370 by 450
		this.setSize(new Dimension(370 + insets.left + insets.right, 450
				+ insets.top + insets.bottom));
		this.setTitle("Paint shop");
		this.setResizable(false);

		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setVisible(true);
	}

	private void jbInit() throws Exception {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		paintShopPanel.setLayout(null);
		lblHeight.setText("Height");
		lblHeight.setBounds(new Rectangle(54, 48, 68, 17));
		lblHeightFeet.setText("feet");
		lblHeightFeet.setBounds(new Rectangle(173, 48, 33, 17));
		lblHeightInches.setText("inches");
		lblHeightInches.setBounds(new Rectangle(274, 48, 42, 17));
		lblLengthInches.setText("inches");
		lblLengthInches.setBounds(new Rectangle(274, 79, 42, 17));
		lblLengthFeet.setText("feet");
		lblLengthFeet.setBounds(new Rectangle(173, 79, 33, 17));
		lblLength.setToolTipText("");
		lblLength.setText("Length");
		lblLength.setBounds(new Rectangle(54, 79, 68, 17));
		lblWidthInches.setText("inches");
		lblWidthInches.setBounds(new Rectangle(274, 110, 42, 17));
		lblWidthFeet.setText("feet");
		lblWidthFeet.setBounds(new Rectangle(173, 110, 33, 17));
		lblWidth.setText("Width");
		lblWidth.setBounds(new Rectangle(54, 110, 68, 17));
		btnCalculate.setBounds(new Rectangle(140, 149, 89, 30));
		btnCalculate.setText("Calculate");
		btnCalculate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCalculate_actionPerformed(e);
			}
		});
		lblTitle.setFont(new java.awt.Font("Serif", 3, 30));
		lblTitle.setForeground(Color.blue);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setText("PAINT SHOP");
		lblTitle.setBounds(new Rectangle(0, 0, 370, 40));
		txtResult.setEditable(false);
		scpResult.setBounds(new Rectangle(10, 192, 350, 248));
		cboWidthInches.setBounds(new Rectangle(206, 109, 61, 19));
		cboWidthFeet.setBounds(new Rectangle(106, 109, 61, 19));
		cboLengthInches.setBounds(new Rectangle(206, 77, 61, 19));
		cboLengthFeet.setBounds(new Rectangle(106, 76, 61, 19));
		cboHeightInches.setBounds(new Rectangle(206, 43, 61, 19));
		cboHeightFeet.setBounds(new Rectangle(106, 43, 61, 19));
		this.getContentPane().add(paintShopPanel, BorderLayout.CENTER);
		paintShopPanel.add(lblHeight, null);
		paintShopPanel.add(cboHeightFeet, null);
		paintShopPanel.add(lblHeightFeet, null);
		paintShopPanel.add(cboHeightInches, null);
		paintShopPanel.add(lblHeightInches, null);
		paintShopPanel.add(cboLengthInches, null);
		paintShopPanel.add(lblLengthInches, null);
		paintShopPanel.add(cboLengthFeet, null);
		paintShopPanel.add(lblLengthFeet, null);
		paintShopPanel.add(lblLength, null);
		paintShopPanel.add(lblWidth, null);
		paintShopPanel.add(cboWidthFeet, null);
		paintShopPanel.add(lblWidthFeet, null);
		paintShopPanel.add(cboWidthInches, null);
		paintShopPanel.add(lblWidthInches, null);
		paintShopPanel.add(btnCalculate, null);
		paintShopPanel.add(lblTitle, null);
		paintShopPanel.add(scpResult, null);
		scpResult.getViewport().add(txtResult, null);
	}

	private void btnCalculate_actionPerformed(ActionEvent e) {
		// Dimensions of the room
		int heightFeet = Integer.parseInt((String) cboHeightFeet
				.getSelectedItem());
		int heightInches = Integer.parseInt((String) cboHeightInches
				.getSelectedItem());
		int widthFeet = Integer.parseInt((String) cboWidthFeet
				.getSelectedItem());
		int widthInches = Integer.parseInt((String) cboWidthInches
				.getSelectedItem());
		int lengthFeet = Integer.parseInt((String) cboLengthFeet
				.getSelectedItem());
		int lengthInches = Integer.parseInt((String) cboLengthInches
				.getSelectedItem());

		// Compute and display the amount of paint needed
		PaintShopCalculator pc = new PaintShopCalculator(heightFeet,
				heightInches, lengthFeet, lengthInches, widthFeet, widthInches);
		txtResult.setText(pc.toString());
	}

	/**
	 * Entry point of the program
	 */
	public static void main(String[] args) {
		new PaintShop();
	}
}