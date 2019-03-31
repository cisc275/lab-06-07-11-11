import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/

public class View extends JPanel{
	private static final long serialVersionUID = 1L; //pointless as far as I'm concerned.
	private final String[] fnames = {"images/orc/orc_forward_southeast.png",
									"images/orc/orc_forward_south.png",
									"images/orc/orc_forward_southwest.png",
									"images/orc/orc_forward_west.png",
									"images/orc/orc_forward_northwest.png",
									"images/orc/orc_forward_north.png",
									"images/orc/orc_forward_northeast.png",
									"images/orc/orc_forward_east.png"}; // just to make life easier, an array of the different file names.
	private final int frameCount = 10; // # of frames per animation
	private final int frameWidth = 500; // pixel width of the screen
	private final int frameHeight = 300; // pixel height of the screen
	private final int imgWidth = 165; //pixel width of the image
	private final int imgHeight = 165; // pixel height of the image
	private HashMap<String,Direction> stringMap; //just to make life easier, a hashmap that we will use with our file names to return a direction.
	private HashMap<Direction,BufferedImage[]> animationMap;//the hashmap where the keys are the direction enum, and the values are the 
																//10-frame buffered image arrays.
	private JFrame frame;//our frame
	private int xloc; // x location
	private int yloc; // y location
	private Direction d = Direction.SOUTHEAST; // direction with default on southeast
	private int picNum = 0; // the current image of the current animation.
	
	/*
	 * Constructor for our view, initializes stringmap, animation map, and all other attributes.
	 */
	public View(){
		System.out.println("CREATED NEW VIEW");
		stringMap = new HashMap<>();
		animationMap = new HashMap<>();
		xloc=0;
		yloc=0;
		d=Direction.EAST;
		stringMapCreate();
		loadImages();
		

		
		//JFrame 
		frame = new JFrame();
		frame.setBackground(Color.gray);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(frameWidth, frameHeight+50);
		Container current_layout = frame.getContentPane(); //get's the JFrame's BorderLayout Container to add elements
		
		
		//This (JPanel)
		this.setBackground(Color.gray); //set's background of this view to gray
		
		
		
		//JButton
		JButton movement_button = new JButton("Stop/Move");
		movement_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				;
			}
		});
		
		//Adding the this(JPanel) and JButton to the JFrame's container 
		current_layout.add(this, BorderLayout.PAGE_START);
		current_layout.add(movement_button, BorderLayout.PAGE_END);
		
		//Makes the JFrame visible (and all of the components)
		frame.setVisible(true);
		
	}
	/*
	 * same exact thing as animationMap.get(d);
	 */
	public BufferedImage[] getAnimation(Direction d){
		return animationMap.get(d);
	}
	/*
	 * 
	 * overrided paint method that gets called from update.
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g); //IMPORTANT: tells the super class' paintComponent to draw the background gray 
								//otherwise background stays white
		
		System.out.println("PAINT"); //testing
		
		picNum = (picNum + 1) % frameCount;
		g.setColor(Color.gray);
		g.drawImage(getAnimation(d)[picNum], xloc, yloc, Color.gray, this);
	}
	/*
	 * sends the xloc, yloc, and direction to the attributes, and calls repaint.
	 */
	public void updategame(int xloc,int yloc,Direction d){
		System.out.println("UPDATE");
		this.xloc=xloc;
		this.yloc=yloc;
		this.d=d;
		
		frame.repaint(); //repaints the whole JFrame and all of its components
		
		System.out.println(frame.getBackground().toString());
		
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	/*
	 * just to make life easier, the creation of our string map.
	 */
	private void stringMapCreate(){
		stringMap.put("images/orc/orc_forward_southeast.png",Direction.SOUTHEAST);
		stringMap.put("images/orc/orc_forward_south.png",Direction.SOUTH);
		stringMap.put("images/orc/orc_forward_southwest.png",Direction.SOUTHWEST);
		stringMap.put("images/orc/orc_forward_west.png",Direction.WEST);
		stringMap.put("images/orc/orc_forward_northwest.png",Direction.NORTHWEST);
		stringMap.put("images/orc/orc_forward_north.png",Direction.NORTH);
		stringMap.put("images/orc/orc_forward_northeast.png",Direction.NORTHEAST);
		stringMap.put("images/orc/orc_forward_east.png",Direction.EAST);
	}
	/*
	 * loads all of the images that we are using and puts them into the animationMap.
	 */
	private void loadImages(){
		for(int i = 0;i<fnames.length;i++){
			BufferedImage[] animation = loadAnimation(fnames[i]);
			animationMap.put(stringMap.get(fnames[i]), animation);
		}
	}
	/*
	 * Loads a single animation (10 frames) and returns it.
	 */
	private BufferedImage[] loadAnimation(String fname){
		BufferedImage bufferedImage=null;
    	try {
    		bufferedImage = ImageIO.read(new File(fname));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
		BufferedImage[] output = new BufferedImage[frameCount];
		for(int i = 0;i<frameCount;i++){
			output[i]=bufferedImage.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
		}
		
		return output;
	}
	
	public int getWidth(){
		return frameWidth;
	}
	public int getHeight(){
		return frameHeight;
	}
	public int getImageWidth(){
		return imgWidth;
	}
	public int getImageHeight(){
		return imgHeight;
	}
	
	
	
}
