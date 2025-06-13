package Crime.Service;


public enum MalmoDistrict {
    CENTRUM("Centrum"),
    INNERSTADEN("Innerstaden"),
    SÖDRA_INNERSTADEN("Södra Innerstaden"),
    VÄSTRA_HAMNEN("Västra hamnen"),
    ROSENGARD("Rosengård"),
    LIMHAMN("Limhamn"),
    HYLLIE("Hyllie"),
    TRIANGELN("Triangeln"),
    OXIE("Oxie"),
    FOSIE("Fosie"),
    KULLADAL("Kulladal"),
    KIRSEBERG("Kirseberg"),
    BUNKEFLOSTRAND("Bunkeflostrand"),
    GUSTAVADOLFSTORG("GUSTAVADOLFSTORG"),
    BUNKEFLO("Bunkeflo"),
    STADION("Stadion"),
    FREDRIKSBERG("Fredriksberg"),
    SÖDERHAMN("Söderhamn"),
    KROKSBÄCK("Kroksbäck"),

    SÖDERKULLA("Söderkulla"),
    SEGEVÅNG("Segevång"),
    SLOTTSSTADEN("Slottsstaden"),

    SLOTTSPARKEN("Slottsparken"),
    HÄLLSTORP("Hällstorp"),
    BULLTOFTA("Bulltofta"),

    HINDBY("Hindby"),

    BURLÖV("Burlöv"),

    ARLÖV("Arlöv"),

    SUNNANÅ("Sunnanå"),
    FÅGELBACKEN("Fågelbacken"),
    FOLKETSPARK("Folkets Park"),

    ÖRESUNDSBRON("Öresundsbron"),

    STORTORGET("Stortorget"),

    SPILLEPENGEN("Spillepengen"),

    BARA("Bara"),

    JÄGERSRO("Jägersro"),

    ALMHÖG("Almhög"),

    PERSBORG("Persborg"),

    ELISEDAL("Elisedal"),

    TOFTANÄS("Toftanäs"),
    HUSIE("Husie"),
    MÖLLEVÅNGEN("Möllevången"),

    SIBBARP("Sibbarp"),
    SOFIELUND("Sofielund"),
    NORRA_SOFIELUND("Norra Sofielund"),
    SÖDRA_SOFIELUND("Södra Sofielund"),
    PILDAMMSPARKEN("Pildammsparken"),
    RÅDMANSVÅNGEN("Rådmansvången"),
    HERRGÅRDEN("Herrgården"),
    BELLEVUE("Bellevue"),
    BELLEVUEGÅRDEN("Bellevuegården"),
    AMIRALSSTADEN("Amiralsstaden"),
    ÖSTRA_SORGENFRI("Östra Sorgenfri"),
    VÄSTRA_SORGENFRI("Västra Sorgenfri"),

    KALKBROTTET("Kalkbrottet"),

    LORENSBORG("Lorensborg"),
    KVARNBY("Kvarnby"),
    DAVIDSHALL("Davidshall"),
    VÄRNHEM("Värnhem"),
    HELLERUP("Hellerup"),
    KLAGSHAMN("Klagshamn"),
    WALDRON("Waldrons Kvarter"),
    LINDEBORG("Lindeborg"),

    AUGUSTENBORG("Augustenborg"),
    LINDÄNGEN("Lindängen"),
    BORGMÄSTAREGÅRDEN("Borgmästaregården"),
    ANNESTAD("Annestad"),
    BEIJERSPARK("Beijers Park"),
    RIBERSBORG("Ribersborg"),
    ERIKSFÄLT("Eriksfält"),
    PROMENADEN("Promenaden"),

    RÖNNEHOLM("Rönneholm"),

    ÖSTRAHAMNEN("Östra Hamnen"),

    RÖRSJÖSTADEN("Rörsjöstaden"),

    MELLANHEDEN("Mellanheden"),
    ÖN("Ön"),
    HELLGÅRDSFÄLTET("Hellgårdsfältet"),
    NYDALA("Nydala"),
    HELENEHOLM("Heleneholm"),
    SÖDERVÄRN("Södervärn"),
    HOLMA("Holma"),
    SVEDALA("Svedala"),
    TOLLARP("Tollarp"),
    KÄLLBY("Källby");
    ;
    private final String name;
    MalmoDistrict(String name) { this.name = name; }
    public String getName() { return name; }
}
