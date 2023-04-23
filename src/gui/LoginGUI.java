package gui;

import javax.print.DocFlavor.URL;
import javax.swing.*;

import conexiones.Servidor;

import excepciones.LoginErrorException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import utiles.Utiles;

/*
 * Este metodo se encarga de la creacion de la interfaz de usuario que sera
 * visible al momento de realizar el log in.
 * 
 * @author Tomas E. Schattmann
 * */

public class LoginGUI extends JFrame implements ActionListener {

	// Configuraciones
	int FRAME_HEIGHT = 500;
	int FRAME_WIDTH = 800;
	int LOGIN_PANEL_WIDTH = 350;
	int IMAGE_LABEL_WIDTH = 450;

	JPanel background = new JPanel();
	JPanel loginPanel = new JPanel();
	JPanel imagePanel = new JPanel();
	JLabel imageLabel = new JLabel();;

	// log in panel components
	JLabel titleIconImg = new JLabel();
	JLabel subtitle = new JLabel("Logiciel de gestion");
	JLabel subtitle2 = new JLabel("Log in - Base de données");
	JLabel userLabel = new JLabel("Nom d'utilisateur:");
	JLabel passwordLabel = new JLabel("Mot de passe:");
	JTextField userTextField = new JTextField("sql10600619");
	JPasswordField pswField = new JPasswordField("KpjBFXkcdf");
	JButton loginButton = new JButton("Se connecter");
	JCheckBox showPassword = new JCheckBox("Montrer mot de passe");

	/*
	 * Constructor de la clase LoginFrame()
	 */
	public LoginGUI() {
		setTitle("Log In - Gestionnaire des Jeux Olympiques");
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		Image icono = new ImageIcon(getClass().getResource("/resources/login/IconoLogin.png")).getImage();
		ImageIcon icono2 = new ImageIcon(icono.getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING));


		
		setIconImage(icono2.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		this.setLocation(Utiles.setCenterLocationFrame(this.getSize()));

		setLayoutManager();

		backgroundPanelConfig();
		imageLabelConfig();
		loginPanelConfig();

		addComponentsToLoginPanel();
		addComponentsToBackground();

		addActionEvent();

		setVisible(true);
		this.repaint();
	}

	/*
	 * Este metodo se encarga de asignar el layout del Login Frame
	 */
	public void setLayoutManager() {
		background.setLayout(null);
		loginPanel.setLayout(null);
		imagePanel.setLayout(null);

	}

	/*
	 * Se encarga de configurar el background panel.
	 */
	public void backgroundPanelConfig() {
		background.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
	}

