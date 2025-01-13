package CampusTycoon.UI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Sprite {
	// Contains the locations of where all Component images are stored
	// Uses getters/setters to obsfuscate single images vs animations, and 
	
	private Map<String, AnimationInfo> animationType = new HashMap<String, AnimationInfo>();
	private String selected = "DEFAULT"; // Which method is currently active
	public boolean usesSpriteSheet = false;
	public SpriteSheet spriteSheet = null;
	
	public Sprite(String ImagePath) {
		Initialise();
		setDefaultImage(ImagePath);
	}
	public Sprite(List<String> ImagePaths) {
		Initialise();
		setDefaultAnimation(ImagePaths);
	}
	
	public Sprite(SpriteSheet SpriteSheet, int SpriteID) {
		spriteSheet = SpriteSheet;
		usesSpriteSheet = true;
		Initialise();
		setDefaultImage(SpriteID);
	}
	public Sprite(SpriteSheet SpriteSheet, ArrayList<Integer> SpriteIDs) {
		spriteSheet = SpriteSheet;
		usesSpriteSheet = true;
		Initialise();
		setDefaultAnimation(SpriteIDs);
	}
	
	private void Initialise() {
		animationType.put("DEFAULT", new AnimationInfo()); // Default image
		animationType.put("HOVER", new AnimationInfo()); // To be shown on mouse hover
		animationType.put("CLICK", new AnimationInfo()); // To be shown on click
		animationType.put("CALL", new AnimationInfo()); // To be shown when criteria defined in the Component is met
	}
	
	// I have 0 clue how to reasonably implement keyframe interpolation or any other fancy technique
	// So all an animation is here is just a list of images to cycle through (1 step per frame)
	private class AnimationInfo {
		private List<String> imagePaths;
		private List<Integer> spriteIDs;
		private int imageStep; // Counter for animation frame, will always be 0 for single images
		// Possibly add Time variable, in order to adjust how fast the animation plays
		
		public AnimationInfo() {
			imagePaths = new ArrayList<String>();
			imageStep = 0;
		}
		public AnimationInfo(String ImagePath) {
			imagePaths = new ArrayList<String>();
			imagePaths.add(ImagePath);
			imageStep = 0;
		}
		public AnimationInfo(List<String> ImagePaths) {
			imagePaths = ImagePaths;
			imageStep = 0;
		}
		
		public AnimationInfo(int SpriteID) {
			spriteIDs = new ArrayList<Integer>();
			spriteIDs.add(SpriteID);
			imageStep = 0;
		}
		public AnimationInfo(ArrayList<Integer> SpriteIDs) {
			// SpriteIDs is of type ArrayList rather than List because otherwise java seems to think that List<String> and List<Integer> is the same thing and throws an error
			spriteIDs = SpriteIDs;
			imageStep = 0;
		}
	}
	
	
	private boolean checkSelectionValid(String selection) {
		return animationType.keySet().contains(selection);
	}
	
	private String getInvalidSelectionMessage() {
		Iterator<String> keyIterator = animationType.keySet().iterator();
		String errorMessage = "Invalid Selection.\nSelection must be one of; ";
		for (int i = 0; i < animationType.keySet().size() - 1; i++) {
			errorMessage += "\"" + keyIterator.next() + "\", ";
		}
		errorMessage += "or \"" + keyIterator.next() + "\".";
		return errorMessage;
	}
	
	public void changeSelection(String selection) throws Exception {
		if (!checkSelectionValid(selection)) {
			throw new Exception(getInvalidSelectionMessage());
		}
		
		selected = selection;
	}
	
	public void setImagePath(String ImagePath) {
		this.animationType.put(selected, new AnimationInfo(ImagePath));
	}
	
	public String getImagePath() {
		return selectImage();
	}
	
	public void updateImagePath(String ImagePath) {
		AnimationInfo animInfo = this.animationType.get(selected);
		animInfo = new AnimationInfo(ImagePath);
		animationType.put(selected, animInfo);
	}
	
	private String selectImage() {
		AnimationInfo animInfo = animationType.get(selected);
		if (!usesSpriteSheet) {
			return animInfo.imagePaths.get(animInfo.imageStep);
		}
		return spriteSheet.imagePath;
	}
	
	public void stepAnimation() {
		AnimationInfo animInfo = animationType.get(selected);
		int currentStep = animInfo.imageStep;
		
		if (!usesSpriteSheet) {
			animInfo.imageStep = (currentStep + 1) % animInfo.imagePaths.size();
			return;
		}
		animInfo.imageStep = (currentStep + 1) % animInfo.spriteIDs.size();
	}
	
	public Integer getID() {
		AnimationInfo animInfo = animationType.get(selected);
		return animInfo.spriteIDs.get(animInfo.imageStep);
	}
	
	
	public void setDefaultImage(String imagePath) {
		animationType.replace("DEFAULT", new AnimationInfo(imagePath)); 
	}
	public void setDefaultAnimation(List<String> imagePaths) {
		animationType.replace("DEFAULT", new AnimationInfo(imagePaths));
	}
	public void setDefaultImage(Integer spriteID) {
		animationType.replace("DEFAULT", new AnimationInfo(spriteID));
	}
	public void setDefaultAnimation(ArrayList<Integer> spriteIDs) {
		animationType.replace("DEFAULT", new AnimationInfo(spriteIDs));
	}
	
	public void setHoverImage(String imagePath) {
		animationType.replace("HOVER", new AnimationInfo(imagePath));
	}
	public void setHoverAnimation(List<String> imagePaths) {
		animationType.replace("HOVER", new AnimationInfo(imagePaths));
	}
	public void setHoverImage(Integer spriteID) {
		animationType.replace("HOVER", new AnimationInfo(spriteID));
	}
	public void setHoverAnimation(ArrayList<Integer> spriteIDs) {
		animationType.replace("HOVER", new AnimationInfo(spriteIDs));
	}
	
	public void setClickImage(String imagePath) {
		animationType.replace("CLICK", new AnimationInfo(imagePath));
	}
	public void setClickAnimation(List<String> imagePaths) {
		animationType.replace("CLICK", new AnimationInfo(imagePaths));
	}
	public void setClickImage(Integer spriteID) {
		animationType.replace("CLICK", new AnimationInfo(spriteID));
	}
	public void setClickAnimation(ArrayList<Integer> spriteIDs) {
		animationType.replace("CLICK", new AnimationInfo(spriteIDs));
	}
	
	public void setCallImage(String imagePath) {
		animationType.replace("CALL", new AnimationInfo(imagePath));
	}
	public void setCallAnimation(List<String> imagePaths) {
		animationType.replace("CALL", new AnimationInfo(imagePaths));
	}
	public void setCallImage(Integer spriteID) {
		animationType.replace("CALL", new AnimationInfo(spriteID));
	}
	public void setCallAnimation(ArrayList<Integer> spriteIDs) {
		animationType.replace("CALL", new AnimationInfo(spriteIDs));
	}
}