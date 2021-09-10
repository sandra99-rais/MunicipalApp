package sample.Models;

public class login {

    String cin;
    String job;
    String mdp;

    public login(String cin, String job, String mdp) {
        this.cin = cin;
        this.job = job;
        this.mdp = mdp;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
}
