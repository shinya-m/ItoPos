package itopos;
import java.awt.Frame;

import javax.swing.GroupLayout;
import javax.swing.JComponent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;


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
public class ShoppingBasketDialog extends JDialog{

	private static String[] column={"item","price"};
	private DefaultTableModel model;
	private JLabel totalLabel;
	private JLabel money;
	private JLabel textLabel;
	private JLabel totalPrice;
	private int total;

	public ShoppingBasketDialog(Frame owner,int shojikin){
		super(owner);
		setTitle("Shopping Basket");
		GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
		getContentPane().setLayout(thisLayout);
		setLocationRelativeTo(null);
		model=new DefaultTableModel(column,0);
		JTable table=new JTable(model);
		JScrollPane scroll=new JScrollPane(table);
		{
			totalPrice = new JLabel();
			total=0;
			setTotalCost(0);
			totalPrice.setFont(new java.awt.Font("メイリオ",0,16));
		}
		{
			totalLabel = new JLabel();
			totalLabel.setText("Total");
			totalLabel.setFont(new java.awt.Font("メイリオ",0,16));
		}
		{
			textLabel = new JLabel();
			textLabel.setText("Now, you have");
			textLabel.setFont(new java.awt.Font("メイリオ",0,16));
		}
		{
			money = new JLabel();
			setMoney(shojikin);
			money.setFont(new java.awt.Font("メイリオ",0,16));
		}
		thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
			.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
			.addGap(20)
			.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    .addComponent(totalPrice, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
			    .addComponent(totalLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
			.addGap(26)
			.addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			    .addComponent(money, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
			    .addComponent(textLabel, GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
			.addContainerGap(43, 43));
		thisLayout.setHorizontalGroup(thisLayout.createParallelGroup()
			.addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
			    .addComponent(scroll, GroupLayout.PREFERRED_SIZE, 383, GroupLayout.PREFERRED_SIZE)
			    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE))
			.addGroup(thisLayout.createSequentialGroup()
			    .addGap(48)
			    .addGroup(thisLayout.createParallelGroup()
			        .addComponent(textLabel, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
			        .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
			            .addGap(62)
			            .addComponent(totalLabel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
			            .addGap(18)))
			    .addGap(40)
			    .addGroup(thisLayout.createParallelGroup()
			        .addGroup(thisLayout.createSequentialGroup()
			            .addComponent(money, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
			        .addGroup(thisLayout.createSequentialGroup()
			            .addComponent(totalPrice, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)))
			    .addContainerGap(33, Short.MAX_VALUE)));
		setVisible(true);
		pack();
		this.setSize(400, 371);
	}
	
	/**
	 * @param data String[]{商品名、金額}
	 */
	public void add(Object[] data){
		model.addRow(data);
		setTotalCost(Integer.parseInt((String)data[1]));
	}
	
	/**
	 * 買い物カゴの合計金額
	 * @param n
	 */
	public void setTotalCost(int n){
		total+=n;
		totalPrice.setText("￥"+total);
	}
	
	/**
	 * 現在の所持金
	 * @param n
	 */
	public void setMoney(int n){
		money.setText("￥"+n);
	}
}
