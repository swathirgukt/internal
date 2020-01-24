package com.indianeagle.internal.enums;

/**
 * User: praveen
 * Date: 1/23/13
 * Time: 6:25 PM
 */
public enum Relation {

    FATHER("F"),
    MOTHER("M"),
    HUSBAND("H"),
    WIFE("W");

    private String code;

    Relation(String code){
        this.code = code;
    }

    /**
     * This method returns the code of the relation
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * This method returns the relation Enum for a given code type of relation.
     *
     * @param code the relation code whose equivalent Enum to be returned
     * @return the matching enum for the code. null if code does not match
     */
    public static Relation getRelation(String code) {
        for (Relation relation : Relation.values()) {
            if (relation.getCode().equalsIgnoreCase(code)) {
                return relation;
            }
        }
        return null;
    }
}
