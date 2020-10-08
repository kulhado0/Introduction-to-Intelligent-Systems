class Dados{
    public int Date;
    public double AverageTemperature, AverageTemperatureUncertaintly;
    public String Country;

    Dados(){
        Date = 0;
        AverageTemperature = 0.0;
        AverageTemperatureUncertaintly = 0.0;
        Country = "China";
    }
}


public class Main{
    public static void main(String[] args){
        Dados[] Limpeza = new Dados[5000];
        String[] Arquivo = new String[5000];
        int numArq = 0;

        Arq.openRead("Databases/GlobalLandTemperatures_China.csv");
        numArq = lerArq(Arquivo, Limpeza, numArq);
        Arq.close();

        trata(Arquivo, Limpeza, numArq);
        Media(Limpeza, numArq);

        //printa(Limpeza, numArq);    
    }

    public static void printa(Dados vet[], int n){
        for (int i = 0; i < n; i++)
            MyIO.println(vet[i].Country+","+vet[i].Date +","+ vet[i].AverageTemperature+","+vet[i].AverageTemperatureUncertaintly);
    }

    public static int lerArq(String vet1[], Dados vet2[], int n){
        do{
            vet1[n] = Arq.readString();
            vet2[n] = new Dados();
            n++;
        }while(Arq.hasNext() == true);
        return n;
    }

    public static void trata(String vet1[], Dados vet2[], int n){
        for (int i = 0; i < n; i++){
            String array[] = new String[4];
            String newDate[] = new String[3];
            array = vet1[i].split(",");
            newDate = array[0].split("-");
            vet2[i].Date = Integer.parseInt(newDate[0]);
            vet2[i].AverageTemperature = Double.parseDouble(array[1]);
            vet2[i].AverageTemperatureUncertaintly = Double.parseDouble(array[2]);
            vet2[i].Country = array[3];
        }
    }

    public static void Media(Dados vet[], int n){
        Arq.openWrite("Databases/ChinaTemperatures.csv");       // Initial Database to manipulate

        double sumAvg1 = 0.0, sumAvg2 = 0.0;
        int counter = 1;

        for (int i = 0; i < n; i++){
            sumAvg1 = vet[i].AverageTemperature;
            sumAvg2 = vet[i].AverageTemperatureUncertaintly;
            int j = i+1;
            while (j<n && vet[i].Date == vet[j].Date){
                sumAvg1 += vet[j].AverageTemperature;
                sumAvg2 += vet[j].AverageTemperatureUncertaintly;
                j++;
                counter++;
                i = j-1;
            }
            Arq.println(vet[i].Country+","+vet[i].Date+","+ (sumAvg1/counter)+","+(sumAvg2/counter));
            counter = 1;
        }
        Arq.close();
    }
}