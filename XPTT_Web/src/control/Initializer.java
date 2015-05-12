package control;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import boards.UserStoryBoard.UserStory;
import boards.taskBoard.ConcreteTaskManager;
import model.ConcreteProjectSettings;
import model.exceptions.InvalidDateException;
import model.exceptions.NameAlreadyInUseException;
import model.project.ConcreteProjectFactory;
import model.project.Project;
import model.project.ProjectsCollector;
import timeline.ConcreteTimeline;
import timeline.Event;
import timeline.MacroEvent;
import timeline.Timeline;

/**
 * Servlet implementation class Initializer
 */
@WebServlet("/initialize")
public class Initializer extends HttpServlet {
	private static final String MILESTONE_STORY_DESCRIPTION = "Da sviluppatore, vorrei che quando guardo la timeline del progetto non venissi confuso dal mischiarsi di eventi di diversa entitÃ . Vorrei quindi che comparissero solo degli eventi principali e che solo all'occasione mi venissero presentati quelli meno rilevanti";
	private static final String LOGIN_STORY_DESCRIPTION = "Come Product Owner, vorrei che la piattaforma presentasse un servizio di login, in modo che utenti diversi possano, con login diversi, accedere ai loro dati e ai loro progetti senza che questi si mischino con quelli di altri utenti";
	private static final String PROJECTS_STORY_DESCRIPTION = "Come sviluppatore che usa la piattaforma xTrEAM, voglio poter utilizzare questi strumenti per piÃ¹ di un progetto, e voglio che tutti i dati e le grafiche vengano distinte in base al progetto che voglio portare avanti in quel momento";
	private static final long serialVersionUID = 1L;
	private static final String US_STORY_DESCRIPTION = "Come sviluppatore, vorrei avere sempre a disposizione una pagina dove mi si ricorda quali sono le storie utente a cui stiamo lavorando, quali abbiamo già terminato e quali sono ancora da iniziare, in modo da non rischiare di sviluppare funzionalità non richieste tralasciando quelle importanti";
	private static final String TASKS_STORY_DESCRIPTION = "Come sviluppatore che vuole lavorare con metodologie agili, vorrei poter condividere con gli altri membri del team una bacheca in cui mi si presentino tutti i task che dobbiamo completare per una particolare storia utente. In questo modo è più facile collaborare, anche se a distanza";
	private static final String MEMBERS_STORY_DESCRIPTION = "Quando creo un progetto, vorrei poter aggiungere nuovi membri al mio team per quel progetto, scegliendo tra tutti gli utenti registrati alla piattaforma xTrEAM";
	private static final String INFO_STORY_DESCRIPTION = "Come Product Owner, vorrei che gli utenti che arrivano alla nostra landing page, possano disporre di una pagina che parli del progetto e di chi lo sta sviluppando in modo che siano invogliati ad iscriversi";

	@Override
	public void init() throws ServletException {
		super.init();

		HashMap<String, String> users = new HashMap<String, String>();
		users.put("admin", "1789");
		HashMap<String, ProjectsCollector> environments = new HashMap<String, ProjectsCollector>();
		HashMap<String, ProjectsCollector> pendingProjects = new HashMap<String, ProjectsCollector>();
		ProjectsCollector testCollector = new ProjectsCollector();
		Project project = new Project("xTrEAM", new ConcreteProjectFactory(),
				"A debugger for this very application");
		ConcreteProjectSettings settings = (ConcreteProjectSettings) project
				.getSettings();
		settings.setPossibleUserStoriesStates("TODO", "IN PROGRESS", "DONE");
		UserStory story1 = new UserStory("Più di un Progetto",
				PROJECTS_STORY_DESCRIPTION, new ConcreteTaskManager());
		story1.moveToState("DONE");
		UserStory story2 = new UserStory("Macro eventi in Timeline",
				MILESTONE_STORY_DESCRIPTION, new ConcreteTaskManager());
		story2.moveToState("DONE");
		UserStory story3 = new UserStory("Servizio di Login",
				LOGIN_STORY_DESCRIPTION, new ConcreteTaskManager());
		story3.moveToState("DONE");
		UserStory story4 = new UserStory("Manager di user stories",
				US_STORY_DESCRIPTION, new ConcreteTaskManager());
		story4.moveToState("IN PROGRESS");
		UserStory story5 = new UserStory("Bacheca dei task",
				TASKS_STORY_DESCRIPTION, new ConcreteTaskManager());
		story5.moveToState("TODO");
		UserStory story6 = new UserStory("Aggiunta di membri",
				MEMBERS_STORY_DESCRIPTION, new ConcreteTaskManager());
		story6.moveToState("IN PROGRESS");
		UserStory story7 = new UserStory("Pagina di informazioni",
				INFO_STORY_DESCRIPTION, new ConcreteTaskManager());
		story7.moveToState("TODO");
		
		try {
			project.getUserStoriesManager().addUserStory(story1);
			project.getUserStoriesManager().addUserStory(story2);
			project.getUserStoriesManager().addUserStory(story3);
			project.getUserStoriesManager().addUserStory(story4);
			project.getUserStoriesManager().addUserStory(story5);
			project.getUserStoriesManager().addUserStory(story6);
			project.getUserStoriesManager().addUserStory(story7);
			story1.setPriority(UserStory.MAXPRIORITY);
			story2.setPriority(UserStory.MAXPRIORITY-1);
			story3.setPriority(UserStory.MAXPRIORITY-2);
			story4.setPriority(UserStory.MAXPRIORITY-2);
			story5.setPriority(UserStory.MAXPRIORITY-5);
			story6.setPriority(UserStory.MINPRIORITY);
			story7.setPriority(UserStory.MINPRIORITY);
			
		} catch (Exception e1) {
		}
		Timeline timeline = new ConcreteTimeline(TimeZone.getDefault());
		MacroEvent macro;
		try {
			macro = new MacroEvent("Third Release", new GregorianCalendar(2015, 5, 13, 9,
					0), new GregorianCalendar(2015, 5, 26, 11, 00), timeline);
			macro.addEvent(new Event("Deploy Online",
					new GregorianCalendar(2015, 5, 26, 10, 00)));
			macro.addEvent(new Event("Ending Project Party",
					new GregorianCalendar(2015, 5, 25, 00, 00)));
			project.getTimeline().addEvent(macro);
		} catch (InvalidDateException e) {
		}
		testCollector.addProject(project);
		environments.put("admin", testCollector);
		super.getServletContext().setAttribute("pendingProjects",
				pendingProjects);
		super.getServletContext().setAttribute("environments", environments);
		super.getServletContext().setAttribute("users", users);

	}
}