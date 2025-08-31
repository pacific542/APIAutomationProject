package Core;

public enum PetStatus {
	
	AVAILABLE("available"),
	PENDING("pending"),
	SOLD("sold");
	
	

	 // 1. A private field to hold the string value
    private final String status;

    // 2. Constructor to assign the value
    PetStatus(String status) {
        this.status = status;
    }

    // 3. Getter to use this value in other classes
    public String getStatus() {
        return status;
	
    }


}
