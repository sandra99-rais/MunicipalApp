package sample.Models;



    public class personnels {
        public String Num_CIN1;
        public String Num_CIN2;
        public String Num_CIN3;
        public String Num_CIN4;
        private String Numequip;
        public String tache;


        public personnels(String numequip, String num_CIN1, String num_CIN2, String num_CIN3,String tache, String num_CIN4) {
            Num_CIN1 = num_CIN1;
            Num_CIN2 = num_CIN2;
            Num_CIN3 = num_CIN3;
            Num_CIN4 = num_CIN4;
            Numequip = numequip;
            this.tache = tache;
        }

        public String getNum_CIN1() {
            return Num_CIN1;
        }

        public void setNum_CIN1(String num_CIN1) {
            Num_CIN1 = num_CIN1;
        }

        public String getNum_CIN2() {
            return Num_CIN2;
        }

        public void setNum_CIN2(String num_CIN2) {
            Num_CIN2 = num_CIN2;
        }

        public String getNum_CIN3() {
            return Num_CIN3;
        }

        public void setNum_CIN3(String num_CIN3) {
            Num_CIN3 = num_CIN3;
        }

        public String getNum_CIN4() {
            return Num_CIN4;
        }

        public void setNum_CIN4(String num_CIN4) {
            Num_CIN4 = num_CIN4;
        }

        public String getNumequip() {
            return Numequip;
        }

        public void setNumequip(String numequip) {
            Numequip = numequip;
        }

        public String getTache() {
            return tache;
        }

        public void setTache(String tache) {
            this.tache = tache;
        }
    }


