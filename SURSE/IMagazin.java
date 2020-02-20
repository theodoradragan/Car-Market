public interface IMagazin {
    double getTotalFaraTaxe();
    double getTotalCuTaxe();
    double getTotalCuTaxeScutite();
    double getTotalTaraFaraTaxe(String taraOrigine);
    double getTotalTaraCuTaxe(String taraOrigine);
    double getTotalTaraCuTaxeScutite(String taraOrigine);
    double calculScutiriTaxe();
}
