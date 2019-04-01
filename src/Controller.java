import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {

	private Model model;
	private View view;
	private int frameChecker; // this is used to track how many frames have passed since an animation has started
							// once all 4 frames of the fire animation have happened, we would like to return to the normal animation.
	
	public Controller(){
		view = new View(this);
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		view.addModel(model);
		frameChecker=0;
	}
	
        //run the simulation
	public void start(){
		for(int i = 0; i < 5000; i++){
			
			/*
			 * chceking how many frames have happened since we have started our jump, or fire animation.
			 * and resetting our model action to none after the animation of the action is complete.
			 */
			if(model.getAction()!=0){
				frameChecker++;
				if(model.getAction() == 1 && frameChecker >= 8){
					model.none();
					frameChecker=0;
				}
				if(model.getAction() == 2 && frameChecker >= 4){
					model.none();
					frameChecker=0;
				}
			}
			//increment the x and y coordinates, alter direction if necessary
			model.updateLocationAndDirection();
			//update the view
			
			
			
			/*
			 * MVC means:
			 * The View is the representation of the model
			 * and the controller is what changes the model
			 * So the controller modifies the model, and the view
			 * updates at some interval pulling updates from the model.
			 * 
			 */
			view.update(model.getX(), model.getY(), model.getDirect());
			
		}
	}
	
	/*
	 * connection of the view's button to the controller
	 * so we can update the model
	 */
	public void stopGoOnClick(){
		model.setMoving(!model.getMoving());
	}

	
	/*
	 * two cases for our keypresses so far, fire, and jump.
	 */
	@Override
	public void keyPressed(KeyEvent kv) {
		int key = kv.getKeyCode();
		switch(key){
		case KeyEvent.VK_F : model.fire();break;
		case KeyEvent.VK_J : model.jump();break;
		}
	}

	@Override
	public void keyReleased(KeyEvent kv) {
		//unused
	}

	@Override
	public void keyTyped(KeyEvent kv) {
		//unused
		
	}
}
