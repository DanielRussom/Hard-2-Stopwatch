package stopwatch;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3910691953637909673L;
	private JPanel contentPane;
	private JLabel lblTotalTime;
	private JButton btnLap;
	private JPanel pnlLaps;
	private JLabel lblLapTime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
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
	public GUI() {
		setSize(new Dimension(378, 257));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblTotalTime = new JLabel("00:00.00");
		lblTotalTime.setFont(new Font("OCR A Extended", Font.PLAIN, 24));
		lblTotalTime.setBounds(154, 11, 166, 35);
		contentPane.add(lblTotalTime);

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Controller.isEnabled) {
					Controller.isEnabled = false;
					btnStart.setText("Start");
					btnLap.setText("Reset");
				} else {
					Controller.isEnabled = true;
					btnStart.setText("Stop");
					btnLap.setText("Lap");
				}
			}
		});
		btnStart.setBounds(122, 109, 89, 23);
		contentPane.add(btnStart);

		btnLap = new JButton("Lap");
		btnLap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Controller.isEnabled){
					addLap();
				} else {
					Controller.resetAll();
					lblTotalTime.setText("00:00.00");
					lblLapTime.setText("00:00.00");
					pnlLaps.removeAll();
					pnlLaps.repaint();
					pnlLaps.revalidate();
				}
				
			}
		});
		btnLap.setBounds(122, 143, 89, 23);
		contentPane.add(btnLap);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(221, 109, 89, 142);
		contentPane.add(scrollPane);
		
		pnlLaps = new JPanel();
		scrollPane.setViewportView(pnlLaps);
		pnlLaps.setLayout(new BoxLayout(pnlLaps, BoxLayout.Y_AXIS));
		
		JLabel lblLaps = new JLabel("Laps");
		lblLaps.setBounds(252, 96, 46, 14);
		contentPane.add(lblLaps);
		
		lblLapTime = new JLabel("00:00.00");
		lblLapTime.setFont(new Font("OCR A Extended", Font.PLAIN, 18));
		lblLapTime.setBounds(168, 57, 138, 35);
		contentPane.add(lblLapTime);
		Controller.startUp(this);
	}

	public void setTotalText(String text) {
		lblTotalTime.setText(text);
	}
	
	public void setLapText(String text) {
		lblLapTime.setText(text);
	}
	
	public void addLap(){
		Time lapTimer = Controller.getLapTimer();
		String newLap = Controller.lapNo + ". " + lapTimer.getTime();
		lapTimer.resetTime();
		Controller.lapNo += 1;
		JLabel lbl = new JLabel(newLap);
		pnlLaps.add(lbl);
		pnlLaps.repaint();
		pnlLaps.revalidate();
	}
}
