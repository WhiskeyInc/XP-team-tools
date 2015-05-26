package model.project;

import java.util.ArrayList;

import util.serialization.Serializable;
import util.serialization.SerializerCollector;

/**
 * ProjectsManager class provides simple methods to manage a collection of
 * {@link Project} instances. It provides item addition, deletion and picking.
 * Moreover, to ensure data identifiability, it uses a
 * {@link SerializerCollector} instance to manage the storage
 * 
 * @author simone, lele, incre, andre
 * @see SerializerCollector, {@link Project}
 *
 */
public class ProjectsCollector {

	private SerializerCollector serializer;

	/**
	 * Creates a new instance of this class
	 * 
	 * @param serializer
	 *            : the serializer to be used for data identifiability
	 */
	public ProjectsCollector(SerializerCollector serializer) {
		this.serializer = serializer;
		this.serializer.registerOwner(this);
	}

	/**
	 * Adds a project to the collection
	 * 
	 * @param project
	 *            : the project to add to the collection
	 */
	public void addProject(Project project) {
		this.serializer.addItem(project, this);
	}

	/**
	 * Returns a specific project
	 * 
	 * @param id
	 *            : an integer that uniquely identify the project
	 * @return: the corresponding instance of {@link Project}
	 */
	public Project getProject(int id) {
		return (Project) this.serializer.getItem(id, this);
	}

	/**
	 * Deletes a specific project
	 * 
	 * @param id
	 *            : an integer that uniquely identify the project
	 */
	public void deleteProject(int id) {
		this.serializer.deleteItem(id, this);
	}

	/**
	 * Returns a list containing each project collected by this class
	 * 
	 * @return: an {@link ArrayList} containing each project
	 */
	public ArrayList<Project> getProjects() {
		ArrayList<Project> list = new ArrayList<Project>();
		for (Serializable item : this.serializer.getItems(this)) {
			list.add((Project) item);
		}
		return list;
	}

}
