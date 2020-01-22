package com.indianeagle.internal.form;

import java.util.Date;

/**
 * Form class for leaves
 * User: nageswaramma
 * Date: 6/11/12
 * Time: 4:49 PM
 */
public class LeavesForm {
    private Long id;
    private double casualLeaves;
    private double sinkLeaves;
    private double compensatoryLeaves;
    private double previousYearLeaves;
    private Date currentLeaveYear;

    /**
     * Method to get the id
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Method to set the id
     *
     * @param id, to set the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method to get the casualLeaves
     *
     * @return casualLeaves
     */
    public double getCasualLeaves() {
        return casualLeaves;
    }

    /**
     * Method to set the casualLeaves
     *
     * @param casualLeaves, to set the casualLeaves
     */
    public void setCasualLeaves(double casualLeaves) {
        this.casualLeaves = casualLeaves;
    }

    /**
     * Method to get the sinkLeaves
     *
     * @return sinkLeaves
     */
    public double getSinkLeaves() {
        return sinkLeaves;
    }

    /**
     * Method to set the sinkLeaves
     *
     * @param sinkLeaves, to set the sinkLeaves
     */
    public void setSinkLeaves(double sinkLeaves) {
        this.sinkLeaves = sinkLeaves;
    }

    /**
     * Method to get the compensatoryLeaves
     *
     * @return compensatoryLeaves
     */
    public double getCompensatoryLeaves() {
        return compensatoryLeaves;
    }

    /**
     * Method to set the compensatoryLeaves
     *
     * @param compensatoryLeaves, to set the compensatoryLeaves
     */
    public void setCompensatoryLeaves(double compensatoryLeaves) {
        this.compensatoryLeaves = compensatoryLeaves;
    }

    /**
     * Method to get the previousYearLeaves
     *
     * @return previousYearLeaves
     */
    public double getPreviousYearLeaves() {
        return previousYearLeaves;
    }

    /**
     * Method to set the previousYearLeaves
     *
     * @param previousYearLeaves, to set the previousYearLeaves
     */
    public void setPreviousYearLeaves(double previousYearLeaves) {
        this.previousYearLeaves = previousYearLeaves;
    }

    /**
     * Method to get the currentLeaveYear
     *
     * @return currentLeaveYear
     */
    public Date getCurrentLeaveYear() {
        return currentLeaveYear;
    }

    /**
     * Method to set the currentLeaveYear
     *
     * @param currentLeaveYear, to set the currentLeaveYear
     */
    public void setCurrentLeaveYear(Date currentLeaveYear) {
        this.currentLeaveYear = currentLeaveYear;
    }
}
