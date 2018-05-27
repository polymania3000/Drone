import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;

public class DroneApp {
	public static JFrame g_FrameMain = new JFrame("Drone");
	public static JPanel g_Panel;
	public static CTask g_TaskControl = new CTask();
	
	public static void main(String[] args) {
		Timer l_time = new Timer();
		System.out.println("System Start!");
		  
        g_FrameMain.setSize(800, 600);
        g_FrameMain.setExtendedState( g_FrameMain.getExtendedState()|JFrame.MAXIMIZED_BOTH );
        //g_FrameMain.getContentPane().setBackground(Color.black);
        
        g_Panel = new JPanel() {
            static final long serialVersionUID = 1L;
            
            // ��дpaint����
            
            @Override
            public void paint(Graphics graphics) {
                // �����ȵ��ø����paint����
                super.paint(graphics);
                // �û���Graphics���ڻ���JPanel�ϻ�һ��С��
                graphics.drawOval(100, 70, 30, 30);// ͷ������Բ�Σ�
                graphics.drawRect(105, 100, 20, 30);// ���壨�����Σ�
                graphics.drawLine(105, 100, 75, 120);// ��ۣ���ֱ�ߣ�
                graphics.drawLine(125, 100, 150, 120);// �ұۣ���ֱ�ߣ�
                graphics.drawLine(105, 130, 75, 150);// ���ȣ���ֱ�ߣ�
                graphics.drawLine(125, 130, 150, 150);// ���ȣ���ֱ�ߣ�
                
                g_TaskControl.getCDisplay().paint(graphics);
            }
        };
        
        //g_Panel.setBackground(Color.BLACK);
        g_FrameMain.add(g_Panel);
        g_FrameMain.setVisible(true);
        
        g_FrameMain.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.out.println("System Quit!");
                System.exit(0);
            }
        });
        
        l_time.schedule(g_TaskControl, 0, 1);
        
    }
}
