package com.github.coryrobertson.AutoUpdater;

import com.github.coryrobertson.Logger.LogLevels;
import com.github.coryrobertson.Logger.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class UpdaterController
{
    final String DEFAULT_FILENAME = "d.7z";
    boolean urlTextInputGiven = false;
    boolean destinationTextInputGiven = false;

    @FXML
    private TextField urlText;

    @FXML
    private TextField destinationText;

    @FXML
    private void urlInputFieldEntered(ActionEvent ae)
    {
        String url = urlText.getText();
        if (url.length() > 0)
        {
            Logger.log(url, LogLevels.LOG);
            urlTextInputGiven = true;
        }
    }

    @FXML
    private void destinationInputFieldEntered(ActionEvent ae)
    {

    }

    public void runUpdater(String url, String destination)
    {


        String[] args = {url, DEFAULT_FILENAME, destination};
        Updater.main(args);
    }

}