	public void loginPanelConfig() {
		String FUENTE = "Calibri";

		loginPanel.setBounds(IMAGE_LABEL_WIDTH, 0, LOGIN_PANEL_WIDTH, FRAME_HEIGHT);
		loginPanel.setVisible(true);
		loginPanel.setBackground(new Color(185, 22, 70));// Color 2

		titleIconImg.setBounds(8, 30, 100, 100);
		titleIconImg.setVerticalAlignment(JLabel.TOP);
		Image iconoTitulo = new ImageIcon(getClass().getResource("/resources/login/IconoLogin.png")).getImage();
		ImageIcon iconoTitulo2 = new ImageIcon(iconoTitulo.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		titleIconImg.setIcon(iconoTitulo2);

		subtitle.setBounds(70, 30, 300, 60);
		subtitle.setHorizontalAlignment(SwingConstants.CENTER);
		subtitle.setHorizontalAlignment(SwingConstants.CENTER);
		subtitle.setFont(new Font(FUENTE, Font.PLAIN, 18));
		subtitle.setForeground(Color.BLACK);

		subtitle2.setBounds(70, 65, 300, 60);
		subtitle2.setHorizontalAlignment(SwingConstants.CENTER);
		subtitle2.setHorizontalAlignment(SwingConstants.CENTER);
		subtitle2.setFont(new Font(FUENTE, Font.PLAIN, 18));
		subtitle2.setForeground(Color.BLACK);

		userLabel.setBounds(IMAGE_LABEL_WIDTH, 0, 150, 30);
		userLabel.setBounds(30, 170, 120, 30);
		userLabel.setFont(new Font(FUENTE, Font.BOLD, 15));
		userLabel.setForeground(Color.BLACK);

		userTextField.setBounds(150, 170, 150, 30);

		passwordLabel.setBounds(50, 220, 100, 30);
		passwordLabel.setFont(new Font(FUENTE, Font.BOLD, 15));
		passwordLabel.setForeground(Color.BLACK);

		pswField.setBounds(150, 220, 150, 30);

		showPassword.setBounds(150, 260, 150, 30);
		showPassword.setFont(new Font(FUENTE, Font.PLAIN, 11));
		showPassword.setBackground(loginPanel.getBackground());
		showPassword.setForeground(Color.BLACK);


		loginButton.setBounds(50, 320, 120, 30);
		loginButton.setFont(new Font(FUENTE, Font.BOLD, 12));

	}

	/*
	 * Se encarga de la configuracion tanto del imageFrame, imageLabel y de la
	 * imagen usada.
	 * 
	 */
	public void imageLabelConfig() {
		// Locacion y tama�o del panel
		imagePanel.setBounds(0, 0, IMAGE_LABEL_WIDTH, FRAME_HEIGHT);
		// Color del panel
		imagePanel.setBackground(new Color(211, 228, 205));
		// Locacion y tama�o del label de imagen.
		imageLabel.setBounds(0, 0, IMAGE_LABEL_WIDTH, FRAME_HEIGHT);
		// Alineaciones
		imageLabel.setHorizontalAlignment(JLabel.CENTER);
		imageLabel.setVerticalAlignment(JLabel.CENTER);
		// Codigo que importa la imagen a usar y le da un tama�o especifico.
		Image img = new ImageIcon(getClass().getResource("/resources/login/imagenLogin.png")).getImage();
		ImageIcon img2 = new ImageIcon(img.getScaledInstance(250, 350, Image.SCALE_SMOOTH));

		imageLabel.setIcon(img2);
	}

	/*
	 * Agrega los componenetes al panel de login.
	 */
	public void addComponentsToLoginPanel() {
		loginPanel.add(titleIconImg);
		loginPanel.add(subtitle);
		loginPanel.add(subtitle2);
		loginPanel.add(userLabel);
		loginPanel.add(userTextField);
		loginPanel.add(passwordLabel);
		loginPanel.add(pswField);
		loginPanel.add(showPassword);
		loginPanel.add(loginButton);

	}

	/*
	 * Agrega los componentes al background panel.
	 */
	public void addComponentsToBackground() {
		add(background);
		background.add(loginPanel);
		background.add(imageLabel);
	}

	/*
	 * Metodo que se encarga de la interaccion con el usuario respecto a los
	 * eventos.
	 */
	public void addActionEvent() {
		loginButton.addActionListener(this);
		showPassword.addActionListener(this);
	}

	public void login() throws HeadlessException, Exception {
		if (Servidor.login(userTextField.getText(), pswField.getText())) {
			JOptionPane.showMessageDialog(this, "Vous avez réussi à vous connecter");
			setVisible(false);
			MainGUI main = new MainGUI();
		} else {
			throw new LoginErrorException("Erreur, nom d'utilisateur ou mot de passe invalide");
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			try {
				login();
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
				JOptionPane.showMessageDialog(this, "Erreur, nom d'utilisateur ou mot de passe invalide");
				userTextField.setText(null);
				pswField.setText(null);
			}
		}
		if (e.getSource() == showPassword) {
			if (showPassword.isSelected()) {
				pswField.setEchoChar((char) 0);
			} else {
				pswField.setEchoChar('*');
			}
		}
	}
}