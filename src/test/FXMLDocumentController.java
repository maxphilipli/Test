/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
/**
 *
 * @author MaxLi
 */
public class FXMLDocumentController implements Initializable{

    //playlist ranking display
    @FXML
    Label rank1;
    @FXML
    Label rank2;
    @FXML
    Label rank3;
    @FXML
    Label rank4;
    @FXML
    Label rank5;
    @FXML
    Label rank6;
    @FXML
    Label rank7;
    @FXML
    Label rank8;
    
    String element1, element2, element3, element4, element5, element6, element7, element8;
    
    
    
    //adding playlist text fields
    @FXML
    TextField playlistPopularity;
    @FXML
    TextField fileUpload;
    
    
    
    //array to hold top 8 playlists
    int[] topPlaylistArr = new int[8];
    
    
    //open playlist file of 1024 and look for top 8 playlists
    
    
    //populating the top 8 playlist
    public void populatePlaylistArr(){
        element1 = Integer.toString(topPlaylistArr[0]);
        element2 = Integer.toString(topPlaylistArr[1]);
        element3 = Integer.toString(topPlaylistArr[2]);
        element4 = Integer.toString(topPlaylistArr[3]);
        element5 = Integer.toString(topPlaylistArr[4]);
        element6 = Integer.toString(topPlaylistArr[5]);
        element7 = Integer.toString(topPlaylistArr[6]);
        element8 = Integer.toString(topPlaylistArr[7]);
    }
    
    public void updateRankings(){
        rank1.setText(element1);
        rank2.setText(element2);
        rank3.setText(element3);
        rank4.setText(element4);
        rank5.setText(element5);
        rank6.setText(element6);
        rank7.setText(element7);
        rank8.setText(element8);
    }
    
    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        populatePlaylistArr();
        updateRankings();
    }
        
        
    
    //Playlist tab search for songs button
    public void SearchButtonClicked(){
        System.out.println("God damn I'm sexy");
    }
    
    //Add playlist tab submit playlist button
    public void SubmitButtonClicked() {
        String popularity;
        popularity = playlistPopularity.getText();
        int compareTemp = Integer.parseInt(popularity);
        if (compareTemp > topPlaylistArr[0]){
            topPlaylistArr[7] = topPlaylistArr[6];
            topPlaylistArr[6] = topPlaylistArr[5];
            topPlaylistArr[5] = topPlaylistArr[4];
            topPlaylistArr[4] = topPlaylistArr[3];
            topPlaylistArr[3] = topPlaylistArr[2];
            topPlaylistArr[2] = topPlaylistArr[1];
            topPlaylistArr[1] = topPlaylistArr[0];
            topPlaylistArr[0] = compareTemp;
        }
        else if (compareTemp > topPlaylistArr[1]){
            topPlaylistArr[7] = topPlaylistArr[6];
            topPlaylistArr[6] = topPlaylistArr[5];
            topPlaylistArr[5] = topPlaylistArr[4];
            topPlaylistArr[4] = topPlaylistArr[3];
            topPlaylistArr[3] = topPlaylistArr[2];
            topPlaylistArr[2] = topPlaylistArr[1];
            topPlaylistArr[1] = compareTemp;
        }
        else if (compareTemp > topPlaylistArr[2]){
            topPlaylistArr[7] = topPlaylistArr[6];
            topPlaylistArr[6] = topPlaylistArr[5];
            topPlaylistArr[5] = topPlaylistArr[4];
            topPlaylistArr[4] = topPlaylistArr[3];
            topPlaylistArr[3] = topPlaylistArr[2];
            topPlaylistArr[2] = compareTemp;
        }
        else if (compareTemp > topPlaylistArr[3]){
            topPlaylistArr[7] = topPlaylistArr[6];
            topPlaylistArr[6] = topPlaylistArr[5];
            topPlaylistArr[5] = topPlaylistArr[4];
            topPlaylistArr[4] = topPlaylistArr[3];
            topPlaylistArr[3] = compareTemp;
        }
        else if (compareTemp > topPlaylistArr[4]){
            topPlaylistArr[7] = topPlaylistArr[6];
            topPlaylistArr[6] = topPlaylistArr[5];
            topPlaylistArr[5] = topPlaylistArr[4];
            topPlaylistArr[4] = compareTemp;
        }
        else if (compareTemp > topPlaylistArr[5]){
            topPlaylistArr[7] = topPlaylistArr[6];
            topPlaylistArr[6] = topPlaylistArr[5];
            topPlaylistArr[5] = compareTemp;
        }
        else if (compareTemp > topPlaylistArr[6]){
            topPlaylistArr[7] = topPlaylistArr[6];
            topPlaylistArr[6] = compareTemp;
        }
        else if (compareTemp > topPlaylistArr[7]){
            topPlaylistArr[7] = compareTemp;
        }
       
        populatePlaylistArr();
        updateRankings();
    }
    
    //Add playlist tab upload file button
    public void UploadButtonClicked() throws Exception{
        
        //read user input and open the file
        String filename;
        filename = fileUpload.getText();
        FileReader file = new FileReader(filename);
        BufferedReader reader = new BufferedReader(file);
        
        //go through the file line by line and grab the popularity, placing it into an array
        int[] tempPopularityHolder = new int[128];
        String line = reader.readLine();
        int iterator = 0;
        while (line != null){
            String[] numbersArray = line.split(" ");
            String tempPop = (numbersArray[numbersArray.length - 1]);
            tempPop = tempPop.replaceAll("\\s", "");
            int lineValue = Integer.parseInt(tempPop);
            tempPopularityHolder[iterator] = lineValue;
            line = reader.readLine();
            iterator++;
        }
        
        //now sort the array from smallest to biggest. i'm gonna use bubble sort cuz its easy
        for (int i = 0; i < iterator; i++){
            for (int j = 0; j < iterator - 1; j++){
                if (tempPopularityHolder[j] > tempPopularityHolder[j+1]){
                    int temp;
                    temp = tempPopularityHolder[j+1];
                    tempPopularityHolder[j+1] = tempPopularityHolder[j];
                    tempPopularityHolder[j] = temp;
                }
            }
        }
        
        
        int[] mergeArr = new int[iterator + 8];
        //now merge with existing topPlaylistArr and sort to get top 8
        for(int i = 0; i < iterator; i++){
            mergeArr[i] = tempPopularityHolder[i];
        }
        for(int i = iterator; i < iterator + 8; i++){
            mergeArr[i] = topPlaylistArr[i - iterator];
        }
        
        
        //now sort mergeArr to get top 8, then copy back to topPlaylistArr
        for (int i = 0; i < iterator+8; i++){
            for (int j = 0; j < iterator + 8 - 1; j++){
                if (mergeArr[j] < mergeArr[j+1]){
                    int temp;
                    temp = mergeArr[j+1];
                    mergeArr[j+1] = mergeArr[j];
                    mergeArr[j] = temp;
                }
            }
        }
        for (int i = 0; i < iterator + 8; i++){
        }
        
        
        for (int i = 0; i < 8; i++){
            topPlaylistArr[i]= mergeArr[i];
        }
        
        populatePlaylistArr();
        updateRankings();
        
        
    }
}
 
