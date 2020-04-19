/**
 * EventsManager.java Created on 08.03.2003, 12:35:19 Alex Package:
 * net.sf.memoranda
 * 
 * @author Alex V. Alishevskikh, alex@openmechanics.net Copyright (c) 2003
 *         Memoranda Team. http://memoranda.sf.net
 */
package main.java.memoranda;

import java.sql.Array;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import main.java.memoranda.database.GymClassEntity;
import main.java.memoranda.date.CalendarDate;
import main.java.memoranda.ui.App;
import main.java.memoranda.util.CurrentStorage;
import main.java.memoranda.util.Util;

import java.util.Map;
import java.util.Collections;

import nu.xom.Attribute;
//import nu.xom.Comment;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParentNode;

/**
 * The type Events manager.
 */
/*$Id: EventsManager.java,v 1.11 2004/10/06 16:00:11 ivanrise Exp $*/
public class EventsManager {
    /**
     * The constant NO_REPEAT.
     */
/*	public static final String NS_JNEVENTS =
		"http://www.openmechanics.org/2003/jnotes-events-file";
*/
	public static final int NO_REPEAT = 0;
    /**
     * The constant REPEAT_DAILY.
     */
    public static final int REPEAT_DAILY = 1;
    /**
     * The constant REPEAT_WEEKLY.
     */
    public static final int REPEAT_WEEKLY = 2;
    /**
     * The constant REPEAT_MONTHLY.
     */
    public static final int REPEAT_MONTHLY = 3;
    /**
     * The constant REPEAT_YEARLY.
     */
    public static final int REPEAT_YEARLY = 4;

    /**
     * The constant _doc.
     */
    public static Document _doc = null;
    /**
     * The Root.
     */
    static Element _root = null;

	static {
		CurrentStorage.get().openEventsManager();
		if (_doc == null) {
			_root = new Element("eventslist");
/*			_root.addNamespaceDeclaration("jnevents", NS_JNEVENTS);
			_root.appendChild(
				new Comment("This is JNotes 2 data file. Do not modify.")); */
			_doc = new Document(_root);
		} else
			_root = _doc.getRootElement();

	}

    /**
     * Create sticker.
     *
     * @param text  the text
     * @param prior the prior
     */
    public static void createSticker(String text, int prior) {
		Element el = new Element("sticker");
		el.addAttribute(new Attribute("id", Util.generateId()));
		el.addAttribute(new Attribute("priority", prior+""));
		el.appendChild(text);
		_root.appendChild(el);
	}

    /**
     * Gets stickers.
     *
     * @return the stickers
     */
    @SuppressWarnings("unchecked")
	public static Map getStickers() {
		Map m = new HashMap();
		Elements els = _root.getChildElements("sticker");
		for (int i = 0; i < els.size(); i++) {
			Element se = els.get(i);
			m.put(se.getAttribute("id").getValue(), se);
		}
		return m;
	}

    /**
     * Remove sticker.
     *
     * @param stickerId the sticker id
     */
    public static void removeSticker(String stickerId) {
		Elements els = _root.getChildElements("sticker");
		for (int i = 0; i < els.size(); i++) {
			Element se = els.get(i);
			if (se.getAttribute("id").getValue().equals(stickerId)) {
				_root.removeChild(se);
				break;
			}
		}
	}

    /**
     * Is nr events for date boolean.
     *
     * @param date the date
     * @return the boolean
     */
    public static boolean isNREventsForDate(CalendarDate date) {
		Day d = getDay(date);
		if (d == null)
			return false;
		if (d.getElement().getChildElements("event").size() > 0)
			return true;
		return false;
	}

