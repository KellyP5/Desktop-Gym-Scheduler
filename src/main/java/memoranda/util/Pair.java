package main.java.memoranda.util;
 
 import nu.xom.Element;

/**
 * The type Pair.
 */
public class Pair {
   private Element element;
   private int priority;

    /**
     * Instantiates a new Pair.
     *
     * @param value    the value
     * @param priority the priority
     */
    public Pair(Element value, int priority){
     setElement(value);
     setPriority(priority);
   }

    /**
     * Gets element.
     *
     * @return the element
     */
    public Element getElement() {
     return element;
   }

    /**
     * Sets element.
     *
     * @param value the value
     */
    public void setElement(Element value) {
     this.element = value;
   }

    /**
     * Gets priority.
     *
     * @return the priority
     */
    public int getPriority() {
     return priority;
   }

    /**
     * Sets priority.
     *
     * @param priority the priority
     */
    public void setPriority(int priority) {
     this.priority = priority;
   }
 
 }