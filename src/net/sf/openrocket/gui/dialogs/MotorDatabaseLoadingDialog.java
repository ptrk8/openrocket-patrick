package net.sf.openrocket.gui.dialogs;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import net.miginfocom.swing.MigLayout;
import net.sf.openrocket.database.ThrustCurveMotorSetDatabase;
import net.sf.openrocket.logging.LogHelper;
import net.sf.openrocket.startup.Application;
import net.sf.openrocket.util.GUIUtil;

public class MotorDatabaseLoadingDialog extends JDialog {
	private static final LogHelper log = Application.getLogger();
	
	
	private MotorDatabaseLoadingDialog(Window parent) {
		super(parent, "Loading motors", ModalityType.APPLICATION_MODAL);
		
		JPanel panel = new JPanel(new MigLayout("fill"));
		panel.add(new JLabel("Loading motors..."), "wrap para");
		
		JProgressBar progress = new JProgressBar();
		progress.setIndeterminate(true);
		panel.add(progress, "growx");
		
		this.add(panel);
		this.pack();
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationByPlatform(true);
		GUIUtil.setWindowIcons(this);
	}
	
	
	/**
	 * Check whether the motor database is loaded and block until it is.
	 * An uncloseable modal dialog window is opened while loading.
	 * 
	 * @param parent	the parent window for the dialog, or <code>null</code>
	 */
	public static void check(Window parent) {
		final ThrustCurveMotorSetDatabase db = Application.getMotorSetDatabase();
		if (db.isLoaded())
			return;
		
		log.info(1, "Motor database not loaded yet, displaying dialog");
		
		final MotorDatabaseLoadingDialog dialog = new MotorDatabaseLoadingDialog(parent);
		
		final Timer timer = new Timer(100, new ActionListener() {
			private int count = 0;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				count++;
				if (db.isLoaded()) {
					log.debug("Database loaded, closing dialog");
					dialog.setVisible(false);
				} else if (count % 10 == 0) {
					log.debug("Database not loaded, count=" + count);
				}
			}
		});
		
		db.setInUse();
		timer.start();
		dialog.setVisible(true);
		timer.stop();
		
		log.debug("Motor database now loaded");
	}
	
}