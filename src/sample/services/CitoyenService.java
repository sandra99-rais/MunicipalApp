package sample.services;

import sample.Models.Citoyen;

import java.util.List;

public interface CitoyenService {
    public void add(Citoyen citoyen);
    public List<Citoyen> findAll();
    public void DeleteCityoen(String text);


  public  void edit(Citoyen citoyen);
}
