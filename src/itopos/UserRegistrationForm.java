package itopos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import db.UserDao;

import obj.Customer;


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
public class UserRegistrationForm extends JDialog{
	private JLabel nameLabel;
	private JTextField name;
	private JLabel costLabel;
	private JLabel id;
	private JButton cancel;
	private JButton ok;
	private JTextField nickname;
	private JLabel nicknameLabel;
	private JComboBox type;
	private JLabel typeLabel;
	private JLabel idLabel;
	private JTextField cost;

	public UserRegistrationForm(String felicaId,UserDao dao) {
		initGUI(felicaId,dao);
	}
	
	private void initGUI(final String felicaId,final UserDao dao) {
		try {
			GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
			getContentPane().setLayout(thisLayout);
			this.setSize(258, 259);
			{
				nameLabel = new JLabel();
				nameLabel.setText("\u540d\u524d");
			}
			{
				name = new JTextField();
			}
			{
				costLabel = new JLabel();
				costLabel.setText("\u91d1\u984d");
			}
			{
				cost = new JTextField();
			}
			{
				idLabel = new JLabel();
				idLabel.setText("Felica ID:");
			}
			{
				id = new JLabel();
				id.setText(felicaId);
			}
			{
				typeLabel = new JLabel();
				typeLabel.setText("\u30bf\u30a4\u30d7");
			}
			{
				ComboBoxModel typeModel = 
					new DefaultComboBoxModel(
							new String[] { "student", "teacher" });
				type = new JComboBox();
				type.setModel(typeModel);
			}
			{
				nicknameLabel = new JLabel();
				nicknameLabel.setText("\u3042\u3060\u540d");
			}
			{
				nickname = new JTextField();
			}
			{
				ok = new JButton();
				ok.setText("\u8ffd\u52a0");
				ok.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Customer user=new Customer();
						try{
							user.setName(name.getText());
							user.setCost(Integer.parseInt(cost.getText()));
							user.setFelicaId(felicaId);
							user.setType((String)type.getSelectedItem());
							user.setNickName(nickname.getText());
							dao.addUser(user);
							dispose();
						}catch(Exception e1){
							e1.printStackTrace();
						}
					}
				});
			}
			{
				cancel = new JButton();
				cancel.setText("\u30ad\u30e3\u30f3\u30bb\u30eb");
				cancel.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
			}
			thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(name, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(nameLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(cost, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(costLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(idLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(id, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(type, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(typeLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(nickname, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(nicknameLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    .addComponent(ok, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				    .addComponent(cancel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
				.addContainerGap());
			thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
				.addContainerGap()
				.addGroup(thisLayout.createParallelGroup()
				    .addGroup(thisLayout.createSequentialGroup()
				        .addGroup(thisLayout.createParallelGroup()
				            .addComponent(idLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
				                .addGap(12))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(costLabel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
				                .addGap(14))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(typeLabel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
				                .addGap(14))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(nicknameLabel, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
				                .addGap(14)))
				        .addGroup(thisLayout.createParallelGroup()
				            .addGroup(thisLayout.createSequentialGroup()
				                .addComponent(name, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
				            .addGroup(thisLayout.createSequentialGroup()
				                .addComponent(cost, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
				            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				                .addComponent(id, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
				                .addGap(18)
				                .addComponent(cancel, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
				            .addGroup(thisLayout.createSequentialGroup()
				                .addComponent(type, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
				            .addGroup(thisLayout.createSequentialGroup()
				                .addComponent(nickname, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)))
				        .addGap(0, 0, Short.MAX_VALUE))
				    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
				        .addGap(29)
				        .addComponent(ok, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
				        .addGap(0, 109, Short.MAX_VALUE)))
				.addContainerGap(28, 28));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
