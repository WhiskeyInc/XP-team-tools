package model.project;

import java.util.ArrayList;

import util.serialization.SerializerCollector;

public class ProjectsManager extends SerializerCollector<Project> {

	public void addProject(Project project) {
		super.addItem(project);		
	}

	public Project getProject(int id) {
		return super.getItem(id);
	}

	public void deleteProject(int id) {
		super.deleteItem(id);		
	}

	public ArrayList<Project> getProjects() {
		return super.getItems();
	}

}
