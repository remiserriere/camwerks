package com.shamwerks.camwerks.gui;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.OutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import org.jfree.chart.JFreeChart;
import org.jfree.ui.RefineryUtilities;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.shamwerks.camwerks.CamWerks;
import com.shamwerks.camwerks.config.Config;
import com.shamwerks.camwerks.config.Lang;
import com.shamwerks.camwerks.config.LangEntry;
import com.shamwerks.camwerks.pojo.Camshaft;

public class CwMenu extends JMenuBar implements ActionListener, ItemListener {

	private static final long serialVersionUID = -2824721007100872585L;
	private com.itextpdf.text.Document document = null;
    private PdfWriter writer = null;
    
	private static enum MenuAction{
		ACTION_NEW_CAMSHAFT, ACTION_OPEN_FILE, ACTION_SAVE_CSV, ACTION_SAVE_CAM, ACTION_OPEN_FILE_COMPARE, ACTION_CLOSE_FILE, ACTION_EXPORT_PDF;
	}
    
	public CwMenu(){

		//JMenuBar menuBar;
		JMenu menu;//, submenu;
		JMenuItem menuItem;
		//JRadioButtonMenuItem rbMenuItem;
		//JCheckBoxMenuItem cbMenuItem;

		//Create the menu bar.
		//menuBar = new JMenuBar();

		//Build the first menu.
		menu = new JMenu(Lang.getText(LangEntry.MENU_NAME));
		menu.setMnemonic(KeyEvent.VK_M);
		add(menu);

		//a group of JMenuItems
		menuItem = new JMenuItem(Lang.getText(LangEntry.MENU_NEW_CAMSHAFT), KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_N, ActionEvent.CTRL_MASK ));
		menuItem.setActionCommand(MenuAction.ACTION_NEW_CAMSHAFT.name());
		menuItem.addActionListener(this);
		menu.add(menuItem); 

