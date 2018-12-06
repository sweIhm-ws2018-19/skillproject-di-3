package verkocht.model;

/**
 * Enum that represents all available units in our cooking book. 
 * @author Florian Uhrig
 */
public enum Unit {
	MILLILITER("Milliliter"), 
	GRAMM("Gramm"), 
	STUECK("Stueck"),
	TEELOEFFEL("Teeloeffel"), 
	ESSLOEFFEL("Essloeffel"), 
	BRISE("Brise"), 
	SCHUSS("Schuss"),
	TASSE("Tasse");
	
	String unitName;
	
	/**
	 * Ctor to initialize the enum class.
	 * @param unit The units name as string.
	 */
	Unit(String unit) {
		this.unitName = unit; 
	}
	
	/**
	 * Getter for one units name.
	 * @return The units name as string.
	 */
	public String getUnit() {
		return this.unitName;
	}
}