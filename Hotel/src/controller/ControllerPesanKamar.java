/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.ModelHotel;
import view.ViewDataKamar;
import view.ViewMenu;
import view.ViewPesanKamar;

/**
 *
 * @author Acer
 */
public class ControllerPesanKamar {
    ViewPesanKamar VP = new ViewPesanKamar();
    ModelHotel MH = new ModelHotel();
    String[][] dataKamar;
    int jumlah;
    String bed;
    String breakfast;
    int harga;
    int idxKamar;
    public ControllerPesanKamar(ViewPesanKamar VP, ModelHotel MD){
        this.VP=VP;
        this.MH=MH;
        ShowKamar();
        VP.LabelHarga.setText("0");
        VP.ButtonInput.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
            
        });
        VP.CBoxKamar.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                idxKamar=VP.CBoxKamar.getSelectedIndex();
                updateHarga();
            }
            
        });
        VP.RButtonDouble.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(VP.RButtonDouble.isSelected()){
                    bed="Double";
                    updateHarga();
                }
            }
        });
        VP.RButtonNo.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(VP.RButtonNo.isSelected()){
                    breakfast="No";
                    updateHarga();
                }
            }
        });
        VP.RButtonSingle.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(VP.RButtonSingle.isSelected()){
                    bed="Single";
                    updateHarga();
                }
            }
        });
        VP.RButtonYes.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(VP.RButtonYes.isSelected()){
                    breakfast="Yes";
                    updateHarga();
                }
            }
        });
        VP.SpinnerHari.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                updateHarga();
            }
            
        });
        VP.ButtonInput.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                MH.inputpesankamar(VP.getNama(), Integer.parseInt(dataKamar[idxKamar][0]), bed, breakfast, jumlah, VP.getCheckIn(), harga);
            }
            
        });
        VP.ButtonKembali.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewMenu VM=new ViewMenu();
                ControllerMenu CM=new ControllerMenu(VM,MH);
                VM.setVisible(true);
                VP.dispose();
            }            
        });
    }
    void updateHarga(){
        jumlah=(int) VP.SpinnerHari.getValue();
        harga=jumlah*Integer.parseInt(dataKamar[idxKamar][2]);
        if(VP.RButtonYes.isSelected()){
            harga+=50000*jumlah;
        }
        if(VP.RButtonDouble.isSelected()){
            harga+=100000;
        }
        VP.LabelHarga.setText(Integer.toString(harga));
    }
    void ShowKamar(){
        dataKamar=MH.readDataKamar();
        String[] kamar=new String[MH.jumlahDataKamar()];
        for(int i=0;i<MH.jumlahDataKamar();i++){
            kamar[i]=dataKamar[i][1];
        }
        VP.CBoxKamar.setModel(new DefaultComboBoxModel(kamar));
        VP.CBoxKamar.setSelectedIndex(-1);
    }
    
}
