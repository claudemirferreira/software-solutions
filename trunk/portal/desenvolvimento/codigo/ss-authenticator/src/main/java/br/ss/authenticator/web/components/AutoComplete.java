package br.ss.authenticator.web.components;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class AutoComplete implements Serializable {
   public String[] getLocations() {
      return new String[] {
                  "Abari", "Absurdsvanj", "Adjikistan", "Afromacoland",
                  "Agrabah", "Agaria", "Aijina", "Ajir", "Al-Alemand",
                  "Al Amarja", "Alaine", "Albenistan", "Aldestan",
                  "Al Hari", "Alpine Emirates", "Altruria",
                  "Allied States of America", "BabaKiueria", "Babalstan",
                  "Babar's Kingdom","Backhairistan", "Bacteria",
                  "Bahar", "Bahavia", "Bahkan", "Bakaslavia",
                  "Balamkadar", "Baki", "Balinderry", "Balochistan",
                  "Baltish", "Baltonia", "Bataniland, Republic of",
                  "Bayview", "Banania, Republica de", "Bandrika",
                  "Bangalia", "Bangstoff", "Bapetikosweti", "Baracq",
                  "Baraza", "Barataria", "Barclay Islands",
                  "Barringtonia", "Bay View", "Basenji",
      };
   }
}