    /**
     * Gets events for date.
     *
     * @param date the date
     * @return the events for date
     */
    public static Collection getEventsForDate(CalendarDate date) {
		Vector v = new Vector();
		Day d = getDay(date);
		if (d != null) {
			Elements els = d.getElement().getChildElements("event");
			for (int i = 0; i < els.size(); i++)
				v.add(new EventImpl(els.get(i)));
		}
		Collections.sort(v);

		//START: Code that will query the SQL DB and return an arraylist of GymClassEntities
		//Kelly Petrone
		LocalDate day = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
		ArrayList<GymClassEntity> arrayList = new ArrayList<>();
		try {
			System.out.println("[DEBUG] Querying SQL DB from EventsManager.getEventsForDate()");
			arrayList = App.conn.getDrq().getAllClassesByDate(day);
			for (int i=0; i<arrayList.size(); i++) {
				GymClassEntity g = arrayList.get(i);
				g.printGymClass();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Vector<GymClassEntity> vector = new Vector<>(arrayList);
		//END: Also converts ArrayList to Vector (How Memoranda stores events)

		return v;
	}

    /**
     * Create event event.
     *
     * @param date the date
     * @param hh   the hh
     * @param mm   the mm
     * @param text the text
     * @return the event
     */
    public static Event createEvent(
		CalendarDate date,
		int hh,
		int mm,
		String text) {
		Element el = new Element("event");
		el.addAttribute(new Attribute("id", Util.generateId()));
		el.addAttribute(new Attribute("hour", String.valueOf(hh)));
		el.addAttribute(new Attribute("min", String.valueOf(mm)));
		el.appendChild(text);
		Day d = getDay(date);
		if (d == null)
			d = createDay(date);
		d.getElement().appendChild(el);
		return new EventImpl(el);
	}

    /**
     * Gets active events.
     *
     * @return the active events
     */
    public static Collection getActiveEvents() {
		return getEventsForDate(CalendarDate.today());
	}

    /**
     * Gets event.
     *
     * @param date the date
     * @param hh   the hh
     * @param mm   the mm
     * @return the event
     */
    public static Event getEvent(CalendarDate date, int hh, int mm) {
		Day d = getDay(date);
		if (d == null)
			return null;
		Elements els = d.getElement().getChildElements("event");
		for (int i = 0; i < els.size(); i++) {
			Element el = els.get(i);
			if ((new Integer(el.getAttribute("hour").getValue()).intValue()
				== hh)
				&& (new Integer(el.getAttribute("min").getValue()).intValue()
					== mm))
				return new EventImpl(el);
		}
		return null;
	}

    /**
     * Remove event.
     *
     * @param date the date
     * @param hh   the hh
     * @param mm   the mm
     */
    public static void removeEvent(CalendarDate date, int hh, int mm) {
		Day d = getDay(date);
		if (d == null)
			d.getElement().removeChild(getEvent(date, hh, mm).getContent());
	}

    /**
     * Remove event.
     *
     * @param ev the ev
     */
    public static void removeEvent(Event ev) {
		ParentNode parent = ev.getContent().getParent();
		parent.removeChild(ev.getContent());
	}

	private static Day createDay(CalendarDate date) {
		Year y = getYear(date.getYear());
		if (y == null)
			y = createYear(date.getYear());
		Month m = y.getMonth(date.getMonth());
		if (m == null)
			m = y.createMonth(date.getMonth());
		Day d = m.getDay(date.getDay());
		if (d == null)
			d = m.createDay(date.getDay());
		return d;
	}

	private static Year createYear(int y) {
		Element el = new Element("year");
		el.addAttribute(new Attribute("year", new Integer(y).toString()));
		_root.appendChild(el);
		return new Year(el);
	}

	private static Year getYear(int y) {
		Elements yrs = _root.getChildElements("year");
		String yy = new Integer(y).toString();
		for (int i = 0; i < yrs.size(); i++)
			if (yrs.get(i).getAttribute("year").getValue().equals(yy))
				return new Year(yrs.get(i));
		//return createYear(y);
		return null;
	}

	private static Day getDay(CalendarDate date) {
		Year y = getYear(date.getYear());
		if (y == null)
			return null;
		Month m = y.getMonth(date.getMonth());
		if (m == null)
			return null;
		return m.getDay(date.getDay());
	}

    /**
     * The type Year.
     */
    static class Year {
        /**
         * The Year element.
         */
        Element yearElement = null;

        /**
         * Instantiates a new Year.
         *
         * @param el the el
         */
        public Year(Element el) {
			yearElement = el;
		}

        /**
         * Gets value.
         *
         * @return the value
         */
        public int getValue() {
			return new Integer(yearElement.getAttribute("year").getValue())
				.intValue();
		}

        /**
         * Gets month.
         *
         * @param m the m
         * @return the month
         */
        public Month getMonth(int m) {
			Elements ms = yearElement.getChildElements("month");
			String mm = new Integer(m).toString();
			for (int i = 0; i < ms.size(); i++)
				if (ms.get(i).getAttribute("month").getValue().equals(mm))
					return new Month(ms.get(i));
			//return createMonth(m);
			return null;
		}

		private Month createMonth(int m) {
			Element el = new Element("month");
			el.addAttribute(new Attribute("month", new Integer(m).toString()));
			yearElement.appendChild(el);
			return new Month(el);
		}

        /**
         * Gets months.
         *
         * @return the months
         */
        public Vector getMonths() {
			Vector v = new Vector();
			Elements ms = yearElement.getChildElements("month");
			for (int i = 0; i < ms.size(); i++)
				v.add(new Month(ms.get(i)));
			return v;
		}

        /**
         * Gets element.
         *
         * @return the element
         */
        public Element getElement() {
			return yearElement;
		}

	}

    /**
     * The type Month.
     */
    static class Month {
        /**
         * The M element.
         */
        Element mElement = null;

        /**
         * Instantiates a new Month.
         *
         * @param el the el
         */
        public Month(Element el) {
			mElement = el;
		}

        /**
         * Gets value.
         *
         * @return the value
         */
        public int getValue() {
			return new Integer(mElement.getAttribute("month").getValue())
				.intValue();
		}

        /**
         * Gets day.
         *
         * @param d the d
         * @return the day
         */
        public Day getDay(int d) {
			if (mElement == null)
				return null;
			Elements ds = mElement.getChildElements("day");
			String dd = new Integer(d).toString();
			for (int i = 0; i < ds.size(); i++)
				if (ds.get(i).getAttribute("day").getValue().equals(dd))
					return new Day(ds.get(i));
			//return createDay(d);
			return null;
		}

		private Day createDay(int d) {
			Element el = new Element("day");
			el.addAttribute(new Attribute("day", new Integer(d).toString()));
			el.addAttribute(
				new Attribute(
					"date",
					new CalendarDate(
						d,
						getValue(),
						new Integer(
							((Element) mElement.getParent())
								.getAttribute("year")
								.getValue())
							.intValue())
						.toString()));

			mElement.appendChild(el);
			return new Day(el);
		}

        /**
         * Gets days.
         *
         * @return the days
         */
        public Vector getDays() {
			if (mElement == null)
				return null;
			Vector v = new Vector();
			Elements ds = mElement.getChildElements("day");
			for (int i = 0; i < ds.size(); i++)
				v.add(new Day(ds.get(i)));
			return v;
		}

        /**
         * Gets element.
         *
         * @return the element
         */
        public Element getElement() {
			return mElement;
		}

	}

    /**
     * The type Day.
     */
    static class Day {
        /**
         * The D el.
         */
        Element dEl = null;

        /**
         * Instantiates a new Day.
         *
         * @param el the el
         */
        public Day(Element el) {
			dEl = el;
		}

        /**
         * Gets value.
         *
         * @return the value
         */
        public int getValue() {
			return new Integer(dEl.getAttribute("day").getValue()).intValue();
		}

		/*
		 * public Note getNote() { return new NoteImpl(dEl);
		 */

        /**
         * Gets element.
         *
         * @return the element
         */
        public Element getElement() {
			return dEl;
		}
	}
}
