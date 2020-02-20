public class Factory {
    public static Magazin buildMagazin(String model){
        Magazin mag = null;
        switch(model){
            case "MiniMarket":
                mag = new MiniMarket();
                break;
            case "MediumMarket":
                mag = new MediumMarket();
                break;
            case "HyperMarket":
                mag = new HyperMarket();
                break;
            default:
                System.out.println("Nu cunosc acest magazin!");
                break;
        }
        return mag;
    }
}
