package control;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import model.ConcreteProjectSettings;
import model.TeamComponent;
import model.exceptions.InvalidDateException;
import model.exceptions.NameAlreadyInUseException;
import model.project.ConcreteProjectFactory;
import model.project.Project;
import model.project.ProjectsCollector;
import timeline.ConcreteTimeline;
import timeline.Event;
import timeline.Timeline;
import timeline.events.MacroEvent;
import util.serialization.GlobalIdentifiabilitySerializer;
import util.serialization.LocalIdentifiabilitySerializer;
import boards.UserStoryBoard.UserStory;
import boards.taskBoard.ConcreteTaskManager;

/**
 * This HttpServlet performs any required operation to properly run this very
 * application
 */
@WebServlet("/initialize")
public class Initializer extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String MILESTONE_STORY_DESCRIPTION = "Da sviluppatore, vorrei che quando guardo la timeline del progetto non venissi confuso dal mischiarsi di eventi di diversa entitÃ . Vorrei quindi che comparissero solo degli eventi principali e che solo all'occasione mi venissero presentati quelli meno rilevanti";
	private static final String LOGIN_STORY_DESCRIPTION = "Come Product Owner, vorrei che la piattaforma presentasse un servizio di login, in modo che utenti diversi possano, con login diversi, accedere ai loro dati e ai loro progetti senza che questi si mischino con quelli di altri utenti";
	private static final String PROJECTS_STORY_DESCRIPTION = "Come sviluppatore che usa la piattaforma xTrEAM, voglio poter utilizzare questi strumenti per piÃ¹ di un progetto, e voglio che tutti i dati e le grafiche vengano distinte in base al progetto che voglio portare avanti in quel momento";
	private static final String US_STORY_DESCRIPTION = "Come sviluppatore, vorrei avere sempre a disposizione una pagina dove mi si ricorda quali sono le storie utente a cui stiamo lavorando, quali abbiamo già terminato e quali sono ancora da iniziare, in modo da non rischiare di sviluppare funzionalità non richieste tralasciando quelle importanti";
	private static final String TASKS_STORY_DESCRIPTION = "Come sviluppatore che vuole lavorare con metodologie agili, vorrei poter condividere con gli altri membri del team una bacheca in cui mi si presentino tutti i task che dobbiamo completare per una particolare storia utente. In questo modo è più facile collaborare, anche se a distanza";
	private static final String MEMBERS_STORY_DESCRIPTION = "Quando creo un progetto, vorrei poter aggiungere nuovi membri al mio team per quel progetto, scegliendo tra tutti gli utenti registrati alla piattaforma xTrEAM";
	private static final String INFO_STORY_DESCRIPTION = "Come Product Owner, vorrei che gli utenti che arrivano alla nostra landing page, possano disporre di una pagina che parli del progetto e di chi lo sta sviluppando in modo che siano invogliati ad iscriversi";

	private static final String TIMELINE_STORY_DESCRIPTION = "Come sviluppatore XP fuori casa, vorrei poter disporre di una pagina web in cui mi vengono presentati tutti le azioni compiute dai miei compagni, in modo da essere sempre aggiornato senza dover accendere il PC";
	private static final String AUTOMATIC_EVENTS_STORY_DESCRIPTION = "In quanto sviluppatore XP, vorrei poter notificare al mio team quello su cui sto lavorando, in modo che anche chi non sta lavorando in quel momento sappia poi chi ha fatto cosa e quando";
	private static final String US_TASK_PARTICIPANTS_STORY_DESCRIPTION = "Come sviluppatore XP, vorrei sapere quali membri del mio team stanno lavorando ai diversi sviluppi del progetto o stanno partecipando a determinati eventi, in modo da sapere sempre a chi rivolgermi per un determinato problema o per un certo chiarimento";
	private static final String EVENTS_STORY_DESCRIPTION = "In quanto membro di un team XP vorrei poter disporre di un sistema unico per la pianificazione degli impegni (anche non di sviluppo), in modo che gli eventi siano facilmente notificabili agli interessati e visibili anche a chi non vi partecipa";
	private static final String FILTERING_STORY_DESCRIPTION = "In quanto membro del team, vorrei non dover perdere tempo a cercare tra un grosso numero di eventi relativi al mio team. Vorrei che mi fosse possibile filtrare tra tutti solo quelli di mio interesse, così da fare ricerche più veloci";

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException {
		super.init();

		HashMap<String, String> users = new HashMap<String, String>();
		users.put("alemarti", "alemarti");
		HashMap<String, ProjectsCollector> environments = new HashMap<String, ProjectsCollector>();
		ProjectsCollector testCollector = new ProjectsCollector(
				GlobalIdentifiabilitySerializer.getInstance());
		Project project = new Project("xTrEAM", new ConcreteProjectFactory(),
				"From your students, with love.");
		ConcreteProjectSettings settings = (ConcreteProjectSettings) project
				.getSettings();
		TeamComponent teamComponent = new TeamComponent("Alessandro", "Martinelli", "The Boss");
		TeamComponent lele = new TeamComponent("Emanuele", "Fabbiani", "Tester & SVN manager");
		TeamComponent simo = new TeamComponent("Simone", "Colucci", "Design pattern & web designer");
		TeamComponent incre = new TeamComponent("Alessandro", "Incremona", "Open suorce code & documentation expert");
		TeamComponent andre = new TeamComponent("Andrea", "Aschei", "Software analyst and user stories manager");
		TeamComponent digio = new TeamComponent("Alberto", "di Gioacchino", "Test & Swing-Thread expert");
		TeamComponent nick = new TeamComponent("Nicola", "Latella", "Design pattern & user stories manager");
		TeamComponent bardo = new TeamComponent("Alessandro", "Bardini", "Software analyst & documentation expert");
		TeamComponent pavlo = new TeamComponent("Pavlo", "Burda", "Server manager & web designer");
		
		try {
			settings.addTeamMember(teamComponent);
			settings.addTeamMember(lele);
			settings.addTeamMember(simo);
			settings.addTeamMember(incre);
			settings.addTeamMember(andre);
			settings.addTeamMember(digio);
			settings.addTeamMember(nick);
			settings.addTeamMember(bardo);
			settings.addTeamMember(pavlo);
		} catch (NameAlreadyInUseException e2) {}
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
		story6.moveToState("DONE");
		UserStory story7 = new UserStory("Pagina di informazioni",
				INFO_STORY_DESCRIPTION, new ConcreteTaskManager());
		story7.moveToState("TODO");
		UserStory story8 = new UserStory("Informazioni su un progetto",
				TIMELINE_STORY_DESCRIPTION, new ConcreteTaskManager());
		story8.moveToState("DONE");
		UserStory story9 = new UserStory("Notifiche al team",
				AUTOMATIC_EVENTS_STORY_DESCRIPTION, new ConcreteTaskManager());
		story9.moveToState("DONE");
		UserStory story10 = new UserStory("Chi fa cosa",
				US_TASK_PARTICIPANTS_STORY_DESCRIPTION, new ConcreteTaskManager());
		story10.moveToState("DONE");
		UserStory story11 = new UserStory("Timeline",
				EVENTS_STORY_DESCRIPTION, new ConcreteTaskManager());
		story11.moveToState("DONE");
		UserStory story12 = new UserStory("Filtri su eventi, task e user story",
				FILTERING_STORY_DESCRIPTION, new ConcreteTaskManager());
		story12.moveToState("DONE");

		try {
			project.getUserStoriesManager().addUserStory(story1);
			project.getUserStoriesManager().addUserStory(story2);
			project.getUserStoriesManager().addUserStory(story3);
			project.getUserStoriesManager().addUserStory(story4);
			project.getUserStoriesManager().addUserStory(story5);
			project.getUserStoriesManager().addUserStory(story6);
			project.getUserStoriesManager().addUserStory(story7);
			project.getUserStoriesManager().addUserStory(story8);
			project.getUserStoriesManager().addUserStory(story9);
			project.getUserStoriesManager().addUserStory(story10);
			project.getUserStoriesManager().addUserStory(story11);
			project.getUserStoriesManager().addUserStory(story12);
			
			story8.setPriority(UserStory.MAXPRIORITY);
			story9.setPriority(UserStory.MAXPRIORITY - 1);
			story10.setPriority(UserStory.MAXPRIORITY - 2);
			story11.setPriority(UserStory.MAXPRIORITY - 3);
			story12.setPriority(UserStory.MAXPRIORITY - 4);
			story1.setPriority(UserStory.MAXPRIORITY - 6);
			story2.setPriority(UserStory.MAXPRIORITY - 7);
			story3.setPriority(UserStory.MAXPRIORITY - 8);
			story4.setPriority(UserStory.MAXPRIORITY - 9);
			story5.setPriority(UserStory.MINPRIORITY);
			story6.setPriority(UserStory.MINPRIORITY);
			story7.setPriority(UserStory.MINPRIORITY);

		} catch (Exception e1) {}
		
		
		Timeline timeline = new ConcreteTimeline(
				TimeZone.getTimeZone("Europe/Rome"),
				new LocalIdentifiabilitySerializer());
		MacroEvent macro;
		try {
			macro = new MacroEvent("Third Release",
					(GregorianCalendar) GregorianCalendar.getInstance(TimeZone
							.getTimeZone("Europe/Rome")),
					new GregorianCalendar(2015, 5, 12, 11, 00), timeline);
			macro.addEvent(new Event("Deploy Online", new GregorianCalendar(
					2015, 5, 2, 10, 00)));
			macro.addEvent(new Event("Ending Project Party",
					new GregorianCalendar(2015, 5, 8, 00, 00)));
			project.getTimeline().addEvent(macro);
		} catch (InvalidDateException e) {}
		
		Event event = new Event("Last update", new GregorianCalendar(2015, 5, 2, 12, 12));
		try {
			project.getTimeline().addEvent(event);
		} catch (InvalidDateException e) {}
		
		testCollector.addProject(project);
		environments.put("alemarti", testCollector);
		super.getServletContext().setAttribute("environments", environments);
		super.getServletContext().setAttribute("users", users);
		HashMap<String, TeamComponent> info = new HashMap<String, TeamComponent>();
		info.put("alemarti", teamComponent);
		super.getServletContext().setAttribute("usersInfo", info);
	}
}