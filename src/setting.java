import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

class setting extends JFrame {
	ButtonGroup bg;
	JRadioButton mouse,keyboard;
	public setting() {
		super("Settings");
		add(new JLabel("control options"),BorderLayout.NORTH);
		//setLayout(newFlowLayout());
		setSize(230,100);
		setResizable(false);
		bg=new ButtonGroup();
		mouse=new JRadioButton("Mouse");
		keyboard=new JRadioButton("Keyboard");
		bg.add(mouse);
		bg.add(keyboard);
		mouse.setSelected(true);

		add(mouse,BorderLayout.WEST);

		add(keyboard,BorderLayout.EAST);
		ActionListener a=new
				ActionListener() {

			@Override
			public void
			actionPerformed(ActionEvent e) {

				if(bg.getSelection()==mouse)

					Menu.ismouse=true;
				else

					Menu.ismouse=false;

			}
		};

		mouse.addActionListener(a);

		keyboard.addActionListener(a);

	}

}
