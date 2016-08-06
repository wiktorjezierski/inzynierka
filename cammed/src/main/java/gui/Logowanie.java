package gui;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import actions.DeviceType;
import actions.Response;
import actions.SignInAction;
import net.miginfocom.swing.MigLayout;

public class Logowanie extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6106752922761877845L;
	
	private JPanel contentPane;
	private JTextField fieldLogin;
	private JTextField fieldPassword;
	private JButton logowanie;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Logowanie frame = new Logowanie();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Logowanie() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][grow][grow][grow]", "[][][][][]"));
		
		fieldLogin = new JTextField();
		contentPane.add(fieldLogin, "cell 5 2,growx");
		fieldLogin.setColumns(10);
		
		fieldPassword = new JPasswordField();
		contentPane.add(fieldPassword, "cell 5 3,growx");
		fieldPassword.setColumns(10);
		
		logowanie = new JButton("zaloguj");
		logowanie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				somemethod();
			}

		});
		contentPane.add(logowanie, "cell 5 4,alignx center");
	}
	
	private void somemethod() {
		try {
			Socket socket = openConnection();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream objectInputStream  = new ObjectInputStream(socket.getInputStream());
			
			SignInAction logowanie = new SignInAction(fieldLogin.getText(), fieldPassword.getText(), true, DeviceType.PC);
			objectOutputStream.writeObject(logowanie);
			Response response = (Response)objectInputStream.readObject();
			
			boolean cos = true;
//			if(response.)
		} catch (Exception e) {
			
		}
	}
	
	private Socket openConnection() {
		String serverName = "192.168.0.4";
		int port = 6066;
		try {
			System.out.println("Connecting to " + serverName + " on port " + port);
			Socket client = new Socket(serverName, port);
			System.out.println("Just connected to " + client.getRemoteSocketAddress());
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			out.writeUTF("Hello from " + client.getLocalSocketAddress());
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			int newPort = Integer.parseInt(in.readUTF());
			System.out.println("Server says " + newPort + " " + client.getRemoteSocketAddress());
			
			return new Socket(serverName, newPort);
		} catch(Exception e) {
			throw new RuntimeException("something was wrong during initialize connection");
		}
	}
}