		menuItem = new JMenuItem(Lang.getText(LangEntry.MENU_OPEN_FILE), KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_O, ActionEvent.CTRL_MASK ));
		menuItem.setActionCommand(MenuAction.ACTION_OPEN_FILE.name());
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem(Lang.getText(LangEntry.MENU_CLOSE_FILE), KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_W, ActionEvent.CTRL_MASK ));
		menuItem.setActionCommand(MenuAction.ACTION_CLOSE_FILE.name());
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem(Lang.getText(LangEntry.MENU_SAVE_CAM), KeyEvent.VK_T);
		menuItem.setActionCommand(MenuAction.ACTION_SAVE_CAM.name());
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem(Lang.getText(LangEntry.MENU_SAVE_CSV), KeyEvent.VK_T);
		menuItem.setActionCommand(MenuAction.ACTION_SAVE_CSV.name());
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem(Lang.getText(LangEntry.MENU_OPEN_FILE_COMPARE_TO), KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_O, ActionEvent.CTRL_MASK ));
		menuItem.setActionCommand(MenuAction.ACTION_OPEN_FILE_COMPARE.name());
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem(Lang.getText(LangEntry.MENU_EXPORT_PDF), KeyEvent.VK_T);
		menuItem.setAccelerator(KeyStroke.getKeyStroke( KeyEvent.VK_P, ActionEvent.CTRL_MASK ));
		menuItem.setActionCommand(MenuAction.ACTION_EXPORT_PDF.name());
		menuItem.addActionListener(this);
		menu.add(menuItem);


		/*
		//Build the TEST menu.
		menu = new JMenu("test");
		menu.setMnemonic(KeyEvent.VK_A);
		add(menu);
		
		menuItem = new JMenuItem("Both text and icon", new ImageIcon("images/middle.gif"));
		menuItem.setMnemonic(KeyEvent.VK_B);
		menu.add(menuItem);

		menuItem = new JMenuItem(new ImageIcon("images/middle.gif"));
		menuItem.setMnemonic(KeyEvent.VK_D);
		menu.add(menuItem);

		//a group of radio button menu items
		menu.addSeparator();
		ButtonGroup group = new ButtonGroup();
		rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
		rbMenuItem.setSelected(true);
		rbMenuItem.setMnemonic(KeyEvent.VK_R);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		rbMenuItem = new JRadioButtonMenuItem("Another one");
		rbMenuItem.setMnemonic(KeyEvent.VK_O);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		//a group of check box menu items
		menu.addSeparator();
		cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
		cbMenuItem.setMnemonic(KeyEvent.VK_C);
		menu.add(cbMenuItem);

		cbMenuItem = new JCheckBoxMenuItem("Another one");
		cbMenuItem.setMnemonic(KeyEvent.VK_H);
		menu.add(cbMenuItem);

		//a submenu
		menu.addSeparator();
		submenu = new JMenu("A submenu");
		submenu.setMnemonic(KeyEvent.VK_S);

		menuItem = new JMenuItem("An item in the submenu");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_2, ActionEvent.ALT_MASK));
		submenu.add(menuItem);

		menuItem = new JMenuItem("Another item");
		submenu.add(menuItem);
		menu.add(submenu);
		*/

		//Build second menu in the menu bar.
		menu = new JMenu("?");
		menu.setMnemonic(KeyEvent.VK_N);
		menu.getAccessibleContext().setAccessibleDescription( "This menu does nothing");
		add(menu);

		//A Propos
		menuItem = new JMenuItem(Lang.getText(LangEntry.MENU_HELP_ABOUT), KeyEvent.VK_T);
		menu.add(menuItem);

	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("MENU " + e.getActionCommand() );
		switch (MenuAction.valueOf(e.getActionCommand())){
			case ACTION_NEW_CAMSHAFT      : actionNewCamshaft();       break; 
			case ACTION_OPEN_FILE         : actionOpenFile();          break;
			case ACTION_SAVE_CAM          : actionSaveCam();           break;
			case ACTION_SAVE_CSV          : actionSaveCsv();           break;
			case ACTION_OPEN_FILE_COMPARE : actionOpenFileCompareTo(); break;
			case ACTION_CLOSE_FILE        : actionCloseFile();         break;
			case ACTION_EXPORT_PDF        : actionExportPdf(); 		       break;
		}


	}
	public void actionExportPdf() {
		if (CamWerks.getInstance().getCamshaft() == null) {
			JOptionPane.showMessageDialog(null, 
					Lang.getText(LangEntry.MENU_EXPORT_PDF_NOCAMDEFINED), 
                    Lang.getText(LangEntry.MENU_EXPORT_PDF), 
                    JOptionPane.INFORMATION_MESSAGE);
		}else {
			FileDialog fdpdf = new FileDialog(CamWerks.getInstance().getFrame(), Lang.getText(LangEntry.MENU_EXPORT_PDF), FileDialog.SAVE);
			fdpdf.setFile(CamWerks.getInstance().getCamshaft().getName() + ".pdf");
			fdpdf.setVisible(true);
			
			String filename = fdpdf.getFile();
			if (filename != null){
				if (!filename.endsWith(".pdf")) filename += ".pdf";
				try {
					generatePdf(new FileOutputStream(new File(fdpdf.getDirectory() + filename)));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	public java.awt.Image getImageFromComponent(javax.swing.JComponent component, int camshaftDetailsHeightSelect, int camshaftDetailsWidthSelect) throws DocumentException {
       // BufferedImage image = new BufferedImage(component.getWidth(), camshaftDetailsHeightSelect, BufferedImage.TYPE_INT_RGB);
        BufferedImage image = new BufferedImage(camshaftDetailsWidthSelect, camshaftDetailsHeightSelect, BufferedImage.TYPE_INT_RGB);
        component.paint(image.getGraphics());
        return image;
    }
	
	public void generatePdf(OutputStream outputStream) throws DocumentException, IOException {         
        try {
        	
            //instantiate document and writer
            document = new com.itextpdf.text.Document();
            writer = PdfWriter.getInstance(document, outputStream);
            document.open();
            
            //document metadata
            document.addCreationDate();
            document.addAuthor("CamWerks " + Config.VERSION);
            document.addTitle(CamWerks.getInstance().getCamshaft().getName());
            
            //getting document printable sizes
            int docPrintableWidth = (int) (document.getPageSize().getWidth() - (document.leftMargin() + document.rightMargin()));
            int docPrintableHeight = (int) (document.getPageSize().getHeight() - (document.topMargin() + document.bottomMargin()));
            int camshaftChartHeight = 300;
            int camshaftDetailsHeightSelect = 583;
            int camshaftDetailsWidthSelect = 649;
            int camshaftDetailsHeight = docPrintableHeight - camshaftChartHeight;

            //getting camshaft details image
    		java.awt.Image img = getImageFromComponent(CwPanelDetails.getCamshaftDetails(), camshaftDetailsHeightSelect, camshaftDetailsWidthSelect);
    		Image camshaftDetailsImg = Image.getInstance(writer, img,  1f);
    		camshaftDetailsImg.scaleAbsoluteHeight(camshaftDetailsHeight);
    		camshaftDetailsImg.scaleAbsoluteWidth(docPrintableWidth);
            document.add(camshaftDetailsImg);
            
            //getting camshaft chart image
            JFreeChart chart = CwPanelLineChart.getChart();
            chart.setTitle("");
            //Image image = Image.getInstance(writer, chart.createBufferedImage(width, height), 1.0f);
            document.add(Image.getInstance(writer, chart.createBufferedImage(docPrintableWidth, camshaftChartHeight), 1.0f));
             
            //release resources
            document.close();
            document = null;
            writer.close();
            writer = null;
        } catch(DocumentException de) {
            throw de;
        } catch (IOException ioe) {
            throw ioe;
        } finally {
            //release resources
            if(null != document) {
                try { document.close(); }
                catch(Exception ex) { }
            }
             
            if(null != writer) {
                try { writer.close(); }
                catch(Exception ex) { }
            }
        }
    }

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}

	private void actionNewCamshaft(){
		try {
			CwNewCamshaft dialog = new CwNewCamshaft();
			dialog.setLocationRelativeTo( CamWerks.getInstance().getFrame() );
			dialog.setVisible(true); //Blocking!!
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void actionOpenFile(){
		FileDialog fd = new FileDialog(CamWerks.getInstance().getFrame(), Lang.getText(LangEntry.OPEN_FILE_CHOOSE), FileDialog.LOAD);
		fd.setFile("*.cam");
		RefineryUtilities.centerFrameOnScreen(fd);
		fd.setVisible(true);
		String filename = fd.getFile();
		if (filename != null){
			CamWerks.getInstance().setCamshaft( Camshaft.parseCamFile(fd.getDirectory() + filename) );
			CamWerks.getInstance().getFrame().updateCamshaftDisplay();
		}
	}

	private void actionOpenFileCompareTo(){
		FileDialog fd = new FileDialog(CamWerks.getInstance().getFrame(), Lang.getText(LangEntry.OPEN_FILE_CHOOSE), FileDialog.LOAD);
		fd.setFile("*.cam");
		RefineryUtilities.centerFrameOnScreen(fd);
		fd.setVisible(true);
		String filename = fd.getFile();
		if (filename != null){
			CamWerks.getInstance().setCamshaftCompareTo( Camshaft.parseCamFile(fd.getDirectory() + filename) );
			CamWerks.getInstance().getFrame().updateCamshaftDisplay();
		}
	}
	
	private void actionCloseFile(){
		//TODO
		System.out.println("TO DO!");
	}

	private void actionSaveCsv(){
		FileDialog fd = new FileDialog(CamWerks.getInstance().getFrame(), Lang.getText(LangEntry.MENU_SAVE_CSV), FileDialog.SAVE);
		fd.setFile("*.csv");
		fd.setVisible(true);
		String filename = fd.getFile();
		if (filename != null){
			CamWerks.getInstance().getCamshaft().saveAsCSV(fd.getDirectory() + filename);
		}
	}
	
	private void actionSaveCam(){
		FileDialog fd = new FileDialog(CamWerks.getInstance().getFrame(), Lang.getText(LangEntry.MENU_SAVE_CSV), FileDialog.SAVE);
		fd.setFile("*.cam");
		fd.setVisible(true);
		String filename = fd.getFile();
		if (filename != null){
			CamWerks.getInstance().getCamshaft().saveAsCAM(fd.getDirectory() + filename);
		}
	}
	
}
