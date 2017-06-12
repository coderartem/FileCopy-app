import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

public class FileCopy implements ActionListener{

	JPanel p = new JPanel();
	JFrame fr = new JFrame("Копирщик");
	JButton b [] = new JButton [4];
	JLabel l [] =new JLabel [4];
	JFormattedTextField tx [] = new JFormattedTextField[4];
	JFileChooser ch = new JFileChooser("Копировать будешь?");
	String [] s1 = {"Browse","Browse","Copy now",""};
	String [] s2 = {"    Copy from:","    Copy to:","",""};
	
		
	FileCopy(){
		for (int i=0; i<b.length; i++){
			b[i] = new JButton(s1[i]);
			b[i].addActionListener(this);
			l[i] = new JLabel(s2[i]);
			tx[i]=new JFormattedTextField();
		}
		ch.setCurrentDirectory(new File("c:"));
		
		p.setLayout(new GridLayout(3,3,5,5));
		p.add(l[0]);
		p.add(tx[0]);
		p.add(b[0]);
		p.add(l[1]);
		p.add(tx[1]);
		p.add(b[1]);
		p.add(l[2]);
		p.add(b[2]);
		p.add(l[3]);
		
		fr.setContentPane(p);
		fr.setBounds(600, 300,0,0);
		fr.pack();
		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent ev){
		String pn = null;
		for(int i=0;i<2;i++){
			if (ev.getSource()==b[i]){
			ch.showOpenDialog(new JFileChooser());
			pn = ch.getSelectedFile().getAbsolutePath();
			tx[i].setText(pn);
		}}
		FileInputStream r =null;
		FileOutputStream w =null;
		BufferedInputStream br = null;
		BufferedOutputStream wr = null;
		
		if (ev.getSource()==b[2]&& !tx[0].getText().equals("")&&!tx[1].getText().equals("")){
			try{
				r=new FileInputStream(tx[0].getText());
				w =new FileOutputStream(tx[1].getText());
				br = new BufferedInputStream(r,5000);
				wr = new BufferedOutputStream(w,5000);
				
				while (true){
				int tt=	br.read();
				if (tt==-1){ break;}else
				wr.write(tt);
				
				
				}
			} catch (IOException e){
				JOptionPane.showConfirmDialog(null,e.toString(),"Ошибка",JOptionPane.PLAIN_MESSAGE);
			}finally{
				try{
					wr.flush();
					r.close();
					br.close();
					w.close();
					wr.close();
				}catch (IOException e1){
					JOptionPane.showConfirmDialog(null,e1.toString(),"Ошибка",JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
	}
	
	public static void main (String [] args){
		new FileCopy();
	}
}
