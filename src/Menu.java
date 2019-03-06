import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class Menu extends JFrame {
	JList<Score> JlScoreBoard;
	static Score[] Scores=new Score[10];
	JButton jbStart,jbSetting,jbExit;
	JPanel jpcenter,jpeast,jpnorth;
	JTextField txtname;
	static boolean ismouse=true;
	Painter p;
	public Menu() {
		super("Project");
		jpcenter=new JPanel();
		setLayout(new BorderLayout());
		add(new JPanel());//1
		jpnorth=new JPanel(new FlowLayout());
		txtname=new JTextField(" player ");
		jpnorth.add(txtname);
		add(jpnorth,BorderLayout.NORTH);
		add(jpcenter,BorderLayout.CENTER);
		jpeast=new JPanel();
		updateScore();
		jpeast.add(JlScoreBoard);
		add(jpeast,BorderLayout.EAST);
		
		
		jbStart=new JButton("Start the game");


		jbStart.addActionListener(new ActionListener() {
			@Override
			public void
			actionPerformed(ActionEvent e) {
				newGame();
			}
		});
		jbSetting=new JButton("Settings");
		jbSetting.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				setting s=new setting();

				s.setLocation(getWidth()/2-s.getWidth()/2,getHeight()/2-s.getHeight()/2);

				s.setVisible(true);

			}
		});
		jbExit=new JButton("Exit");
		jbExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);

			}
		});
		jpcenter.setLayout(new GridLayout(3,1));
		jpcenter.add(jbStart);
		jpcenter.add(jbSetting);
		jpcenter.add(jbExit);
		setVisible(true);
		setSize(1000,1000); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void newGame() {
		JFrame m = new JFrame();
		m.setUndecorated(true);
		m.setMinimumSize(new Dimension(500, 500));
		m.setSize(getWidth(),getHeight());
		p=new Painter(this);
		m.add(p);
		m.addKeyListener(p);
		m.addMouseListener(p);
		m.setFocusable(true);
		setVisible(false);
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.setVisible(true);
	}
	void addScore(Score s){


		for (int i = 0; i < Scores.length; i++) {
			if(Scores[i]==null)
			{	
				Scores[i]=s;
				break;
			}
			else if(Scores[i].score<s.score)
			{
				for(int j=Scores.length-1;j>i;j--)
				{
					Scores[j]=Scores[j-1];
				}
				Scores[i]=s;
				
				break;
			}

		}
		updateScore();
	}

	void updateScore() {
			if(Scores[0]==null)
				addScore(new Score("Test"));
		
		
		JlScoreBoard=new JList<>(Scores);
		remove(jpeast);
		jpeast=new JPanel();
		jpeast.add(JlScoreBoard);
		add(jpeast,BorderLayout.EAST);
		revalidate();
		repaint();
	}
}
