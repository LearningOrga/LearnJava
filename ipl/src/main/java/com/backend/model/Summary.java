package com.backend.model;

import java.util.ArrayList;
import java.util.List;

public class Summary {


private int userId;

private String userName;

private int totalNumberWins;

private int totalNumberPredicted;

/**
 * @return the rule1Wins
 */
public int getRule1Wins() {
	return rule1Wins;
}

/**
 * @param rule1Wins the rule1Wins to set
 */
public void setRule1Wins(int rule1Wins) {
	this.rule1Wins = rule1Wins;
}

/**
 * @return the rule2Wins
 */
public int getRule2Wins() {
	return rule2Wins;
}

/**
 * @param rule2Wins the rule2Wins to set
 */
public void setRule2Wins(int rule2Wins) {
	this.rule2Wins = rule2Wins;
}

private double totalPoints;

private double totalPointsInvested;

private double totalPointsEarned;

private int rank;

private double winLossPer;

private int rule1Wins;

private int rule2Wins;

/**
 * @return the userName
 */
public String getUserName() {
	return userName;
}

/**
 * @param userName the userName to set
 */
public void setUserName(String userName) {
	this.userName = userName;
}

/**
 * @return the totalNumberWins
 */
public int getTotalNumberWins() {
	return totalNumberWins;
}

/**
 * @param totalNumberWins the totalNumberWins to set
 */
public void setTotalNumberWins(int totalNumberWins) {
	this.totalNumberWins = totalNumberWins;
}

/**
 * @return the totalPoints
 */
public double getTotalPoints() {
	return totalPoints;
}

/**
 * @param totalPoints the totalPoints to set
 */
public void setTotalPoints(double totalPoints) {
	this.totalPoints = totalPoints;
}

/**
 * @return the totalPointsInvested
 */
public double getTotalPointsInvested() {
	return totalPointsInvested;
}

/**
 * @param totalPointsInvested the totalPointsInvested to set
 */
public void setTotalPointsInvested(double totalPointsInvested) {
	this.totalPointsInvested = totalPointsInvested;
}

/**
 * @return the rank
 */
public int getRank() {
	return rank;
}

/**
 * @param rank the rank to set
 */
public void setRank(int rank) {
	this.rank = rank;
}

/**
 * @return the winLossPer
 */
public double getWinLossPer() {
	return winLossPer;
}

/**
 * @param winLossPer the winLossPer to set
 */
public void setWinLossPer(double winLossPer) {
	this.winLossPer = winLossPer;
}

public double getTotalPointsEarned() {
	return totalPointsEarned;
}

public void setTotalPointsEarned(double totalPointsEarned) {
	this.totalPointsEarned = totalPointsEarned;
}

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "Summary [userId=" + userId + ", userName=" + userName
			+ ", totalNumberWins=" + totalNumberWins
			+ ", totalNumberPredicted=" + totalNumberPredicted
			+ ", totalPoints=" + totalPoints + ", totalPointsInvested="
			+ totalPointsInvested + ", totalPointsEarned=" + totalPointsEarned
			+ ", rank=" + rank + ", winLossPer=" + winLossPer + ", rule1Wins="
			+ rule1Wins + ", rule2Wins=" + rule2Wins + "]";
}

/**
 * @return the userId
 */
public int getUserId() {
	return userId;
}

/**
 * @param userId the userId to set
 */
public void setUserId(int userId) {
	this.userId = userId;
}

/**
 * @return the totalNumberPredicted
 */
public int getTotalNumberPredicted() {
	return totalNumberPredicted;
}

/**
 * @param totalNumberPredicted the totalNumberPredicted to set
 */
public void setTotalNumberPredicted(int totalNumberPredicted) {
	this.totalNumberPredicted = totalNumberPredicted;
}

}
