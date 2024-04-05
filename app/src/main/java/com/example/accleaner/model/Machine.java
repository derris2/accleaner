package com.app.cleanspace.model;

public class Machine {
    private int      id;
    private String      name;
    private String      type;
    private String      macAddress;
    private String      location;
    private Boolean     isConnect;
    private String      connectWith; // B | W, B for Bluetooth and W for Wifi
    private String      ip;

    private String      machineType;
    private String      machineName;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    private String      IPAddress;
    private String      VendorName;
    private String      Power;
    private String      FanSpeed;
    private String      TemperatureTarget;
    private String      Temperature;
    private String      TemperatureAC;


    public String getPower() {
        return Power;
    }

    public void setPower(String power) {
        Power = power;
    }

    public String getFanSpeed() {
        return FanSpeed;
    }

    public void setFanSpeed(String fanSpeed) {
        FanSpeed = fanSpeed;
    }

    public String getTemperatureTarget() {
        return TemperatureTarget;
    }

    public void setTemperatureTarget(String temperatureTarget) {
        TemperatureTarget = temperatureTarget;
    }

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }

    public Machine() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getConnect() {
        return isConnect;
    }

    public void setConnect(Boolean connect) {
        isConnect = connect;
    }

    public String getConnectWith() {
        return connectWith;
    }

    public void setConnectWith(String connectWith) {
        this.connectWith = connectWith;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public String getVendorName() {
        return VendorName;
    }

    public void setVendorName(String vendorName) {
        VendorName = vendorName;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getTemperatureAC() {
        return TemperatureAC;
    }

    public void setTemperatureAC(String temperatureAC) {
        TemperatureAC = temperatureAC;
    }
}
