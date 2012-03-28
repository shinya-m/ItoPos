package twitter;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author shinya-m
 *
 */
public class OAuthFrame extends JDialog implements ActionListener{

	/**
	 *
	 */
	private static final long serialVersionUID = 8392398249033215287L;
	private JButton button;
	private JTextField text;
	private JLabel label;
	private TwitterAccount tw;

	public OAuthFrame(Frame owner,TwitterAccount tw) throws URISyntaxException,IOException{
		super(owner);
		this.tw=tw;
		setTitle("PINコードの入力");
		getContentPane().setLayout(new FlowLayout());
		getContentPane().add(label=new JLabel("ページに表示されたPINコードを入力してください"));
		getContentPane().add(text=new JTextField(20));
		getContentPane().add(button=new JButton("認証"));
		button.addActionListener(this);
		setSize(350,100);
		setResizable(false);
		setVisible(true);

		//launch browser
		Desktop desktop=Desktop.getDesktop();
		desktop.browse(new URI(tw.getOAuthURL()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		label.setText("認証中");
		try {
			if(tw.makeAccessToken(text.getText()))
				dispose();
			else{
				label.setForeground(Color.RED);
				label.setText("PINコードが正しくありません");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
