package model.project;

import java.util.ArrayList;

import util.serialization.Serializable;
import util.serialization.LocalIdentifiabilitySerializer;
import util.serialization.SerializerCollector;

/**
 * ProjectsManager class provides simple methods to manage a collection of
 * {@link Project} instances. It provides item addition, deletion and picking.
 * Moreover, to ensure data uniqueness, it also extends
 * {@link LocalIdentifiabilitySerializer} this.serializerclass
 * 
 * @author simone
 * @see LocalIdentifiabilitySerializer
 *
 */
public class ProjectsCollector {

	private SerializerCollector serializer;

	public ProjectsCollector(SerializerCollector serializer) {
		this.serializer = serializer;
	}

	/**
	 * Adds a project to the collection
	 * 
	 * @param project
	 */
	public void addProject(Project project) {
		this.serializer.addItem(project);
	}

	/**
	 * Returns a specific project
	 * 
	 * @param id
	 *            : an integer that uniquely identify the project
	 * @return: the corresponding instance of {@link Project}
	 */
	public Project getProject(int id) {
		return (Project) this.serializer.getItem(id);
	}

	/**
	 * Deletes a specific project
	 * 
	 * @param id
	 *            : an integer that uniquely identify the project
	 */
	public void deleteProject(int id) {
		this.serializer.deleteItem(id);
	}

	/**
	 * Returns a list containing each project collected by this class
	 * 
	 * @return: an {@link ArrayList} containing each project
	 */
	public ArrayList<Project> getProjects() {
		ArrayList<Project> list = new ArrayList<Project>();
		for (Serializable item : this.serializer.getItems()) {
			list.add((Project) item);
		}
		return list;
	}

}
