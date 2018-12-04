package verkocht.model;

/*
 * Enum for the units of the ingredients.
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
	
	Unit(String unit) {
		this.unitName = unit; 
	}
	
	public String getUnit() {
		return this.unitName;
	}
}