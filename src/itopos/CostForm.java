package itopos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import obj.Customer;
import twitter.TwitterAccount;

import db.UserDao;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
/**
 * @author shinya-m
 *
 */
@SuppressWarnings("serial")
public class CostForm extends JDialog{
	private JTextField cost;
	private JButton ok;
	private TwitterAccount tw;

	public CostForm(UserDao dao,Customer user,ItoPosFrame frame, TwitterAccount tw) {
		initGUI(dao,user,frame);
		setLocationRelativeTo(null);
		setVisible(true);
		this.tw = tw;
	}

	private void initGUI(final UserDao dao,final Customer user,final ItoPosFrame frame) {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			{
				cost = new JTextField();
			}
			{
				ok = new JButton();
				ok.setText("\u8ffd\u52a0");
				ok.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						try {
							int extra=Integer.parseInt(cost.getText());
							dao.updateUser(user.getFelicaId(),user.getCost()+extra,user.getAllConsumedPoint());
							dispose();
							frame.resetSystem();
							frame.requestResetSystem(1,true);

							// tweet
							int x = user.getCost() + extra;
							String s=user.getNickName()+"さんが";
                            String ss="円チャージしました！";
                            String sss=user.getNickName()+"さんの残金は" + x + "円です。";
                            String tweet=s+ extra +ss+sss;
                            tw.tweet(tweet);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap(18, 18)
				.addComponent(cost, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 1, Short.MAX_VALUE)
				.addComponent(ok, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				.addContainerGap());
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addComponent(cost, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 0, Short.MAX_VALUE))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGap(29)
				        .addComponent(ok, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 32, Short.MAX_VALUE)))
				.addContainerGap());
			{
				this.setSize(165, 138);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
