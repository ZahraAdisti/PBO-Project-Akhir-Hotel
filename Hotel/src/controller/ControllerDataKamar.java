/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import model.ModelHotel;
import view.ViewDataKamar;
import view.ViewEditKamar;
import view.ViewMenu;



/**
 *
 * @author Acer
 */
public class ControllerDataKamar {
    ViewDataKamar VD = new ViewDataKamar();
    ModelHotel MH = new ModelHotel();
    String[][] DataKamar;
    public ControllerDataKamar(ViewDataKamar VD, ModelHotel MD){
        this.VD=VD;
        this.MH=MD;
        ShowData();
        VD.ButtonTambah.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                MD.inputdatakamar(VD.getTipeKamar(),VD.getHargaKamar());
                ShowData();
                ResetData();
            }
        });
        VD.ButtonKembali.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               ViewMenu VM=new ViewMenu();
                ControllerMenu CM=new ControllerMenu(VM,MH);
                VM.setVisible(true);
                VD.dispose();
            }
            
        });
       VD.TabelKamar.addMouseListener(new MouseAdapter(){
           @Override
           public void mouseClicked(MouseEvent e){
               super.mouseClicked(e);
               int row = VD.TabelKamar.getSelectedRow();
               String TipeKamar = (String) VD.TabelKamar.getValueAt(row, 0);
               String HargaKamar = (String) VD.TabelKamar.getValueAt(row, 1);
               int input = JOptionPane.showConfirmDialog(null,
                        "Apakah kamu mau hapus tipe kamar '" +TipeKamar+ "'?",
                        "Option",
                        JOptionPane.YES_NO_OPTION); // yes =0, n0=1
                
                if(input==0){
                    MH.hapusdatakamar(Integer.parseInt(DataKamar[row][0]));
                    ShowData();
                }
                else{
                    int input1 = JOptionPane.showConfirmDialog(null,
                        "Apakah kamu mau edit tipe kamar '"+TipeKamar+ "'?",
                        "Option",
                        JOptionPane.YES_NO_OPTION); // yes =0, n0=1
                    if(input1==0){
                        ViewEditKamar VEK=new ViewEditKamar();
                        ControllerEditKamar CEK=new ControllerEditKamar(VEK,MH,DataKamar[row]);
                        VEK.setVisible(true);
                        VD.dispose();
                    }
                }
           }
           
           
       });
        
        
    }
    void ResetData(){
        VD.TFTipeKamar.setText("");
        VD.TFHargaKamar.setText("");
    }
    void ShowData(){
        DataKamar=MH.readDataKamar();
        String[][] kamar=new String[MH.jumlahDataKamar()][2];
        for(int i=0;i<MH.jumlahDataKamar();i++){
            for(int j=0;j<2;j++){
                kamar[i][j]=DataKamar[i][j+1];
            }
        }
        String[] namaKolom={"Tipe Kamar","Harga"};
        DefaultTableModel tableModel=new DefaultTableModel(kamar,namaKolom){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        VD.TabelKamar.setModel(tableModel);
    }
    
    
}
