class Infos{
    public int Number;
    public String Date;
    public String Item;

    Infos(){
        this.Number = -1;
        this.Date = "00-00-0000";
        this.Item = "DEFAULT";
    }

    Infos(int n, String date, String item){
        this.Number = n;
        this.Date = date;
        this.Item = item;
    }
}

class Main {
    public static void main(String[] args){
        Arq.openRead("../Datasets/Groceries_dataset.csv");
        String Line = Arq.readLine();
        Line = Arq.readLine();

        Infos vet[] = new Infos[50000];
        String splitado[] = new String[3];
        int numArq = 0;
        do{
            Line = Arq.readLine();
            splitado = Line.split(",");
            vet[numArq++] = new Infos(Integer.parseInt(splitado[0]), splitado[1], splitado[2]);
        }while(Arq.hasNext());
        Arq.close();

        Arq.openWrite("../Datasets/Groceries_Finished.csv");
        for(int i = 0; i < numArq; i++){
            String Products = vet[i].Item+",";
            for (int j = i + 1; j < numArq; j++){
                if (vet[i].Date.compareTo(vet[j].Date) == 0){
                    if (vet[i].Number == vet[j].Number){
                        Products += (vet[j].Item + ",");
                    }
                }
            }

            char adapta[] = Products.toCharArray();
            int tam = Products.length() - 1;
            adapta[tam] = ' ';
            Products = new String (adapta);

            Arq.println(vet[i].Number +","+ vet[i].Date +","+ Products);
        }
        Arq.close();
    }
}
