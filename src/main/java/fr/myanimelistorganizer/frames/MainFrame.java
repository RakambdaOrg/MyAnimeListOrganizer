package fr.myanimelistorganizer.frames;

import fr.myanimelistorganizer.Main;
import fr.myanimelistorganizer.enums.Status;
import fr.myanimelistorganizer.frames.components.AnimePanel;
import fr.myanimelistorganizer.frames.components.SearchPanel;
import fr.myanimelistorganizer.utils.MyAnimeListHandler;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
	public final MyAnimeListHandler myal;
	private final AnimePanel watching;
	private final AnimePanel completed;
	private final AnimePanel onHold;
	private final AnimePanel dropped;
	private final AnimePanel planned;

	public MainFrame(MyAnimeListHandler myAnimeListHandler)
	{
		super("MyAnimeList Organizer");
		if(myAnimeListHandler == null)
			System.exit(2);
		myal = myAnimeListHandler;
		this.setIconImages(Main.icons);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new GridBagLayout());
		SearchPanel search = new SearchPanel(this);
		watching = new AnimePanel(this, Status.WATCHING);
		completed = new AnimePanel(this, Status.COMPLETED);
		onHold = new AnimePanel(this, Status.ONHOLD);
		dropped = new AnimePanel(this, Status.DROPPED);
		planned = new AnimePanel(this, Status.PLANNEDTOWATCH);
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab(search.getName(), null, search);
		tabbedPane.addTab(watching.getName(), null, watching);
		tabbedPane.addTab(completed.getName(), null, completed);
		tabbedPane.addTab(onHold.getName(), null, onHold);
		tabbedPane.addTab(dropped.getName(), null, dropped);
		tabbedPane.addTab(planned.getName(), null, planned);
		int line = 0;
		GridBagConstraints gcb = new GridBagConstraints();
		getContentPane().setLayout(new GridBagLayout());
		gcb.anchor = GridBagConstraints.PAGE_START;
		gcb.fill = GridBagConstraints.BOTH;
		gcb.weighty = 100;
		gcb.weightx = 100;
		gcb.gridheight = 1;
		gcb.gridwidth = 1;
		gcb.gridx = 0;
		gcb.gridy = line++;
		getContentPane().add(tabbedPane, gcb);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getPreferredSize().width / 2, dim.height / 2 - this.getPreferredSize().height / 2);
		pack();
		setVisible(true);
	}

	public void updateAll()
	{
		watching.reload();
		completed.reload();
		onHold.reload();
		dropped.reload();
		planned.reload();
	}
}
