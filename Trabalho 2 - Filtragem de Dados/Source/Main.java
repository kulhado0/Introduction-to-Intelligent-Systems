class Infos{
    public int Number;
    public String Date;
    public int Dia;
    public int Mes;
    public int Ano;
    public String Item;

    Infos(){
        this.Number = -1;
        this.Date = "00-00-0000";
        this.Dia = 0;
        this.Mes = 0;
        this.Ano = 0;
        this.Item = "DEFAULT";
    }

    Infos(int n, String date, String item){
        String splita[] = new String[3];
        splita = date.split("-");

        this.Number = n;
        this.Date = date;
        this.Dia = Integer.parseInt(splita[0]);
        this.Mes = Integer.parseInt(splita[1]);
        this.Ano = Integer.parseInt(splita[2]);
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


        for (int i = 0; i < numArq - 1; i++){
            int menor = i;
            for (int j = i + 1; j < numArq; j++){
                if (vet[menor].Number > vet[j].Number){
                    menor = j;
                }
            }
            int auxN = vet[i].Number;
            int auxDia = vet[i].Dia;
            int auxM = vet[i].Mes;
            int auxA = vet[i].Ano;

            String auxD = vet[i].Date;
            String auxI = vet[i].Item;

            vet[i].Number = vet[menor].Number;
            vet[i].Date = vet[menor].Date;
            vet[i].Dia = vet[menor].Dia;
            vet[i].Mes = vet[menor].Mes;
            vet[i].Ano = vet[menor].Ano;
            vet[i].Item = vet[menor].Item;

            vet[menor].Number = auxN;
            vet[menor].Date = auxD;
            vet[menor].Item = auxI;
            vet[menor].Dia = auxDia;
            vet[menor].Mes = auxM;
            vet[menor].Ano = auxA;
        }

        for (int i = 0; i < numArq - 1; i++){
            int menor = i;
            for (int j = i + 1; j < numArq; j++){
                if(vet[menor].Number == vet[j].Number){
                    if ( ((vet[menor].Ano >= vet[j].Ano) && (vet[menor].Mes >= vet[j].Mes) && (vet[menor].Dia >= vet[j].Dia))){
                        menor = j;
                    }
                }
            }
            int auxN = vet[i].Number;
            int auxDia = vet[i].Dia;
            int auxM = vet[i].Mes;
            int auxA = vet[i].Ano;
            String auxD = vet[i].Date;
            String auxI = vet[i].Item;

            vet[i].Number = vet[menor].Number;
            vet[i].Date = vet[menor].Date;
            vet[i].Dia = vet[menor].Dia;
            vet[i].Mes = vet[menor].Mes;
            vet[i].Ano = vet[menor].Ano;
            vet[i].Item = vet[menor].Item;

            vet[menor].Number = auxN;
            vet[menor].Date = auxD;
            vet[menor].Item = auxI;
            vet[menor].Dia = auxDia;
            vet[menor].Mes = auxM;
            vet[menor].Ano = auxA;
        }

        // for (i = 0; i < numArq; i++){
        //     MyIO.println(vet[i].Number +","+ vet[i].Date +","+ vet[i].Item);
        // }

        //+ vet[i].Dia +","+ vet[i].Mes +","+ vet[i].Ano +","

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
        // for (i = 0; i < numArq; i++){
        //     Arq.println(vet[i].Number +","+ vet[i].Date +","+ Products);
        // }
        Arq.close();
    }
}
