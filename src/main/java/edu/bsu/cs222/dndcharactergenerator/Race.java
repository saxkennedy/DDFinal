package edu.bsu.cs222.dndcharactergenerator;

public enum Race {
    HALFORC(2,0,1,0,0,0),
    DRAGONBORN(2,0,0,0,0,1),
    DWARF(0,0,2,0,0,0),
    ELF(0,2,0,0,0,0),
    GNOME(0,0,0,2,0,0),
    HALFELF(0,0,0,0,0,2),
    HALFLING(0,2,0,0,0,0),
    HUMAN(1,1,1,1,1,1),
    TIEFLING(0,0,0,1,0,2);


    private final int str;
    private final int dex;
    private final int con;
    private final int intel;
    private final int wis;
    private final int chr;

    Race(int str,int dex,int con, int intel, int wis, int chr) {
        this.str = str;
        this.dex = dex;
        this.con = con;
        this.intel = intel;
        this.wis = wis;
        this.chr = chr;
    }

    public void addRacialAbilityScoreBonus(Character man) {
        man.setSTR(man.getSTR()+man.getRace().str);
        man.setDEX(man.getDEX()+man.getRace().dex);
        man.setCON(man.getCON()+man.getRace().con);
        man.setINT(man.getINT()+man.getRace().intel);
        man.setWIS(man.getWIS()+man.getRace().wis);
        man.setCHA(man.getCHA()+man.getRace().chr);
    }

    public void removeRacialAbilityScoreBonus(Character man) {
        man.setSTR(man.getSTR()-man.getRace().str);
        man.setDEX(man.getDEX()-man.getRace().dex);
        man.setCON(man.getCON()-man.getRace().con);
        man.setINT(man.getINT()-man.getRace().intel);
        man.setWIS(man.getWIS()-man.getRace().wis);
        man.setCHA(man.getCHA()-man.getRace().chr);
    }

}
