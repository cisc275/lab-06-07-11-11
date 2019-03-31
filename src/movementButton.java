import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class movementButton extends JButton{
	private int currentPicNum;
	
	//SetVelocity
	private int vx = 0;
	private int vy = 0;
	
	//Current velocity of Model
	private int current_vx;
	private int current_vy;
	
	movementButton(String text){
		super(text);
	}
	
	public void setVelocity(int vx, int vy) {
		this.vx = vx;
		this.vy = vy;
	}
	
	//updates the current Model's velocity (to later test if its moving)
	public int[] stopMove() {
		int[] velocity = new int[2]; //int array storing the velocity
		
		this.current_vx = this.vx;
		this.current_vy = this.vy;
		
		//checks to see if the Model is moving
		if(Math.abs(this.vx)>0 || Math.abs(this.vy)>0) {
			this.current_vx = this.vx;
			this.current_vy = this.vy;
			
			//sets velocity to velx = 0, vely = 0; (NOT MOVING)
			velocity[0] = 0;
			velocity[1] = 0;		
		}
		//Assumes that model is Not moving and returns the old velocity
		else {
			//sets velocity to the old velx, vely
			velocity[0] = this.current_vx;
			velocity[1] = this.current_vy;
		}
		
		System.out.println(velocity);
		
		return velocity;
	}
	
	
	
	
	
	
	
}
