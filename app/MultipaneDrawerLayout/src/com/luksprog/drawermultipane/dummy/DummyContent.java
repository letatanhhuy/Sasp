package com.luksprog.drawermultipane.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

	/**
	 * The array holding the categories names.
	 */
	public static final String[] CATEGORIES = { "School Assignments",
			"Parent Assignments", "Sport Assignments", "Personal Assignments" };
	
	public static final List<Assigment> ITEMS_SCHOOL = new ArrayList<DummyContent.Assigment>();
	public static final List<Assigment> ITEMS_PARENTS = new ArrayList<DummyContent.Assigment>();
	public static final List<Assigment> ITEMS_SPORT = new ArrayList<DummyContent.Assigment>();
	public static final List<Assigment> ITEMS_PERSONAL = new ArrayList<DummyContent.Assigment>();

	/**
	 * A map of sample (dummy) items, where the key is a category which points
	 * to a list of assignments for that category.
	 */
	public static Map<String, List<Assigment>> ITEM_MAP = new HashMap<String, List<Assigment>>();

	static {
		// add the data to the list of assignments.
		ITEMS_SCHOOL.add(new Assigment(0, "Do math homework!"));
		ITEMS_SCHOOL.add(new Assigment(1, "Do english homework!"));
		ITEMS_SCHOOL.add(new Assigment(2, "Do physics homework!"));
		ITEMS_SCHOOL.add(new Assigment(3, "Learn to make Android apps!"));
		ITEM_MAP.put(CATEGORIES[0], ITEMS_SCHOOL);
		ITEMS_PARENTS.add(new Assigment(0, "Take out trash!"));
		ITEMS_PARENTS.add(new Assigment(1, "Buy big pizza!"));
		ITEMS_PARENTS.add(new Assigment(2, "Pay the electric bill!"));
		ITEMS_PARENTS.add(new Assigment(3, "Heat the food!"));
		ITEM_MAP.put(CATEGORIES[1], ITEMS_PARENTS);
		ITEMS_SPORT.add(new Assigment(0, "Beat the world 100m record!"));
		ITEMS_SPORT.add(new Assigment(1, "Run a marathon!"));
		ITEMS_SPORT.add(new Assigment(2, "Do 200 exercises!"));
		ITEMS_SPORT.add(new Assigment(3, "Lift 400 kg!"));
		ITEM_MAP.put(CATEGORIES[2], ITEMS_SPORT);
		ITEMS_PERSONAL.add(new Assigment(0, "Be the best!"));
		ITEMS_PERSONAL.add(new Assigment(1, "Become an astronaut!"));
		ITEMS_PERSONAL.add(new Assigment(2, "Make world peace!"));
		ITEMS_PERSONAL.add(new Assigment(3, "Spend a week at the north pole!"));
		ITEM_MAP.put(CATEGORIES[3], ITEMS_PERSONAL);
	}

	/**
	 * A dummy item representing a piece of content.
	 */
	public static class Assigment {
		public int id;
		public String content;

		public Assigment(int id, String content) {
			this.id = id;
			this.content = content;
		}

		@Override
		public String toString() {
			return content;
		}
	}
}
