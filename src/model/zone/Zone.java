package model.zone;

import model.Cell;

// Butun zone turlerinin ortak ozellikleri burada.
public abstract class Zone extends Cell {

    // Zone'un anlik seviyesi.
    protected int level;

    // Alinan utility miktarlari.
    protected int electricityReceived;
    protected int waterReceived;
    protected int internetReceived;

    // Servislerden cover oldu mu?
    protected boolean securityCovered;
    protected boolean healthCovered;
    protected boolean educationCovered;

    // Sehirden gelen kaynaklar.
    protected int populationReceived;
    protected int goodsReceived;
    protected int lifestyleReceived;

    // Son tick'teki uretim miktari.
    protected int lastTickOutput;

    public Zone(int row, int col) {
        super(row, col);
        this.level = 0;
        this.lastTickOutput = 0;
    }

    public int getLevel() {
        return level;
    }

    // Bu zone bu tick'te ne kadar utility istiyor?
    public int getDemand() {
        return Math.max(1, lastTickOutput);
    }

    // Her zone kendi m'ini hesapliyor.
    public abstract int computeM();

    // Tick'teki uretim miktari hesaplama.
    public abstract int computeOutput();

    // Tick sonunda level guncelleniyor.
    public abstract void updateLevel();

    // Uretilen override edilir ve digerleri 0 doner.
    public int getPopulationProduced() {
        return 0;
    }
    public int getGoodsProduced() {
        return 0;
    }
    public int getLifestyleProduced() {
        return 0;
    }

    // Distributorler bu metodlari cagirarak zone'a kaynak veriyor.

    public void receiveElectricity(int amount) {
        electricityReceived += amount;
    }
    public void receiveWater(int amount) {
        waterReceived += amount;
    }
    public void receiveInternet(int amount) {
        internetReceived += amount;
    }

    public void setSecurity(boolean v) {
        securityCovered = v;
    }
    public void setHealth(boolean v) {
        healthCovered = v;
    }
    public void setEducation(boolean v) {
        educationCovered = v;
    }

    public void setPopulationReceived(int v) {
        populationReceived = v;
    }
    public void setGoodsReceived(int v) {
        goodsReceived = v;
    }
    public void setLifestyleReceived(int v) {
        lifestyleReceived = v;
    }

    // Zone daha ne kadar daha utility alabilecegini dondurme.
    public int getRemainingElecDemand() {
        return Math.max(0, getDemand() - electricityReceived);
    }
    public int getRemainingWaterDemand() {
        return Math.max(0, getDemand() - waterReceived);
    }
    public int getRemainingNetDemand() {
        return Math.max(0, getDemand() - internetReceived);
    }

    // Getter'lar.
    public int getElectricityReceived() {
        return electricityReceived;
    }
    public int getWaterReceived() {
        return waterReceived;
    }
    public int getInternetReceived() {
        return internetReceived;
    }
    public boolean isSecurityCovered() {
        return securityCovered;
    }
    public boolean isHealthCovered() {
        return healthCovered;
    }
    public boolean isEducationCovered() {
        return educationCovered;
    }
    public int getPopulationReceived() {
        return populationReceived;
    }
    public int getGoodsReceived() {
        return goodsReceived;
    }
    public int getLifestyleReceived() {
        return lifestyleReceived;
    }

    // Eski tick'teki veriler yeni tick'te sayilmasin diye her tick basi sifirliyoruz.
    public void resetTickData() {
        electricityReceived = 0;
        waterReceived       = 0;
        internetReceived    = 0;
        securityCovered     = false;
        healthCovered       = false;
        educationCovered    = false;
        populationReceived  = 0;
        goodsReceived       = 0;
        lifestyleReceived   = 0;
    }

    // Zone'lar BFS'in parcasi. Yani elektrik, su zone'lardan da gecebiliyor.
    @Override
    public boolean isPassable() {
        return true;
    }
}