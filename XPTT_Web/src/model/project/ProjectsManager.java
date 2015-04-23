package model.project;

import java.util.ArrayList;

import util.serialization.SerializerCollector;

/**
 * ProjectsManager class provides simple methods to manage a collection of
 * {@link Project} instances. It provides item addition, deletion and picking.
 * Moreover, to ensure data uniqueness, it also extends
 * {@link SerializerCollector} superclass
 * 
 * @author simone
 * @see SerializerCollector
 *
 */
public class ProjectsManager extends SerializerCollector<Project> {

	/**
	 * Adds a project to the collection
	 * 
	 * @param project
	 */
	public void addProject(Project project) {
		super.addItem(project);
	}

	/**
	 * Returns a specific project
	 * 
	 * @param id
	 *            : an integer that uniquely identify the project
	 * @return: the corresponding instance of {@link Project}
	 */
	public Project getProject(int id) {
		return super.getItem(id);
	}

	/**
	 * Deletes a specific project
	 * 
	 * @param id
	 *            : an integer that uniquely identify the project
	 */
	public void deleteProject(int id) {
		super.deleteItem(id);
	}

	/**
	 * Returns a list containing each project collected by this class
	 * 
	 * @return: an {@link ArrayList} containing each project
	 */
	public ArrayList<Project> getProjects() {
		return super.getItems();
	}

}
