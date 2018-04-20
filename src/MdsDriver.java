/**
 * Project #5
 * CS 2334, Section 010
 * April 22, 2016
 * <P>
 * Class Driver
 * This Program is load/import data from files and edit/check result by users
 * </P>
 */
public class MdsDriver {
	public static void main(String[] args){
		InputWindowsView input = new InputWindowsView();
		MediaModel model = new MediaModel();
		Controller controller = new Controller();
		controller.setView(input);
		controller.setModel(model);
		input.setModel(model);
		
	}
}
	