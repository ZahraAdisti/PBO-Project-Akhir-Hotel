/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.ModelHotel;
import view.ViewDataKamar;
import view.ViewHistory;
import view.ViewLogin;
import view.ViewMenu;
import view.ViewPesanKamar;

/**
 *
 * @author Acer
 */
public class ControllerMenu {
    ViewMenu VM = new ViewMenu();
    ModelHotel MH = new ModelHotel();
    public ControllerMenu(ViewMenu VM, ModelHotel MH){
        this.VM=VM;
        this.MH=MH;
        VM.ButtonPesan.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewPesanKamar VP = new ViewPesanKamar();
                ControllerPesanKamar CP = new ControllerPesanKamar(VP, MH);
                VP.setVisible(true);
                VM.dispose();
            }
            
        });
        VM.ButtonDataKamar.addActionListener(new ActionListener (){
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewDataKamar VD = new ViewDataKamar();
                ControllerDataKamar CP = new ControllerDataKamar(VD, MH);
                VD.setVisible(true);
                VM.dispose();
            }
   
        });
        VM.ButtonHistory.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewHistory VH = new ViewHistory();
                ControllerHistory CH = new ControllerHistory(VH, MH);
                VH.setVisible(true);
                VM.dispose(); 
            }
            
        });
        VM.ButtonLogout.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewLogin VL = new ViewLogin();
                ControllerLogin CL = new ControllerLogin(VL, MH);
                VL.setVisible(true);
                VM.dispose();
            }
            
        });
        
    }
    
    
}
