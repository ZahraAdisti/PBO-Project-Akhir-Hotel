/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ModelHotel;
import view.ViewHistory;
import view.ViewMenu;

/**
 *
 * @author Acer
 */
public class ControllerHistory {
    ViewHistory VH = new ViewHistory();
    ModelHotel MH = new ModelHotel();
    String[][] PesanKamar;
    public ControllerHistory(ViewHistory VH, ModelHotel MH){
        this.VH=VH;
        this.MH=MH;
        ShowData();
        VH.ButtonKembali.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewMenu VM=new ViewMenu();
                ControllerMenu CM=new ControllerMenu(VM,MH);
                VM.setVisible(true);
                VH.dispose();
            }
            
        });
        VH.TabelHistory.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
               super.mouseClicked(e);
               int row = VH.TabelHistory.getSelectedRow();
                String Status = (String) VH.TabelHistory.getValueAt(row, 8);
                if (Status.equals("Check In")){
                    int input=JOptionPane.showConfirmDialog(null,
                        "Apakah kamu mau check out tamu '" +PesanKamar[row][1]+ "'?",
                        "Option",
                        JOptionPane.YES_NO_OPTION); // yes =0, n0=1
                
                    if(input==0){
                        int id_booking=Integer.parseInt(PesanKamar[row][0]);
                        MH.editpesankamar(id_booking);
                        ShowData();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Tamu sudah check out!");
                }  
            }
        });
        
    }
    void ShowData(){
        PesanKamar=MH.readDataPesan();
        String[][] pesan=new String[MH.jumlahDataPesan()][9];
        for(int i=0;i<MH.jumlahDataPesan();i++){
            for(int j=0;j<9;j++){
                pesan[i][j]=PesanKamar[i][j+1];
            }
        }
        String[] namaKolom={"Nama Tamu","Tipe Kamar","Bed","Breakfast","Total Hari","Check In","Check Out","Harga","Status"};
        DefaultTableModel tableModel=new DefaultTableModel(pesan,namaKolom){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        VH.TabelHistory.setModel(tableModel);
    }
}
