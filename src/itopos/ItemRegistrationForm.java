package itopos;

import java.awt.Frame;
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

import db.ItemDao;

import obj.Item;


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
public class ItemRegistrationForm extends JDialog{

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	private JTextField name;
	private JTextField cost;//仕入れ値
	private JLabel barcode;
	private JLabel barcode_label;
	private JLabel type;
	private JLabel sold_label;
	private JTextField num;//個数
	private JLabel num_label;
	private JComboBox type_box;//種類
	private JLabel cost_label;
	private JLabel goods_name;
	private JTextField sold_cost;//売値
	private JButton ok,cancel;
	private static final String[] TYPE={"お菓子","飲み物","カップ麺","その他"};
	
	public ItemRegistrationForm(final ItemDao dao,Frame owner,final String bcode) {
		super(owner);
		GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
		getContentPane().setLayout(thisLayout);
		setTitle("Item registration");
		setLocationRelativeTo(null);
		setVisible(true);
		this.setSize(329, 298);
		{
			goods_name = new JLabel();
			goods_name.setText("\u5546\u54c1\u540d");
		}
		{
			name = new JTextField();
		}
		{
			barcode = new JLabel();
			barcode.setText("\u30d0\u30fc\u30b3\u30fc\u30c9");
		}
		{
			barcode_label = new JLabel();
			barcode_label.setText(bcode);
		}
		{
			sold_label = new JLabel();
			sold_label.setText("\u58f2\u5024");
		}
		{
			sold_cost = new JTextField();
		}
		{
			ok = new JButton();
			ok.setText("\u8ffd\u52a0");
			ok.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String hogename=name.getText();
					String hogecost=cost.getText();
					String hogenum=num.getText();
					String hogesold=sold_cost.getText();
					if(hogename.length()>0 && hogecost.length()>0 && 
							hogenum.length()>0 && hogesold.length()>0){
						Item item=new Item();
						try{
							item.setBarcode(bcode);
							item.setCost(Integer.parseInt(hogecost));
							item.setName(hogename);
							item.setPro_num(Integer.parseInt(hogenum));
							item.setSold_cost(Integer.parseInt(hogesold));
							item.setType((String)type_box.getSelectedItem());
							dao.addBuppin(item);
							dispose();
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
			});
		}
		{
			cancel = new JButton();
			cancel.setText("\u30ad\u30e3\u30f3\u30bb\u30eb");
			cancel.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		{
			cost_label = new JLabel();
			cost_label.setText("\u4ed5\u5165\u308c\u5024");
		}
		{
			cost = new JTextField();
		}
		{
			type = new JLabel();
			type.setText("\u7a2e\u985e");
		}
		{
			ComboBoxModel type_boxModel = 
				new DefaultComboBoxModel(TYPE);
			type_box = new JComboBox();
			type_box.setModel(type_boxModel);
		}
		{
			num_label = new JLabel();
			num_label.setText("\u500b\u6570");
		}
		{
			num = new JTextField();
		}
		thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
			.addContainerGap()
			.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    .addComponent(barcode, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			    .addComponent(barcode_label, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    .addComponent(name, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
			    .addComponent(goods_name, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    .addComponent(cost, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
			    .addComponent(cost_label, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
			.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    .addComponent(type_box, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
			    .addComponent(type, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
			.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
			.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    .addComponent(num, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
			    .addComponent(num_label, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
			.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
			.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    .addComponent(sold_cost, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
			    .addComponent(sold_label, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
			.addGap(20)
			.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    .addComponent(ok, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
			    .addComponent(cancel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
			.addContainerGap());
		thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
			.addContainerGap()
			.addGroup(thisLayout.createParallelGroup()
			    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
			        .addGroup(thisLayout.createParallelGroup()
			            .addComponent(num_label, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
			            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
			                .addComponent(sold_label, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
			                .addGap(24))
			            .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
			                .addComponent(type, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
			                .addGap(22)))
			        .addComponent(ok, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
			        .addGap(24)
			        .addComponent(cancel, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
			        .addGap(0, 26, Short.MAX_VALUE))
			    .addGroup(thisLayout.createSequentialGroup()
			        .addGroup(thisLayout.createParallelGroup()
			            .addComponent(cost_label, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
			            .addComponent(goods_name, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
			            .addComponent(barcode, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
			        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
			        .addGroup(thisLayout.createParallelGroup()
			            .addGroup(thisLayout.createSequentialGroup()
			                .addComponent(sold_cost, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
			            .addGroup(thisLayout.createSequentialGroup()
			                .addComponent(num, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
			            .addGroup(thisLayout.createSequentialGroup()
			                .addComponent(type_box, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))
			            .addGroup(thisLayout.createSequentialGroup()
			                .addComponent(cost, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
			            .addGroup(thisLayout.createSequentialGroup()
			                .addComponent(name, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
			            .addGroup(thisLayout.createSequentialGroup()
			                .addComponent(barcode_label, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)))
			        .addGap(0, 0, Short.MAX_VALUE)))
			.addContainerGap(18, 18));
	}
}
