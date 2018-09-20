package com.ftn.SBZ_2018.netgear.dao;

public class SearchNetworkSystem {

	private String streetCabling;
	private int floorsCount;
	private Double avarageFloorSurface;
	private Double avarageOfficeCountByFloor;
	private Double avarageComputerCountByOffice;
	private boolean includeWiFi;
	private Double avarageWiFiCoverageSurfaceByFloor;
	private Double wantedAvarageDownloadSpeed;
	private Double wantedAvarageUploadSpeed;
	private boolean calculateWithPreferences;
	private Double budget;
	
	public SearchNetworkSystem () {}

	public SearchNetworkSystem(String streetCabling, int floorsCount, Double avarageFloorSurface,
			Double avarageOfficeCountByFloor, Double avarageComputerCountByOffice, boolean includeWiFi,
			Double avarageWiFiCoverageSurfaceByFloor, Double wantedAvarageDownloadSpeed,
			Double wantedAvarageUploadSpeed, boolean calculateWithPreferences, Double budget) {
		this.streetCabling = streetCabling;
		this.floorsCount = floorsCount;
		this.avarageFloorSurface = avarageFloorSurface;
		this.avarageOfficeCountByFloor = avarageOfficeCountByFloor;
		this.avarageComputerCountByOffice = avarageComputerCountByOffice;
		this.includeWiFi = includeWiFi;
		this.avarageWiFiCoverageSurfaceByFloor = avarageWiFiCoverageSurfaceByFloor;
		this.wantedAvarageDownloadSpeed = wantedAvarageDownloadSpeed;
		this.wantedAvarageUploadSpeed = wantedAvarageUploadSpeed;
		this.calculateWithPreferences = calculateWithPreferences;
		this.budget = budget;
	}

	public String getStreetCabling() {
		return streetCabling;
	}

	public void setStreetCabling(String streetCabling) {
		this.streetCabling = streetCabling;
	}

	public int getFloorsCount() {
		return floorsCount;
	}

	public void setFloorsCount(int floorsCount) {
		this.floorsCount = floorsCount;
	}

	public Double getAvarageFloorSurface() {
		return avarageFloorSurface;
	}

	public void setAvarageFloorSurface(Double avarageFloorSurface) {
		this.avarageFloorSurface = avarageFloorSurface;
	}

	public Double getAvarageOfficeCountByFloor() {
		return avarageOfficeCountByFloor;
	}

	public void setAvarageOfficeCountByFloor(Double avarageOfficeCountByFloor) {
		this.avarageOfficeCountByFloor = avarageOfficeCountByFloor;
	}

	public Double getAvarageComputerCountByOffice() {
		return avarageComputerCountByOffice;
	}

	public void setAvarageComputerCountByOffice(Double avarageComputerCountByOffice) {
		this.avarageComputerCountByOffice = avarageComputerCountByOffice;
	}

	public boolean isIncludeWiFi() {
		return includeWiFi;
	}

	public void setIncludeWiFi(boolean includeWiFi) {
		this.includeWiFi = includeWiFi;
	}

	public Double getAvarageWiFiCoverageSurfaceByFloor() {
		return avarageWiFiCoverageSurfaceByFloor;
	}

	public void setAvarageWiFiCoverageSurfaceByFloor(Double avarageWiFiCoverageSurfaceByFloor) {
		this.avarageWiFiCoverageSurfaceByFloor = avarageWiFiCoverageSurfaceByFloor;
	}

	public Double getWantedAvarageDownloadSpeed() {
		return wantedAvarageDownloadSpeed;
	}

	public void setWantedAvarageDownloadSpeed(Double wantedAvarageDownloadSpeed) {
		this.wantedAvarageDownloadSpeed = wantedAvarageDownloadSpeed;
	}

	public Double getWantedAvarageUploadSpeed() {
		return wantedAvarageUploadSpeed;
	}

	public void setWantedAvarageUploadSpeed(Double wantedAvarageUploadSpeed) {
		this.wantedAvarageUploadSpeed = wantedAvarageUploadSpeed;
	}

	public boolean isCalculateWithPreferences() {
		return calculateWithPreferences;
	}

	public void setCalculateWithPreferences(boolean calculateWithPreferences) {
		this.calculateWithPreferences = calculateWithPreferences;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	@Override
	public String toString() {
		return "SearchNetworkSystem [streetCabling=" + streetCabling + ", floorsCount=" + floorsCount
				+ ", avarageFloorSurface=" + avarageFloorSurface + ", avarageOfficeCountByFloor="
				+ avarageOfficeCountByFloor + ", avarageComputerCountByOffice=" + avarageComputerCountByOffice
				+ ", includeWiFi=" + includeWiFi + ", avarageWiFiCoverageSurfaceByFloor="
				+ avarageWiFiCoverageSurfaceByFloor + ", wantedAvarageDownloadSpeed=" + wantedAvarageDownloadSpeed
				+ ", wantedAvarageUploadSpeed=" + wantedAvarageUploadSpeed + ", calculateWithPreferences="
				+ calculateWithPreferences + ", budget=" + budget + "]";
	}
}
