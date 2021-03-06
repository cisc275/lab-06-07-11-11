import java.awt.image.BufferedImage;

/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/
public class Model{
	Direction direct; //direction(enum)
	private int action = 0; //for now, this will be an integer. however, this should be an enumerated type.
							//it represents the current action of our orc.
							//0 = normal
							//1 = jump
							//2 = fire
	private int xloc = 0; //xlocation 
	private int yloc = 0; //y location 
	private final int xIncr = 8;//x increment
    private final int yIncr = 2;//y increment
    private int xMult = 1; //xMult and yMult are essentially another representation of the direction
    					   //they are unneccessary, could just be checking our direction and extrapolating these, its 
    					   //just easier this way.
    private int yMult = 1;
	private int frameWidth; //pixel width of the screen
	private int frameHeight; // pixel height of the screen
	private int imgWidth;//pixel width of the image
	private int imgHeight; // pixel height of the image
	
	//LAB6
	private boolean isMoving; //Keeps track whether or not the orc is moving
	
	
	/*
	 * constructor for our model, takes in data that will come from our view.
	 */
	public Model(int width, int height, int imgWidth, int imgHeight){
		frameWidth=width;
		frameHeight=height;
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
		isMoving=true;
	}
	//0 = normal
	//1 = jump
	//2 = fire
	//wrapper methods for changing our action attribute
	public void fire(){
		setAction(2);
	}
	public void jump(){
		setAction(1);
	}
	public void none(){
		setAction(0);
	}
	private void setAction(int a){
		this.action=a;
	}
	/*
	 * xMult and yMult make this trivial.
	 * changes the direction based on the boundaries of the screen, and 
	 * changes the location based on the current direction.
	 */
	public void updateLocationAndDirection(){
		//if the orc is moving then update its movement otherwise do not do anything
		if(getMoving()) {
			if(xloc>=(frameWidth-imgWidth)){
				xMult=-1;
			}
			if(yloc>=(frameHeight-imgHeight)){
				yMult=-1;
			}
			if(xloc+xIncr<0){
				xMult=1;
			}
			if(yloc+yIncr<0){
				yMult=1;
			}
			updateDirection();
			xloc+=xIncr*xMult;
			yloc+=yIncr*yMult;
		}
		
	}
	/*
	 * modifies the direction based on the xMult and yMult.
	 */
	public void updateDirection(){
		if(xMult == 1 && yMult == 1){
			direct=Direction.SOUTHEAST;
    	}else if(xMult == 1 && yMult == -1){
    		direct=Direction.NORTHEAST;
    	}else if(xMult==-1&&yMult==1){
    		direct = Direction.SOUTHWEST;
    	}else{
    		direct = Direction.NORTHWEST;
    	}
	}
	
	
	//getters BELOW.
	public Direction getDirect() {
		return direct;
	}

	public int getXloc() {
		return xloc;
	}

	public int getYloc() {
		return yloc;
	}
	
	public int getX(){
		return xloc;
	}
	public int getY(){
		return yloc;
	}

	public int getFrameWidth() {
		return frameWidth;
	}

	public int getFrameHeight() {
		return frameHeight;
	}

	public int getImgWidth() {
		return imgWidth;
	}

	public int getImgHeight() {
		return imgHeight;
	}
	//0 = normal
	//1 = jump
	//2 = fire
	public int getAction(){
		return action;
	}
	//LAB6
	public boolean getMoving() {
		return this.isMoving;
	}
	
	
	public void setMoving(boolean moving) {
		this.isMoving = moving;
	}	
}