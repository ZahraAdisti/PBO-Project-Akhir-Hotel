/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.ModelHotel;
import view.ViewLogin;
import view.ViewMenu;

/**
 *
 * @author Acer
 */
public class ControllerLogin {
    ViewLogin VL = new ViewLogin();
    ModelHotel MH = new ModelHotel();
    public ControllerLogin(ViewLogin VL, ModelHotel MH){
        this.VL=VL;
        this.MH=MH;
        VL.ButtonLogin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(MH.login(VL.getUsername(), VL.getPassword())){
                    ViewMenu VM = new ViewMenu();
                    ControllerMenu CM = new ControllerMenu( VM, MH);
                    VM.setVisible(true);
                    VL.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Username/password salah!");
                }
            }
            
        });
    }
    
}
