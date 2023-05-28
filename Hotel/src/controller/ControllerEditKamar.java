/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ModelHotel;
import view.ViewEditKamar;
import view.ViewMenu;

/**
 *
 * @author Acer
 */
public class ControllerEditKamar {
    ViewEditKamar VE = new ViewEditKamar();
    ModelHotel MH = new ModelHotel();
    String[] data;
    public ControllerEditKamar(ViewEditKamar VE, ModelHotel MH, String[] data){
        this.VE=VE;
        this.MH=MH;
        this.data=data;
        VE.TFEditTipe.setText(data[1]);
        VE.TFHarga.setText(data[2]);
        VE.ButtonEdit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                MH. editdatakamar(Integer.parseInt(data[0]), VE.getTipeKamar(), VE.getEditHarga());
            }
            
        });
        VE.ButtonKembali.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewMenu VM=new ViewMenu();
                ControllerMenu CM=new ControllerMenu(VM,MH);
                VM.setVisible(true);
                VE.dispose();
            }
                
            
        });
    }
    
    
}
